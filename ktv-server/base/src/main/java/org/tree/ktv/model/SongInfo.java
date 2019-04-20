package org.tree.ktv.model;

import org.tree.commons.annotation.Comment;

public class SongInfo {
    @Comment("编号")
    private Long songId;

    @Comment("歌手编号")
    private Long singerId;

    @Comment("歌名")
    private String songName;

    @Comment("歌名缩写")
    private String songNameAbbr;

    @Comment("语种")
    private String songLanguage;

    @Comment("歌曲文件名")
    private String songFileName;

    @Comment("时长")
    private String duration;

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName == null ? null : songName.trim();
    }

    public String getSongNameAbbr() {
        return songNameAbbr;
    }

    public void setSongNameAbbr(String songNameAbbr) {
        this.songNameAbbr = songNameAbbr == null ? null : songNameAbbr.trim();
    }

    public String getSongLanguage() {
        return songLanguage;
    }

    public void setSongLanguage(String songLanguage) {
        this.songLanguage = songLanguage == null ? null : songLanguage.trim();
    }

    public String getSongFileName() {
        return songFileName;
    }

    public void setSongFileName(String songFileName) {
        this.songFileName = songFileName == null ? null : songFileName.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", songId=").append(songId);
        sb.append(", singerId=").append(singerId);
        sb.append(", songName=").append(songName);
        sb.append(", songNameAbbr=").append(songNameAbbr);
        sb.append(", songLanguage=").append(songLanguage);
        sb.append(", songFileName=").append(songFileName);
        sb.append(", duration=").append(duration);
        sb.append("]");
        return sb.toString();
    }
}