package org.tree.ktv.args;

import org.tree.commons.support.mapper.Searchable;

public enum SongInfoColumn implements Searchable {
	SONG_ID("song_info.song_id"),
	SINGER_ID("song_info.singer_id"),
	SONG_NAME("song_info.song_name"),
	SONG_NAME_ABBR("song_info.song_name_abbr"),
	SONG_LANGUAGE("song_info.song_language"),
	SONG_FILE_NAME("song_info.song_file_name"),
	DURATION("song_info.duration");

	public static final String TABLE_NAME = "song_info";

	private String name;

	SongInfoColumn (String name) {
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