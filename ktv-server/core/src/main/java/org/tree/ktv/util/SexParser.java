package org.tree.ktv.util;

/**
 * @author er_dong_chen
 * @date 2019/3/16
 */
public class SexParser {
    public static String parse(byte bit) {
        return bit == 1 ? "男" : "女";
    }
}
