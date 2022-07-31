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
public class NavigationServiceImpl implements NavigationService{

    @Autowired
    private NavigationRepository navigationRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public List<Navigation> findAllNavigation() {
        return navigationRepository.findAll();
    }

    @Override
    public Page<Navigation> findPageableNavigation(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return navigationRepository.findAll(pageable);
    }

    @Override
    public Navigation findOneNavigationById(Long id) {
        return navigationRepository.findById(id).get();
    }

    @Override
    public void saveNavigation(Navigation navigation) { 
        logger.info("Request-保存一个网址," + navigation.getName());
        navigationRepository.save(navigation);
    }

    @Override
    public Navigation updateNavigationById(Navigation navigation) {
        logger.info("Request-更新一个网址," + navigation.getName());
        Navigation navigation2 = navigationRepository.findById(navigation.getId()).get();
        BeanUtils.copyProperties(navigation, navigation2);
        return navigationRepository.save(navigation2);
    }

    @Override
    public void deleteNavigationById(Long id) {
        logger.info("Request-删除一个网址," + id);
        navigationRepository.deleteById(id);
    }

}
