package org.tree.ktv.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tree.commons.annotation.Comment;
import org.tree.ktv.service.MusicService;

import java.io.File;
import java.io.IOException;

/**
 * @author er_dong_chen
 * @date 2019/3/18
 * <p>
 * 下载不能是 @RestController，所以单独分出来
 */
@Controller
public class DownloadController {

    @Autowired
    private MusicService musicService;

    @RequestMapping("/download.do")
    public ResponseEntity<byte[]> download(@Comment("歌曲编号") Long songId) throws IOException {

        File file = new File(musicService.getMusicPath(songId));
        if (!file.exists())
            return null;

        HttpHeaders header = new HttpHeaders();
        header.setContentDispositionFormData("attachment",
                new String(file.getName().getBytes("utf-8"), "iso-8859-1"));
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), header, HttpStatus.OK);
    }
}
