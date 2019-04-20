package org.tree.ktv.model;

import org.tree.commons.annotation.Comment;

public class SingerInfo {
    @Comment("编号")
    private Long singerId;

    @Comment("歌手姓名")
    private String singerName;

    @Comment("性别：( 1 : 男 ) ( 0 : 女 )( -1 : 未知)")
    private Byte singerSex;

    @Comment("所在国籍")
    private String singerNationality;

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
        this.singerName = singerName == null ? null : singerName.trim();
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
        this.singerNationality = singerNationality == null ? null : singerNationality.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", singerId=").append(singerId);
        sb.append(", singerName=").append(singerName);
        sb.append(", singerSex=").append(singerSex);
        sb.append(", singerNationality=").append(singerNationality);
        sb.append("]");
        return sb.toString();
    }
}