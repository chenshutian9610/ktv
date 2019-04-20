package org.tree.ktv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tree.commons.annotation.Comment;
import org.tree.commons.annotation.Ignore;
import org.tree.commons.support.controller.Result;
import org.tree.ktv.model.SingerInfo;
import org.tree.ktv.model.SongInfo;
import org.tree.ktv.model.User;
import org.tree.ktv.service.MusicService;
import org.tree.ktv.vo.ConditionVO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * @author er_dong_chen
 * @date 2019/3/15
 */
@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    /* 获取音乐列表 */
    @RequestMapping("/getMusicList.do")
    public Result getMusicList(ConditionVO conditionVO) {
        return new Result(musicService.getMusicInfoList(conditionVO));
    }

    /* 播放音乐 */
    @RequestMapping("/play.do")
    public void play(@Comment("歌曲编号") Long songId,
                     @Ignore HttpServletResponse response) {

        if (songId == null) return;

        File file = new File(musicService.getMusicPath(songId));

        // 在线播放音乐的 MIME 设置
        response.setContentType("audio/mpeg");
        response.setHeader("Accept-Ranges:", "bytes");
        response.setHeader("Cache-Control", "max-age=31536000, must-revalidate");
        response.setHeader("Content-Length", file.length() + "");

        try {
            InputStream inputStream = new FileInputStream(file);
            StreamUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
        } catch (IOException e) {
        }
    }

    @RequestMapping("/getRecommendMusics.do")
    public Result getRecommendMusics(@Ignore HttpSession session) {
        String generation = new SimpleDateFormat("yyyy").format(((User) session.getAttribute("user")).getBirthday());
        return new Result(musicService.getRecommendMusics(generation));
    }

    /****************************** 收藏 *******************************/

    /* 获取收藏列表 */
    @RequestMapping("/getFavorites.do")
    public Result getFavorites(@Ignore HttpSession session) {
        long id = ((User) session.getAttribute("user")).getId();
        return new Result(musicService.getFavorites(id));
    }

    /* 管理收藏 */
    @RequestMapping("/manageFavorite.do")
    public Result manageFavorite(@Comment("歌曲编号") Long songId,
                                 @Comment("添加或删除") Boolean add,
                                 @Ignore HttpSession session) {
        if (songId == null || add == null)
            return new Result(Result.PARAMETER_MISSING);
        User user = (User) session.getAttribute("user");
        return new Result(add ? musicService.addFavorite(user, songId) : musicService.removeFavorite(user.getId(), songId));
    }

    @RequestMapping("/search.do")
    public Result search(ConditionVO conditionVO) {
        if (conditionVO.getType() == null || conditionVO.getValue() == null)
            return new Result(Result.PARAMETER_MISSING);
        if (conditionVO.getType() > 3 || conditionVO.getType() < 1)
            return new Result("type 取值错误");
        return new Result(musicService.getMusicInfoList(conditionVO));
    }

    //    @RequestMapping("/getSingers.do")
//    public Result getSingers() {
//        Map<?, ?> map = MapUtils.put("singers", musicService.getSingers()).build();
//        return new Result(true, "", map);
//    }
//
//    /* 获取歌手信息 */
//    @RequestMapping("/getSingerInfo.do")
//    public Result getSingerInfo(String singerName) {
//        if (singerName == null)
//            return new Result("参数缺失");
//
//        Map<?, ?> map = MapUtils.put("singer", musicService.getSingerInfo(singerName)).build();
//        return new Result(map);
//    }
//

    /* 更新歌手信息 */
    @RequestMapping("/updateSinger.do")
    public Result updateSinger(SingerInfo singerInfo) {
        if (singerInfo.getSingerId() == null)
            return new Result(false);

        return new Result(musicService.updateSingerInfo(singerInfo));
    }

    /* 更新歌曲信息 */
    @RequestMapping("/updateSong.do")
    public Result updateSong(SongInfo songInfo) {
        if (songInfo.getSongId() == null)
            return new Result();

        if (songInfo.getSongNameAbbr() != null)
            songInfo.setSongNameAbbr(songInfo.getSongNameAbbr().toUpperCase());

        return new Result(musicService.updateSongInfo(songInfo));
    }
}
