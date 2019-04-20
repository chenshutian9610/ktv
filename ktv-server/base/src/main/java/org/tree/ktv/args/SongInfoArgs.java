package org.tree.ktv.args;

import java.util.EnumSet;
import org.tree.commons.support.mapper.Args;
import org.tree.ktv.model.SongInfo;

public class SongInfoArgs implements Args<SongInfo> {
    private EnumSet<SongInfoColumn> set = EnumSet.noneOf(SongInfoColumn.class);

    public SongInfoArgs setSongId(boolean contained) {
        if (contained)
        	set.add(SongInfoColumn.SONG_ID);
        else
        	set.remove(SongInfoColumn.SONG_ID);
        return this;
    }

    public SongInfoArgs setSingerId(boolean contained) {
        if (contained)
        	set.add(SongInfoColumn.SINGER_ID);
        else
        	set.remove(SongInfoColumn.SINGER_ID);
        return this;
    }

    public SongInfoArgs setSongName(boolean contained) {
        if (contained)
        	set.add(SongInfoColumn.SONG_NAME);
        else
        	set.remove(SongInfoColumn.SONG_NAME);
        return this;
    }

    public SongInfoArgs setSongNameAbbr(boolean contained) {
        if (contained)
        	set.add(SongInfoColumn.SONG_NAME_ABBR);
        else
        	set.remove(SongInfoColumn.SONG_NAME_ABBR);
        return this;
    }

    public SongInfoArgs setSongLanguage(boolean contained) {
        if (contained)
        	set.add(SongInfoColumn.SONG_LANGUAGE);
        else
        	set.remove(SongInfoColumn.SONG_LANGUAGE);
        return this;
    }

    public SongInfoArgs setSongFileName(boolean contained) {
        if (contained)
        	set.add(SongInfoColumn.SONG_FILE_NAME);
        else
        	set.remove(SongInfoColumn.SONG_FILE_NAME);
        return this;
    }

    public SongInfoArgs setDuration(boolean contained) {
        if (contained)
        	set.add(SongInfoColumn.DURATION);
        else
        	set.remove(SongInfoColumn.DURATION);
        return this;
    }

    public SongInfoArgs setAllTrue() {
        set = EnumSet.allOf(SongInfoColumn.class);
        return this;
    }

    @Override
    public String getTableName() {
        return SongInfoColumn.TABLE_NAME;
    }

    @Override
    public String toString() {
        if(set.size() == 0)
        	return "song_info.*";
        StringBuilder sb = new StringBuilder();
        set.forEach(arg -> sb.append(arg.getName() + ","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}