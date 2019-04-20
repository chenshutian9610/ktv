package org.tree.ktv.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tree.commons.support.mapper.MapperMap;

import javax.annotation.PostConstruct;

@Component
public class LocalMapperMap extends MapperMap {
    @Autowired
    UserMapper userMapper;

    @Autowired
    SongInfoMapper songInfoMapper;

    @Autowired
    FavoriteMapper favoriteMapper;

    @Autowired
    SingerInfoMapper singerInfoMapper;

    @PostConstruct
    public void init() {
        put("User", userMapper);
        put("SongInfo", songInfoMapper);
        put("Favorite", favoriteMapper);
        put("SingerInfo", singerInfoMapper);
    }
}