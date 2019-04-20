package org.tree.ktv.model;

import org.tree.commons.annotation.Comment;

import java.util.Date;

public class User {
    private Long id;

    @Comment("账户名")
    private String username;

    @Comment("真实姓名")
    private String realName;

    @Comment("密码")
    private String password;

    @Comment("性别：( 1 : 男 ) ( 0 : 女 )")
    private Byte userSex;

    @Comment("出生时间")
    private Date birthday;

    @Comment("注册时间")
    private Date registerDate;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
        this.userSex = userSex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", realName=").append(realName);
        sb.append(", password=").append(password);
        sb.append(", userSex=").append(userSex);
        sb.append(", birthday=").append(birthday);
        sb.append(", registerDate=").append(registerDate);
        sb.append("]");
        return sb.toString();
    }
}