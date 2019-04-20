package org.tree.ktv.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.tree.commons.annotation.Comment;

import javax.validation.constraints.NotNull;

/**
 * @author er_dong_chen
 * @date 2019/3/14
 */
public class LoginVO {
    @NotBlank
    @Comment("用户名")
    private String username;

    @NotBlank
    @Comment("密码")
    private String password;

    @NotBlank
    @Comment("图片验证码")
    private String pictureCode;

    @NotNull
    @Comment("管理员/用户")
    private Boolean admin;

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
