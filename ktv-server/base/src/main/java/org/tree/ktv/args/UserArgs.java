package org.tree.ktv.args;

import java.util.EnumSet;
import org.tree.commons.support.mapper.Args;
import org.tree.ktv.model.User;

public class UserArgs implements Args<User> {
    private EnumSet<UserColumn> set = EnumSet.noneOf(UserColumn.class);

    public UserArgs setId(boolean contained) {
        if (contained)
        	set.add(UserColumn.ID);
        else
        	set.remove(UserColumn.ID);
        return this;
    }

    public UserArgs setUsername(boolean contained) {
        if (contained)
        	set.add(UserColumn.USERNAME);
        else
        	set.remove(UserColumn.USERNAME);
        return this;
    }

    public UserArgs setRealName(boolean contained) {
        if (contained)
        	set.add(UserColumn.REAL_NAME);
        else
        	set.remove(UserColumn.REAL_NAME);
        return this;
    }

    public UserArgs setPassword(boolean contained) {
        if (contained)
        	set.add(UserColumn.PASSWORD);
        else
        	set.remove(UserColumn.PASSWORD);
        return this;
    }

    public UserArgs setUserSex(boolean contained) {
        if (contained)
        	set.add(UserColumn.USER_SEX);
        else
        	set.remove(UserColumn.USER_SEX);
        return this;
    }

    public UserArgs setBirthday(boolean contained) {
        if (contained)
        	set.add(UserColumn.BIRTHDAY);
        else
        	set.remove(UserColumn.BIRTHDAY);
        return this;
    }

    public UserArgs setRegisterDate(boolean contained) {
        if (contained)
        	set.add(UserColumn.REGISTER_DATE);
        else
        	set.remove(UserColumn.REGISTER_DATE);
        return this;
    }

    public UserArgs setAllTrue() {
        set = EnumSet.allOf(UserColumn.class);
        return this;
    }

    @Override
    public String getTableName() {
        return UserColumn.TABLE_NAME;
    }

    @Override
    public String toString() {
        if(set.size() == 0)
        	return "user.*";
        StringBuilder sb = new StringBuilder();
        set.forEach(arg -> sb.append(arg.getName() + ","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}