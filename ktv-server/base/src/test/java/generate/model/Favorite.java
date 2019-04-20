package generate.model;

import org.tree.commons.generate.annotation.Column;
import org.tree.commons.generate.annotation.Table;

/**
 * @author er_dong_chen
 * @date 2019/3/15
 */
@Table(comment = "收藏")
public class Favorite {
    @Column(comment = "用户 id")
    long user_id;

    @Column(comment = "歌曲编号")
    long song_id;

    @Column(comment = "年代")
    int generation;
}
