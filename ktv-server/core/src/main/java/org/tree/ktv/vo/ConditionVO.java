package org.tree.ktv.vo;

/**
 * @author er_dong_chen
 * @date 2019/3/21
 */
public class ConditionVO {
    private Byte type;
    private String value;
    private Integer pageIndex;

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
