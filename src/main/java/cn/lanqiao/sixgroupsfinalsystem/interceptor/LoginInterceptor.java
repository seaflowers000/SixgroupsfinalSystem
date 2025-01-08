//package cn.lanqiao.sixgroupsfinalsystem.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.thymeleaf.util.StringUtils;
//
//import java.util.concurrent.TimeUnit;
//import static cn.lanqiao.sixgroupsfinalsystem.model.common.FinalClass.USER_TOKEN;
//
//
//
//@Component
//@Slf4j
//public class LoginInterceptor implements HandlerInterceptor {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        log.info("拦截所有:{}", requestURI);
//        if (isStaticResource(requestURI)) {
//            return true;
//        }
//        //先session拿到登录成功的session值
//        String token=request.getHeader("token");
//        if (StringUtils.isEmpty(token)){
//            return false;
//        }
//        String redisKey=USER_TOKEN+token;
//        String userInfo=stringRedisTemplate.opsForValue().get(redisKey);
//        if (StringUtils.isEmpty(userInfo)){
//            return false;
//        }
//        stringRedisTemplate.expire(redisKey,30L, TimeUnit.MINUTES);
//        return true;
//    }
//    private boolean isStaticResource(String uri) {
//        return uri.startsWith("/css/") ||
//                uri.startsWith("/js/") ||
//                uri.startsWith("/images/") ||
//                uri.startsWith("/fonts/") ||
//                uri.startsWith("/files/") ||
//                uri.startsWith("/error") ||
//                uri.startsWith("/pages") ||
//                uri.startsWith("/lib/") ||
//                uri.startsWith("/static/") ||
//                uri.startsWith("/Six/") ||
//                uri.startsWith("/mang/") ||
//                uri.startsWith("/codeImage/") ||
//                uri.endsWith(".html") ||
//                uri.endsWith(".ico");
//    }
//}
