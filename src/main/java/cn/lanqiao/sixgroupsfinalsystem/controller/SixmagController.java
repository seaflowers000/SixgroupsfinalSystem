package cn.lanqiao.sixgroupsfinalsystem.controller;

import cn.hutool.core.util.IdUtil;

import cn.lanqiao.sixgroupsfinalsystem.model.dto.SixmagLogin;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import cn.lanqiao.sixgroupsfinalsystem.service.SixmagService;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import static cn.lanqiao.sixgroupsfinalsystem.model.common.FinalClass.MOBILE_PHONE_CAPTCHA_PREFIX;
import static cn.lanqiao.sixgroupsfinalsystem.model.common.FinalClass.USER_TOKEN;


@RestController
@RequestMapping("/Six")
public class SixmagController {
    @Autowired
    private SixmagService SixmagService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final ObjectMapper mapper=new ObjectMapper();
    //注册
//    @RequestMapping("/register")
//    public ResponseUtils<String> register(@RequestBody Manager mag, HttpServletRequest request){
//        try {
//            HttpSession session = request.getSession();
//            String verificationCode=(String) session.getAttribute("verificationcode");
//            String userCode = mag.getCode();
//            if (verificationCode.equalsIgnoreCase(userCode)){
//                int result = SixmagService.register(mag);
//                return (result == 1) ? new ResponseUtils<String>(200, "注册成功") : new ResponseUtils<String>(500, "注册失败");
//            }else{
//                return new ResponseUtils<String>(500,"验证码错误");
//            }
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return  new ResponseUtils<String>(400,"数据注册异常");
//        }
//    }
    //登录
    @RequestMapping("/login")
        public ResponseUtils<MagVO> login(@RequestBody SixmagLogin loginForm, HttpServletResponse response) {
            try {
                // 调用service层验证登录
                Manager manager = SixmagService.login(loginForm);

                if (manager != null) {
                    // 转换为MagVO对象
                    MagVO magVO = new MagVO();
                    magVO.setId(manager.getId());
                    magVO.setLoginName(manager.getLoginName());
                    magVO.setRole(manager.getRole());
                    magVO.setStatus(manager.getStatus());
                    // 不设置密码，保证安全性

                    // 如果用户选择了"记住我"
                    if (loginForm.isRemember()) {
                        // 创建cookie存储用户名和密码
                        Cookie usernameCookie = new Cookie("uname",
                                URLEncoder.encode(loginForm.getLoginName(), "UTF-8"));
                        Cookie passwordCookie = new Cookie("pword",
                                URLEncoder.encode(loginForm.getPassword(), "UTF-8"));
                        Cookie rememberCookie = new Cookie("rememberMe", "true");

                        // 设置cookie过期时间为7天
                        int maxAge = 7 * 24 * 60 * 60;
                        usernameCookie.setMaxAge(maxAge);
                        passwordCookie.setMaxAge(maxAge);
                        rememberCookie.setMaxAge(maxAge);

                        // 设置cookie路径
                        usernameCookie.setPath("/");
                        passwordCookie.setPath("/");
                        rememberCookie.setPath("/");

                        // 添加cookie到响应
                        response.addCookie(usernameCookie);
                        response.addCookie(passwordCookie);
                        response.addCookie(rememberCookie);
                    }

                    return new ResponseUtils<>(200, "登录成功", magVO);
                } else {
                    return new ResponseUtils<>(401, "用户名或密码错误", null);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseUtils<>(500, "登录异常", null);
            }
        }



}




