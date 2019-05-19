package org.tree.ktv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tree.commons.annotation.Ignore;
import org.tree.commons.support.controller.Result;
import org.tree.commons.utils.PictureCode;
import org.tree.commons.utils.PropertiesUtils;
import org.tree.commons.utils.VerifyUtils;
import org.tree.ktv.model.User;
import org.tree.ktv.service.UserService;
import org.tree.ktv.vo.LoginVO;
import org.tree.ktv.vo.RegisterVO;
import org.tree.ktv.vo.ResetPasswordVO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author er_dong_chen
 * @date 2019/3/14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /* 登陆 */
    @RequestMapping("/login.do")
    public Result login(@Valid LoginVO loginVO,
                        @Ignore BindingResult bindingResult,
                        @Ignore HttpServletResponse response,
                        @Ignore HttpSession session) throws UnsupportedEncodingException {

        // 非空检查
        if (bindingResult.hasErrors())
            return new Result(VerifyUtils.getErrorString(bindingResult));

        // 验证码检查
        if (!loginVO.getPictureCode().equals(session.getAttribute("pictureCode")))
            return new Result(Result.AUTH_CODE_ERROR);

        // 密码检查
        User user = null;
        if (loginVO.getAdmin()) {
            Properties properties = PropertiesUtils.getProperties("admin.properties");
            if (!(loginVO.getUsername().equals(properties.getProperty("username")) &&
                    loginVO.getPassword().equals(properties.getProperty("password"))))
                return new Result(Result.PASSWORD_ERROR);

            user = new User();
            user.setId(Long.parseLong(properties.getProperty("id")));
            user.setUsername(properties.getProperty("username"));
        } else {
            user = userService.login(loginVO.getUsername(), loginVO.getPassword());
            if (user == null)
                return new Result(Result.PASSWORD_ERROR);
        }

        // 登陆成功
        session.setAttribute("user", user);
        Cookie cookie = new Cookie("username",
                new String(user.getUsername().getBytes("utf8"), "iso-8859-1"));
        cookie.setPath("/");
        response.addCookie(cookie);
        return new Result(true);
    }

    /* 退出登陆 */
    @RequestMapping("/logout.do")
    public Result logout(@Ignore HttpSession session) {
        session.invalidate();
        return new Result(true);
    }

    /* 登陆测试 */
    @RequestMapping("/loginTest.do")
    public Result loginTest(@Ignore HttpSession session) {
        return new Result(session.getAttribute("user") != null);
    }

    /* 注册 */
    @RequestMapping("/register.do")
    public Result register(@Valid RegisterVO registerVO,
                           @Ignore BindingResult bindingResult,
                           @Ignore HttpSession session) {

        // 非空检查
        if (bindingResult.hasErrors())
            return new Result(VerifyUtils.getErrorString(bindingResult));

        // 验证码检查
        if (!registerVO.getPictureCode().equals(session.getAttribute("pictureCode")))
            return new Result(Result.AUTH_CODE_ERROR);

        if ("admin".equals(registerVO.getUsername()))
            return new Result("不能注册管理员账户");

        String msg = userService.register(registerVO);
        return msg == null ? new Result(true) : new Result(msg);
    }

    /* 重置密码 */
    @RequestMapping("/resetPassword.do")
    public Result resetPassword(@Valid ResetPasswordVO resetPasswordVO,
                                @Ignore BindingResult bindingResult,
                                @Ignore HttpSession session) {

        // 非空检查
        if (bindingResult.hasErrors())
            return new Result(VerifyUtils.getErrorString(bindingResult));

        // 验证码检查
        if (!resetPasswordVO.getPictureCode().equals(session.getAttribute("pictureCode")))
            return new Result(Result.AUTH_CODE_ERROR);

        String msg = userService.resetPassword(resetPasswordVO);
        return msg == null ? new Result(true) : new Result(msg);
    }

    /* 获取图片验证码 */
    @RequestMapping("/getPictureCode.do")
    public void getPictureCode(@Ignore HttpServletResponse response, @Ignore HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        session.setAttribute("pictureCode", PictureCode.generate(response.getOutputStream()));
    }
}
