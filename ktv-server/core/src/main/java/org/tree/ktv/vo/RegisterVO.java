package org.tree.ktv.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.tree.commons.annotation.Comment;
import org.tree.ktv.model.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author er_dong_chen
 * @date 2019/3/15
 */
public class RegisterVO {

    @NotBlank
    @Comment("账户名")
    private String username;

    @NotBlank
    @Comment("真实姓名")
    private String realName;

    @NotBlank
    @Comment("密码")
    private String password;

    @NotNull
    @Comment("性别")
    private Byte userSex;

    @Comment("出生时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotBlank
    @Comment("验证码")
    private String pictureCode;

    public User parseUser() {
        User user = new User();
        user.setUsername(username);
        user.setRealName(realName);
        user.setPassword(password);
        user.setUserSex(userSex);
        user.setBirthday(birthday);
        return user;
    }

    public String getPictureCode() {
        return pictureCode;
    }

    public void setPictureCode(String pictureCode) {
        this.pictureCode = pictureCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
