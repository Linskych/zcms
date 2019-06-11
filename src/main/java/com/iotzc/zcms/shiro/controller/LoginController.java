package com.iotzc.zcms.shiro.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iotzc.zcms.util.CaptchaGenerator;
import com.iotzc.zcms.util.Result;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    private final static String KEY_CAPTCHA = "CAPTCHA";
    
    @RequestMapping("/login")
    public String toLoginPage() {
        return "login";
    }
    
    @RequestMapping("/home")
    public String toHomePage() {
        return "index";
    }
    
    @RequestMapping("/check")
    @ResponseBody
    public String check(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        log.info(userName + "checking...");
        String sc = (String)SecurityUtils.getSubject().getSession().getAttribute(KEY_CAPTCHA);
        if (StringUtils.isEmpty(captcha) || !captcha.equalsIgnoreCase(sc)) {
            return Result.jsonFail("验证码错误");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            e.printStackTrace();
            return Result.jsonFail("用户名或密码错误");
        } catch (ShiroException se) {
            se.printStackTrace();
            return Result.jsonFail("登入异常");
        }
        return Result.jsonSucc("登入成功");
    }
    
    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        log.info("getCaptcha:{}", "request");
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        
        HttpSession session = request.getSession();
        StringBuilder code = new StringBuilder();
        BufferedImage image = CaptchaGenerator.genRandomCodeImage(code);
        
        session.removeAttribute(KEY_CAPTCHA);
        session.setAttribute(KEY_CAPTCHA, code.toString());
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
