package red.fengtai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import red.fengtai.entity.User;
import red.fengtai.repository.UserRepository;
import red.fengtai.util.MD5Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl{

    @Autowired
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 验证用户
     * @param username
     * @param password
     * @return
     */
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }

    /**
     * 更新权限
     * @param user
     * @return
     */
    public void updateJurisdictionById(Long id, Boolean jurisdiction) {
        logger.info("Request-更新用户状态," + jurisdiction);
        userRepository.updateJurisdictionById(id, jurisdiction);
    }

    /**
     * 验证权限
     * @param id
     * @return
     */
    public User findUserById(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }
    
}
