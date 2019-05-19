package org.tree.ktv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.tree.commons.support.mapper.IntegratedMapper;
import org.tree.commons.support.mapper.Searchable;
import org.tree.commons.support.mapper.UnionSearch;
import org.tree.commons.support.mapper.UnionSearchMapper;
import org.tree.commons.utils.MapUtils;
import org.tree.ktv.Main;
import org.tree.ktv.args.*;
import org.tree.ktv.model.*;
import org.tree.ktv.vo.ConditionVO;
import org.tree.ktv.vo.FavoriteVO;
import org.tree.ktv.vo.MusicVO;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author er_dong_chen
 * @date 2019/3/15
 */
@Service
@Transactional
public class MusicService {
    @Autowired
    private IntegratedMapper mapper;

    @Autowired
    private UnionSearchMapper unionSearchMapper;

    @Value("${music.dir}")
    private String musicDir;

    private Map<Long, String> musicPathCache = new ConcurrentHashMap<>();

    public List<Map<?, ?>> getSingers() {
        List<Map<?, ?>> result = new ArrayList<>();
        List<SingerInfo> singerInfos = mapper.querySelective(
                new SingerInfoArgs().setSingerName(true).setSingerNationality(true),
                new SingerInfoExample());
        singerInfos.stream().collect(Collectors.groupingBy(SingerInfo::getSingerNationality)).forEach((k, v) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nationality", k);
            map.put("singer", v);
            result.add(map);
        });
        return result;
    }

    public boolean updateSingerInfo(SingerInfo singerInfo) {
        return mapper.updateByPrimaryKeySelective(singerInfo) == 1 ? true : false;
    }

    public boolean updateSongInfo(SongInfo songInfo) {
        return mapper.updateByPrimaryKeySelective(songInfo) == 1 ? true : false;
    }

    public boolean deleteSong(long songId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andSongIdEqualTo(songId);
        if (mapper.deleteByExample(favoriteExample) == 0) return false;
        return mapper.deleteByPrimaryKey(SongInfo.class, songId) == 1 ? true : false;
    }

    /* 获取音乐列表 */
    public Map<String, ?> getMusicInfoList(ConditionVO condition) {
        UnionSearch unionSearch = new UnionSearch(unionSearchMapper);
        unionSearch.selectColumns(new SongInfoArgs().setAllTrue(), new SingerInfoArgs().setAllTrue());
        UnionSearch.Criteria criteria = unionSearch.getCriteria()
                .and(SongInfoColumn.SINGER_ID, "=", SingerInfoColumn.SINGER_ID);

        if (condition.getSingerName() != null && condition.getSingerName().length() != 0)
            criteria.and(SingerInfoColumn.SINGER_NAME, String.format("= '%s'", condition.getSingerName()));

        if (condition.getType() != null) {
            Searchable searchable = null;
            switch (condition.getType()) {
                case 1:
                    searchable = SongInfoColumn.SONG_NAME;
                    break;
                case 2:
                    searchable = SongInfoColumn.SONG_NAME_ABBR;
                    break;
                case 3:
                    searchable = SingerInfoColumn.SINGER_NAME;
                    break;
            }
            criteria.and(searchable, String.format("like '%%%s%%'", condition.getValue()));
        }
        if (condition.getPageSize() != null) {
            int pageSize = condition.getPageSize();
            unionSearch.limit((condition.getPageIndex() - 1) * pageSize, pageSize);
        }

        List<MusicVO> musicVOS = unionSearch.query(MusicVO.class);

        musicVOS.forEach(musicVO -> musicPathCache.put(musicVO.getSongId(), musicVO.getSongFileName()));

        return MapUtils.stringKeys()
                .put("musics", musicVOS)
                .put("totalPage", unionSearch.getTotalCount())
                .build();
    }

    /* 获取音乐路径 */
    public File getMusicFile(long songId) {
        // 从缓存中获取
        if (musicPathCache.get(songId) != null)
            return new File(musicDir + musicPathCache.get(songId));
        // 获取失败，则检索数据库
        SongInfoExample songInfoExample = new SongInfoExample();
        songInfoExample.createCriteria().andSongIdEqualTo(songId);
        SongInfo songInfo = mapper.querySelectiveForFirst(new SongInfoArgs().setSongFileName(true), songInfoExample);
        return songInfo == null ? null : new File(musicDir + songInfo.getSongFileName());
    }

    public Map<String, ?> getRecommendMusics(String generation) {
        UnionSearch unionSearch = new UnionSearch(unionSearchMapper);
        unionSearch.selectColumns(new SongInfoArgs().setAllTrue(), new SingerInfoArgs().setAllTrue());
        unionSearch.addColumnAlias(UnionSearch.count(SongInfoColumn.SONG_ID), "numOfCollection");
        unionSearch.getCriteria()
                .and(SongInfoColumn.SINGER_ID, "=", SingerInfoColumn.SINGER_ID)
                .and(SongInfoColumn.SONG_ID, "=", FavoriteColumn.SONG_ID)
                .and(FavoriteColumn.GENERATION, String.format("= %s", generation));
        unionSearch.groupBy(SongInfoColumn.SONG_ID).orderByCount(SongInfoColumn.SONG_ID, false).limit(0, 10);
        List<MusicVO> musicVOS = unionSearch.query(MusicVO.class);
        return MapUtils.put("musics", musicVOS).build();
    }

    public Map<String, ?> getFavorites(long userId) {
        UnionSearch unionSearch = new UnionSearch(unionSearchMapper);
        unionSearch.selectColumns(new SongInfoArgs().setSongId(true).setSongName(true));
        unionSearch.getCriteria()
                .and(FavoriteColumn.USER_ID, "=" + userId)
                .and(FavoriteColumn.SONG_ID, "=", SongInfoColumn.SONG_ID);
        return MapUtils.put("favoriteMusics", unionSearch.query(FavoriteVO.class)).build();
    }

    public boolean addFavorite(User user, long songId) {
        long userId = user.getId();
        int generation = Integer.parseInt(new SimpleDateFormat("yyyy").format(user.getBirthday()));
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andUserIdEqualTo(userId).andSongIdEqualTo(songId);
        if (mapper.countByExample(favoriteExample) > 0)
            return true;
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setSongId(songId);
        favorite.setGeneration(generation);
        return mapper.insert(favorite) == 1 ? true : false;
    }

    public boolean removeFavorite(long userId, long songId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andUserIdEqualTo(userId).andSongIdEqualTo(songId);
        return mapper.deleteByExample(favoriteExample) == 1 ? true : false;
    }

    public String dealMusicFile(MultipartFile multipartFile) throws IOException {
        File dir=new File(String.format("%s/其他", musicDir));
        if(!dir.exists()) dir.mkdir();

        File file = new File(String.format("%s/其他/%s", musicDir, multipartFile.getOriginalFilename()));

        SongInfo songInfo = new SongInfo();

        try {
            songInfo.setSongName(file.getName().substring(0, file.getName().lastIndexOf(".")).split(" - ")[1]);
        } catch (Exception e) {
            return file.getName() + "上传失败，文件名格式错误";
        }

        FileCopyUtils.copy(multipartFile.getBytes(), file);

        songInfo.setSongNameAbbr(Main.getAbbr(songInfo.getSongName()));
        songInfo.setSongLanguage("其他");
        songInfo.setSongFileName(songInfo.getSongLanguage() + "/" + file.getName());
        try {
            songInfo.setDuration(Main.getMusicDuration(file));
        } catch (Exception e) {
        }

        String singerName = file.getName().split(" - ")[0];
        SingerInfoExample singerInfoExample = new SingerInfoExample();
        singerInfoExample.createCriteria().andSingerNameEqualTo(singerName);
        Long singer_id = Optional.ofNullable(mapper.querySelectiveForFirst(new SingerInfoArgs().setSingerId(true), singerInfoExample)).map(SingerInfo::getSingerId).orElse(null);

        if (singer_id == null) {
            SingerInfo singerInfo = new SingerInfo();
            singerInfo.setSingerName(singerName);
            singerInfo.setSingerNationality(Main.getNationality(songInfo.getSongLanguage()));
            songInfo.setSingerId(singerInfo.getSingerId());
            mapper.insertSelective(singerInfo);
        } else songInfo.setSingerId(singer_id);

        mapper.insertSelective(songInfo);

        return null;
    }
}
