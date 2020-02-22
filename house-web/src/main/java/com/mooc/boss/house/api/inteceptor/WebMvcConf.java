package com.mooc.boss.house.api.inteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    /** 添加拦截器 **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
        registry.addInterceptor(authActionInterceptor)
                .addPathPatterns("/house/toAdd")
                .addPathPatterns("/accounts/profile").addPathPatterns("/accounts/profileSubmit")
                .addPathPatterns("/house/bookmarked").addPathPatterns("/house/del")
                .addPathPatterns("/house/ownlist").addPathPatterns("/house/add")
                .addPathPatterns("/house/toAdd").addPathPatterns("/agency/agentMsg")
                .addPathPatterns("/comment/leaveComment").addPathPatterns("/comment/leaveBlogComment");
    }

    /** 静态资源处理 **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/id4wea-images/**").addResourceLocations("/WEB-INF/static/");
    }

    /** 这里配置视图解析器 **/
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.jsp("/WEB-INF/view/", ".jsp");
    }


    /**
     * 跨域支持
     * allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                //.exposedHeaders("heard1", "heard2") //暴露哪些消息头
                .maxAge(3600 * 24);
    }


}
