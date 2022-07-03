package red.fengtai.service;

import java.util.List;

import red.fengtai.entity.Navigation;

import org.springframework.data.domain.Page;

public interface NavigationService {
    
    /**
     * 查询所有Navigation
     * @return
     */
    List<Navigation> findAllNavigation();

    /**
     * 翻页查询
     * @param page
     * @param size
     * @return
     */
    Page<Navigation> findPageableNavigation(Integer page, Integer size);

    /**
     * 查询一个navigation
     * @return
     */
    Navigation findOneNavigationById(Long id);

    /**
     * 保存一个navigation
     * @param navigation
     * @return
     */
    void saveNavigation(Navigation navigation);

    /**
     * 更新navigation
     * @param navigation
     * @return
     */
    Navigation updateNavigationById(Navigation navigation);

    /**
     * 删除一个navigation
     * @param id
     */
    void deleteNavigationById(Long id);
}
