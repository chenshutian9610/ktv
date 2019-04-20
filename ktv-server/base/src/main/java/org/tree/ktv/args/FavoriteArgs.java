package org.tree.ktv.args;

import java.util.EnumSet;
import org.tree.commons.support.mapper.Args;
import org.tree.ktv.model.Favorite;

public class FavoriteArgs implements Args<Favorite> {
    private EnumSet<FavoriteColumn> set = EnumSet.noneOf(FavoriteColumn.class);

    public FavoriteArgs setUserId(boolean contained) {
        if (contained)
        	set.add(FavoriteColumn.USER_ID);
        else
        	set.remove(FavoriteColumn.USER_ID);
        return this;
    }

    public FavoriteArgs setSongId(boolean contained) {
        if (contained)
        	set.add(FavoriteColumn.SONG_ID);
        else
        	set.remove(FavoriteColumn.SONG_ID);
        return this;
    }

    public FavoriteArgs setGeneration(boolean contained) {
        if (contained)
        	set.add(FavoriteColumn.GENERATION);
        else
        	set.remove(FavoriteColumn.GENERATION);
        return this;
    }

    public FavoriteArgs setAllTrue() {
        set = EnumSet.allOf(FavoriteColumn.class);
        return this;
    }

    @Override
    public String getTableName() {
        return FavoriteColumn.TABLE_NAME;
    }

    @Override
    public String toString() {
        if(set.size() == 0)
        	return "favorite.*";
        StringBuilder sb = new StringBuilder();
        set.forEach(arg -> sb.append(arg.getName() + ","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}