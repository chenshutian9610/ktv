package org.tree.ktv.vo;

import org.tree.commons.annotation.Comment;

/**
 * @author er_dong_chen
 * @date 2019/3/18
 */
public class MusicVO {

    @Comment("编号")
    private Long songId;

    @Comment("歌名")
    private String songName;

    @Comment("歌名缩写")
    private String songNameAbbr;

    @Comment("语种")
    private String songLanguage;

    @Comment("时长")
    private String duration;

    @Comment("歌曲文件名")
    private String songFileName;

    @Comment("歌手编号")
    private Long singerId;

    @Comment("歌手姓名")
    private String singerName;

    @Comment("性别：( 1 : 男 ) ( 0 : 女 )( -1 : 未知)")
    private Byte singerSex;

    @Comment("所在国籍")
    private String singerNationality;

    @Comment("收藏次数")
    private int numOfCollection;

    public int getNumOfCollection() {
        return numOfCollection;
    }

    public void setNumOfCollection(int numOfCollection) {
        this.numOfCollection = numOfCollection;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongNameAbbr() {
        return songNameAbbr;
    }

    public void setSongNameAbbr(String songNameAbbr) {
        this.songNameAbbr = songNameAbbr;
    }

    public String getSongLanguage() {
        return songLanguage;
    }

    public void setSongLanguage(String songLanguage) {
        this.songLanguage = songLanguage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Byte getSingerSex() {
        return singerSex;
    }

    public void setSingerSex(Byte singerSex) {
        this.singerSex = singerSex;
    }

    public String getSingerNationality() {
        return singerNationality;
    }

    public void setSingerNationality(String singerNationality) {
        this.singerNationality = singerNationality;
    }

    public String getSongFileName() {
        return songFileName;
    }

    public void setSongFileName(String songFileName) {
        this.songFileName = songFileName;
    }
}
