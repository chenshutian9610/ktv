package generate.model;

import org.tree.commons.generate.annotation.Column;
import org.tree.commons.generate.annotation.Table;

/**
 * @author er_dong_chen
 * @date 2019/3/14
 */
@Table(comment = "歌星信息")
public class SingerInfo {
    @Column(id = true, comment = "编号")
    long singer_id;

    @Column(comment = "歌手姓名")
    String singer_name;

    @Column(comment = "性别：( 1 : 男 ) ( 0 : 女 )( -1 : 未知)", defaultValue = "-1")
    byte singer_sex;

    @Column(comment = "所在国籍")
    String singer_nationality;
}
