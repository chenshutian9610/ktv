package org.tree.ktv.args;

import org.tree.commons.support.mapper.Searchable;

public enum SingerInfoColumn implements Searchable {
	SINGER_ID("singer_info.singer_id"),
	SINGER_NAME("singer_info.singer_name"),
	SINGER_SEX("singer_info.singer_sex"),
	SINGER_NATIONALITY("singer_info.singer_nationality");

	public static final String TABLE_NAME = "singer_info";

	private String name;

	SingerInfoColumn (String name) {
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