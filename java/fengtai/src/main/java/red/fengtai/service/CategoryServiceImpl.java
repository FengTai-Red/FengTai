package red.fengtai.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import red.fengtai.entity.Category;
import red.fengtai.repository.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CategoryServiceImpl{

    @Autowired
    private CategoryRepository categoryRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 查询所有数据
     * @return
     */
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * 翻页查询
     * @param page
     * @param size
     * @return
     */
    public Page<Category> findPageableCategory(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return categoryRepository.findAll(pageable);
    }

    /**
     * 查询一个category
     * @return
     */
    public Category findOneCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    /**
     * 保存一个category
     * @param category
     * @return
     */
    public void saveCategory(Category category) { 
        logger.info("Request-新增一个分类," + category.getName());
        categoryRepository.save(category);
    }

    /**
     * 更新category
     * @param category
     * @return
     */
    public Category updateCategoryById(Category category) {
        logger.info("Request-更新分类," + category.getName());
        Category category2 = categoryRepository.findById(category.getId()).get();
        BeanUtils.copyProperties(category, category2);
        return categoryRepository.save(category2);
    }

    /**
     * 删除一个category
     * @param id
     */
    public void deleteCategoryById(Long id) {
        logger.info("Request-删除分类," + id);
        categoryRepository.deleteById(id);
    }


}
