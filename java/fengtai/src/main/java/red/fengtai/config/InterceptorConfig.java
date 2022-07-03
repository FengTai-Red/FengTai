package red.fengtai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import red.fengtai.interceptor.UserInterceptor;

@Configuration//定义此类为配置类
public class InterceptorConfig implements WebMvcConfigurer {

    //这里要这样生成拦截器对象
    @Bean
    public UserInterceptor myInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/admin/**")  // addPathPatterns拦截的路径
                .excludePathPatterns("/login");  // excludePathPatterns排除的路径
    }
}