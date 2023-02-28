package red.fengtai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 开启 mvc支持，设置 static 目录为类路径
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${uploadFile.file.root.path}")  // 将 yml 中的自定义配置注入到这里
    private String uploadFile;

    // 得到 classpath 的根路径， resources 目录下的所以路径都可以得到
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("Request : {}");
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + uploadFile);
    }
}
