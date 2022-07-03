package red.fengtai.service;

import red.fengtai.entity.User;

public interface UserService {

    /**
     * 验证用户
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);

    /**
     * 更新权限
     * @param user
     * @return
     */
    void updateJurisdictionById(Long id, Boolean jurisdiction);

    /**
     * 验证权限
     * @param id
     * @return
     */
    User findUserById(Long id);
}
