package generate.model;

import org.tree.commons.generate.annotation.Column;
import org.tree.commons.generate.annotation.Table;

/**
 * @author er_dong_chen
 * @date 2019/3/26
 */
@Table(name = "tb_person", comment = "公民")
public class Person {

    @Column(id = true)
    long id; // id 需要为 long 类型

    @Column(comment = "身份证的 id")
    long idCard_id;

    @Column(comment = "名字", length = 20)
    String name;

    @Column(defaultValue = "1", comment = "性别（0：女；1：男）")
    byte sex;

}
