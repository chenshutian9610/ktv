package generate.model;

import org.tree.commons.generate.annotation.Column;

/**
 * @author er_dong_chen
 * @date 2019/3/14
 */
//@Table(comment = "歌曲信息")
public class SongInfo {
    @Column(id = true, comment = "编号")
    long song_id;

    @Column(comment = "歌手编号")
    long singer_id;

    @Column(comment = "歌名",length = 255)
    String song_name;

    @Column(comment = "歌名缩写")
    String song_name_abbr;

    @Column(comment = "语种")
    String song_language;

    @Column(comment = "歌曲文件名", length = 255)
    String song_file_name;

    @Column(comment = "时长", length = 8)
    String duration;
}
