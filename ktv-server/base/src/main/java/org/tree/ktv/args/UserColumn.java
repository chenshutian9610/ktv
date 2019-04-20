package org.tree.ktv.args;

import org.tree.commons.support.mapper.Searchable;

public enum UserColumn implements Searchable {
	ID("user.id"),
	USERNAME("user.username"),
	REAL_NAME("user.real_name"),
	PASSWORD("user.password"),
	USER_SEX("user.user_sex"),
	BIRTHDAY("user.birthday"),
	REGISTER_DATE("user.register_date");

	public static final String TABLE_NAME = "user";

	private String name;

	UserColumn (String name) {
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