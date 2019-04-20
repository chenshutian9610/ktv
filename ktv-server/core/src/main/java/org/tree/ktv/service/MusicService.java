package org.tree.ktv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tree.commons.support.mapper.IntegratedMapper;
import org.tree.commons.support.mapper.Searchable;
import org.tree.commons.support.mapper.UnionSearch;
import org.tree.commons.support.mapper.UnionSearchMapper;
import org.tree.commons.utils.MapUtils;
import org.tree.ktv.args.*;
import org.tree.ktv.model.*;
import org.tree.ktv.vo.ConditionVO;
import org.tree.ktv.vo.FavoriteVO;
import org.tree.ktv.vo.MusicVO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
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

    private String musicDir = "D:/CloudMusic/";

    private Map<Long, MusicVO> cache = new ConcurrentHashMap<>();

    private static final int pageSize = 11;

    public List<SingerInfo> getSingers() {
        return mapper.selectByExample(new SingerInfoExample());
    }

    public SingerInfo getSingerInfo(String singerName) {
        SingerInfoExample singerInfoExample = new SingerInfoExample();
        singerInfoExample.createCriteria().andSingerNameEqualTo(singerName);
        return mapper.selectForFirst(singerInfoExample);
    }

    public boolean updateSingerInfo(SingerInfo singerInfo) {
        return mapper.updateByPrimaryKeySelective(singerInfo) == 1 ? true : false;
    }

    public boolean updateSongInfo(SongInfo songInfo) {
        return mapper.updateByPrimaryKeySelective(songInfo) == 1 ? true : false;
    }

    /* 获取音乐列表 */
    public Map<String, ?> getMusicInfoList(ConditionVO condition) {
        UnionSearch unionSearch = new UnionSearch(unionSearchMapper);
        unionSearch.selectColumns(new SongInfoArgs().setAllTrue(), new SingerInfoArgs().setAllTrue());
        UnionSearch.Criteria criteria = unionSearch.createCriteria()
                .and(SongInfoColumn.SINGER_ID, "=", SingerInfoColumn.SINGER_ID);
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
        unionSearch.limit((condition.getPageIndex() - 1) * pageSize, pageSize);
        List<MusicVO> musicVOS = unionSearch.query(MusicVO.class);

        cache = musicVOS.stream().collect(Collectors.toMap(
                musicVO -> musicVO.getSongId(),
                musicVO -> musicVO,
                (x, y) -> {
                    throw new RuntimeException("不允许融合");
                },
                ConcurrentHashMap::new));

        return MapUtils.stringKeys()
                .put("musics", musicVOS)
                .put("totalPage", unionSearch.getTotalCount())
                .build();
    }

    /* 获取音乐路径 */
    public String getMusicPath(long songId) {
        // 从缓存中获取
        MusicVO musicVO = cache.get(songId);
        if (musicVO != null)
            return musicDir + musicVO.getSongFileName();
        // 获取失败，则检索数据库
        SongInfoExample songInfoExample = new SongInfoExample();
        songInfoExample.createCriteria().andSongIdEqualTo(songId);
        SongInfo songInfo = mapper.querySelectiveForFirst(new SongInfoArgs().setSongFileName(true), songInfoExample);
        return songInfo == null ? "" : (musicDir + songInfo.getSongFileName());
    }

    public Map<String, ?> getRecommendMusics(String generation) {
        UnionSearch unionSearch = new UnionSearch(unionSearchMapper);
        unionSearch.selectColumns(new SongInfoArgs().setAllTrue(), new SingerInfoArgs().setAllTrue());
        unionSearch.addColumnAlias(UnionSearch.count(SongInfoColumn.SONG_ID), "numOfCollection");
        unionSearch.createCriteria()
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
        unionSearch.createCriteria()
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
}
