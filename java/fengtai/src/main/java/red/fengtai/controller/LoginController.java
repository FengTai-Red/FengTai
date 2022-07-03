package red.fengtai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import red.fengtai.entity.User;
import red.fengtai.service.UserService;

/**
 * 登录
 */
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 保存一个user
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String savaUser(@RequestBody User user){
        User user2 = userService.checkUser(user.getUsername(), user.getPassword());
        if (user2 != null){
            userService.updateJurisdictionById(1L, true);
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * 注销
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        User user = new User();
        user.setId(1L);
        userService.updateJurisdictionById(1L, false);
        return "success";
    }
}
