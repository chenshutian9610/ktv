package org.tree.ktv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tree.commons.support.mapper.IntegratedMapper;
import org.tree.commons.utils.MD5;
import org.tree.ktv.model.User;
import org.tree.ktv.model.UserExample;
import org.tree.ktv.vo.RegisterVO;
import org.tree.ktv.vo.ResetPasswordVO;

import java.util.Date;
import java.util.List;

/**
 * @author er_dong_chen
 * @date 2019/3/14
 */
@Service
@Transactional
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IntegratedMapper mapper;

    public User login(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = mapper.selectByExample(userExample);
        return users.size() == 0 ? null : users.get(0);
    }

    public String register(RegisterVO registerVO) {
        if (existsName(registerVO.getUsername()))
            return "该用户名已被占用！";
        User user = registerVO.parseUser();
        user.setRegisterDate(new Date());
        user.setPassword(MD5.wrap(user.getPassword()));
        return mapper.insertSelective(user) == 1 ? null : "个人信息错误！";
    }

    public boolean existsName(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        long count = mapper.countByExample(userExample);

        // 不影响程序运行
        if (count > 0 && count != 1)
            if (logger.isErrorEnabled())
                logger.error(String.format("用户表存在 %s 个同名记录", count));

        return count > 0 ? true : false;
    }

    public String resetPassword(ResetPasswordVO resetPasswordVO) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(resetPasswordVO.getUsername())
                .andRealNameEqualTo(resetPasswordVO.getRealName())
                .andUserSexEqualTo(resetPasswordVO.getUserSex())
                .andBirthdayEqualTo(resetPasswordVO.getBirthday());

        User user = new User();
        user.setPassword(MD5.wrap(resetPasswordVO.getPassword()));
        return mapper.updateByExampleSelective(user, userExample) == 1 ? null : "个人信息错误！";
    }
}
