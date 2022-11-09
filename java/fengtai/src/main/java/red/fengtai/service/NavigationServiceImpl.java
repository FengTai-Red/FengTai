package red.fengtai.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import red.fengtai.entity.Navigation;
import red.fengtai.repository.NavigationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NavigationServiceImpl{

    @Autowired
    private NavigationRepository navigationRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 查询所有Navigation
     * @return
     */
    @Transactional
    public List<Navigation> findAllNavigation() {
        return navigationRepository.findAll();
    }

    /**
     * 翻页查询
     * @param page
     * @param size
     * @return
     */
    public Page<Navigation> findPageableNavigation(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return navigationRepository.findAll(pageable);
    }

    /**
     * 查询一个navigation
     * @return
     */
    public Navigation findOneNavigationById(Long id) {
        return navigationRepository.findById(id).get();
    }

    /**
     * 保存一个navigation
     * @param navigation
     * @return
     */
    public void saveNavigation(Navigation navigation) { 
        logger.info("Request-保存一个网址," + navigation.getName());
        navigationRepository.save(navigation);
    }

    /**
     * 更新navigation
     * @param navigation
     * @return
     */
    public Navigation updateNavigationById(Navigation navigation) {
        logger.info("Request-更新一个网址," + navigation.getName());
        Navigation navigation2 = navigationRepository.findById(navigation.getId()).get();
        BeanUtils.copyProperties(navigation, navigation2);
        return navigationRepository.save(navigation2);
    }

    /**
     * 删除一个navigation
     * @param id
     */
    public void deleteNavigationById(Long id) {
        logger.info("Request-删除一个网址," + id);
        navigationRepository.deleteById(id);
    }

}
