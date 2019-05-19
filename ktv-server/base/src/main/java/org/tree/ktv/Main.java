package org.tree.ktv;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tree.commons.support.mapper.IntegratedMapper;
import org.tree.ktv.args.SingerInfoArgs;
import org.tree.ktv.args.SongInfoArgs;
import org.tree.ktv.model.SingerInfo;
import org.tree.ktv.model.SongInfo;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author er_dong_chen
 * @date 2019/3/18
 */
public class Main {

    public static void main(String[] args) {
        new Main().init();
    }

    public void init() {
        File dir = new File("D:\\CloudMusic");
        File[] subDirs = dir.listFiles((f) -> f.isDirectory() && !f.getName().equals("cache"));
        List<SongInfo> songInfos = new ArrayList<>();
        List<SingerInfo> singerInfos = new ArrayList<>();
        long singer_id = 0;
        Map<String, Long> singerIdMap = new HashMap<>();
        for (File subDir : subDirs) {
            File[] mp3List = subDir.listFiles((f) -> f.getName().endsWith(".mp3"));
            for (File file : mp3List) {
                SongInfo songInfo = new SongInfo();
                try {
                    songInfo.setSongName(file.getName().substring(0, file.getName().lastIndexOf(".")).split(" - ")[1]);
                } catch (Exception e) {
                    continue;
                }
                songInfo.setSongNameAbbr(getAbbr(songInfo.getSongName()));
                songInfo.setSongLanguage(file.getParent().substring(file.getParent().lastIndexOf("\\") + 1));
                songInfo.setSongFileName(songInfo.getSongLanguage() + "/" + file.getName());
                try {
                    songInfo.setDuration(getMusicDuration(file));
                } catch (Exception e) {
                }
                songInfos.add(songInfo);

                String singerName = file.getName().split(" - ")[0];
                if (singerIdMap.get(singerName) == null) {
                    SingerInfo singerInfo = new SingerInfo();
                    singerInfo.setSingerName(singerName);
                    singerInfo.setSingerNationality(getNationality(songInfo.getSongLanguage()));
                    singerInfo.setSingerId(++singer_id);
                    singerInfos.add(singerInfo);
                    singerIdMap.put(singerInfo.getSingerName(), singerInfo.getSingerId());
                    songInfo.setSingerId(singerInfo.getSingerId());
                } else songInfo.setSingerId(singerIdMap.get(singerName));
            }
        }
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        context.getBean(JdbcTemplate.class).execute(String.format("truncate %s", new SingerInfoArgs().getTableName()));
        context.getBean(JdbcTemplate.class).execute(String.format("truncate %s", new SongInfoArgs().getTableName()));
        context.getBean(IntegratedMapper.class).insertBatchSelective(singerInfos);
        context.getBean(IntegratedMapper.class).insertBatchSelective(songInfos);
        System.out.println();
    }

    public static String getNationality(String language) {
        switch (language) {
            case "中文":
                return "中国";
            case "日语":
                return "日本";
            case "英语":
                return "欧美地区";
        }
        return null;
    }

    public static String getMusicDuration(File mp3File) throws Exception {
        MP3File f = (MP3File) AudioFileIO.read(mp3File);
        MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
        long seconds = audioHeader.getTrackLength();
        return LocalTime.ofSecondOfDay(seconds).toString();
    }

    public static String getAbbr(String name) {
        if (name.indexOf("(") != -1)
            name = name.substring(0, name.indexOf("("));
        if (name.indexOf("（") != -1)
            name = name.substring(0, name.indexOf("（"));
        if (!String.join("", name.split(" ")).matches("^\\w+$"))
            return "";
        String[] strs = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(str.substring(0, 1).toUpperCase());
        return sb.toString();
    }
}
