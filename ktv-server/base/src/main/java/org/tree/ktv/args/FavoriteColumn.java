package org.tree.ktv.args;

import org.tree.commons.support.mapper.Searchable;

public enum FavoriteColumn implements Searchable {
	USER_ID("favorite.user_id"),
	SONG_ID("favorite.song_id"),
	GENERATION("favorite.generation");

	public static final String TABLE_NAME = "favorite";

	private String name;

	FavoriteColumn (String name) {
		this.name = name;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getName() {
		return name;
	}
}