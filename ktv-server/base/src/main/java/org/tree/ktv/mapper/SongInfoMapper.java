package org.tree.ktv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tree.commons.support.mapper.BaseMapper;
import org.tree.ktv.args.SongInfoArgs;
import org.tree.ktv.model.SongInfo;
import org.tree.ktv.model.SongInfoExample;

public interface SongInfoMapper extends BaseMapper<SongInfo> {
    long countByExample(SongInfoExample example);

    int deleteByExample(SongInfoExample example);

    int deleteByPrimaryKey(Long songId);

    int insert(SongInfo record);

    int insertSelective(SongInfo record);

    List<SongInfo> selectByExample(SongInfoExample example);

    SongInfo selectByPrimaryKey(Long songId);

    int updateByExampleSelective(@Param("record") SongInfo record, @Param("example") SongInfoExample example);

    int updateByExample(@Param("record") SongInfo record, @Param("example") SongInfoExample example);

    int updateByPrimaryKeySelective(SongInfo record);

    int updateByPrimaryKey(SongInfo record);

    List<SongInfo> querySelective(@Param("args") SongInfoArgs args, @Param("example") SongInfoExample example);

    int insertSelectiveWithGeneratedKey(SongInfo songinfo);

    int insertBatchSelective(List<SongInfo> records);
}