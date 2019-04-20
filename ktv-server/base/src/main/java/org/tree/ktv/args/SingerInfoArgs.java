package org.tree.ktv.args;

import java.util.EnumSet;
import org.tree.commons.support.mapper.Args;
import org.tree.ktv.model.SingerInfo;

public class SingerInfoArgs implements Args<SingerInfo> {
    private EnumSet<SingerInfoColumn> set = EnumSet.noneOf(SingerInfoColumn.class);

    public SingerInfoArgs setSingerId(boolean contained) {
        if (contained)
        	set.add(SingerInfoColumn.SINGER_ID);
        else
        	set.remove(SingerInfoColumn.SINGER_ID);
        return this;
    }

    public SingerInfoArgs setSingerName(boolean contained) {
        if (contained)
        	set.add(SingerInfoColumn.SINGER_NAME);
        else
        	set.remove(SingerInfoColumn.SINGER_NAME);
        return this;
    }

    public SingerInfoArgs setSingerSex(boolean contained) {
        if (contained)
        	set.add(SingerInfoColumn.SINGER_SEX);
        else
        	set.remove(SingerInfoColumn.SINGER_SEX);
        return this;
    }

    public SingerInfoArgs setSingerNationality(boolean contained) {
        if (contained)
        	set.add(SingerInfoColumn.SINGER_NATIONALITY);
        else
        	set.remove(SingerInfoColumn.SINGER_NATIONALITY);
        return this;
    }

    public SingerInfoArgs setAllTrue() {
        set = EnumSet.allOf(SingerInfoColumn.class);
        return this;
    }

    @Override
    public String getTableName() {
        return SingerInfoColumn.TABLE_NAME;
    }

    @Override
    public String toString() {
        if(set.size() == 0)
        	return "singer_info.*";
        StringBuilder sb = new StringBuilder();
        set.forEach(arg -> sb.append(arg.getName() + ","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}