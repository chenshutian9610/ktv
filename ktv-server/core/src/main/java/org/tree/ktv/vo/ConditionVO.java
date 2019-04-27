package org.tree.ktv.vo;

import org.tree.commons.annotation.Comment;

/**
 * @author er_dong_chen
 * @date 2019/3/21
 */
public class ConditionVO {
    @Comment("正常搜索：搜索类型（1 歌名；2 歌名缩写；3 歌手名）")
    private Byte type;

    @Comment("正常搜索：筛选字符串")
    private String value;

    @Comment("第几页")
    private Integer pageIndex;

    @Comment("每页行数")
    private Integer pageSize;

    @Comment("扩展搜索：歌手名")
    private String singerName;

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
