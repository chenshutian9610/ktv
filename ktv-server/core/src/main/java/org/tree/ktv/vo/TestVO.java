package org.tree.ktv.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author er_dong_chen
 * @date 2019/3/18
 */
public class TestVO {
    Date date1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date2;

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

}
