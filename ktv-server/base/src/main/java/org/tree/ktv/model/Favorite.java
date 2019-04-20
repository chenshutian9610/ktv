package org.tree.ktv.model;

import org.tree.commons.annotation.Comment;

public class Favorite {
    @Comment("用户 id")
    private Long userId;

    @Comment("歌曲编号")
    private Long songId;

    @Comment("年代")
    private Integer generation;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", songId=").append(songId);
        sb.append(", generation=").append(generation);
        sb.append("]");
        return sb.toString();
    }
}