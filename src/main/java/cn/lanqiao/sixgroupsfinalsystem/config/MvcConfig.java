//package cn.lanqiao.sixgroupsfinalsystem.config;
//
//import cn.lanqiao.sixgroupsfinalsystem.interceptor.LoginInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MvcConfig implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                //放行
//                .excludePathPatterns(
//                        "/login.html",
//                        "/css/**",
//                        "/js/**",
//                        "/fonts/**",
//                        "/images/*",
//                        "/*.ico",
//                        "/Six",
//                        "/codeImage"
//                ).order(1);
//    }
//}
