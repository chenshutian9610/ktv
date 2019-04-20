package org.tree.ktv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tree.commons.support.mapper.BaseMapper;
import org.tree.ktv.args.SingerInfoArgs;
import org.tree.ktv.model.SingerInfo;
import org.tree.ktv.model.SingerInfoExample;

public interface SingerInfoMapper extends BaseMapper<SingerInfo> {
    long countByExample(SingerInfoExample example);

    int deleteByExample(SingerInfoExample example);

    int deleteByPrimaryKey(Long singerId);

    int insert(SingerInfo record);

    int insertSelective(SingerInfo record);

    List<SingerInfo> selectByExample(SingerInfoExample example);

    SingerInfo selectByPrimaryKey(Long singerId);

    int updateByExampleSelective(@Param("record") SingerInfo record, @Param("example") SingerInfoExample example);

    int updateByExample(@Param("record") SingerInfo record, @Param("example") SingerInfoExample example);

    int updateByPrimaryKeySelective(SingerInfo record);

    int updateByPrimaryKey(SingerInfo record);

    List<SingerInfo> querySelective(@Param("args") SingerInfoArgs args, @Param("example") SingerInfoExample example);

    int insertSelectiveWithGeneratedKey(SingerInfo singerinfo);

    int insertBatchSelective(List<SingerInfo> records);
}