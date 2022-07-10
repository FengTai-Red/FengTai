package red.fengtai.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import red.fengtai.entity.User;
import red.fengtai.service.UserService;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userService.findUserById(1L);
        System.out.println(user.toString());
        boolean jurisdiction = user.getJurisdiction();
        //判断用户是否登录
        if (jurisdiction){
            return true;
        }
        logger.info("没权限的 : {}");
        return false;
    }
}