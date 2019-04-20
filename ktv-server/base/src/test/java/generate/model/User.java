package generate.model;

import org.tree.commons.generate.annotation.Column;

import java.util.Date;

/**
 * @author er_dong_chen
 * @date 2019/3/14
 */
//@Table(comment = "用户")
public class User {
    @Column(id = true)
    long id;

    @Column(comment = "账户名")
    String username;

    @Column(comment = "真实姓名")
    String real_name;

    @Column(comment = "密码")
    String password;

    @Column(comment = "性别：( 1 : 男 ) ( 0 : 女 )")
    byte user_sex;

    @Column(comment = "出生时间")
    Date birthday;

    @Column(comment = "注册时间")
    Date register_date;
}
