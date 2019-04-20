package org.tree.ktv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tree.commons.support.mapper.BaseMapper;
import org.tree.ktv.args.FavoriteArgs;
import org.tree.ktv.model.Favorite;
import org.tree.ktv.model.FavoriteExample;

/* 该表没有主键，或数据库驱动版本错误 */
public interface FavoriteMapper extends BaseMapper<Favorite> {
    long countByExample(FavoriteExample example);

    int deleteByExample(FavoriteExample example);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    List<Favorite> selectByExample(FavoriteExample example);

    int updateByExampleSelective(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    int updateByExample(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    List<Favorite> querySelective(@Param("args") FavoriteArgs args, @Param("example") FavoriteExample example);

    int insertBatchSelective(List<Favorite> records);
}