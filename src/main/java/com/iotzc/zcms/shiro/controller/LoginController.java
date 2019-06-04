package com.iotzc.zcms.shiro.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iotzc.zcms.util.CaptchaGenerator;
import com.iotzc.zcms.util.Result;

@Controller
public class LoginController {

    private final static String KEY_CAPTCHA = "CAPTCHA";
    
    @RequestMapping("/login")
    public String toLoginPage() {
        System.out.println("page.......");
        return "login";
    }
    
    @RequestMapping("/check")
    public String login(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        String sc = (String)SecurityUtils.getSubject().getSession().getAttribute(KEY_CAPTCHA);
        if (StringUtils.isEmpty(captcha) || !captcha.equalsIgnoreCase(sc)) {
            return Result.jsonFail("验证码错误");
        }
        return "";
    }
    
    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
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
