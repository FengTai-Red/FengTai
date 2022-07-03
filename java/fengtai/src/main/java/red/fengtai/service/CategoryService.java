package red.fengtai.service;

import java.util.List;

import org.springframework.data.domain.Page;

import red.fengtai.entity.Category;

public interface CategoryService {

    /**
     * 查询所有数据
     * @return
     */
    List<Category> findAll();

    /**
     * 翻页查询
     * @param page
     * @param size
     * @return
     */
    Page<Category> findPageableCategory(Integer page, Integer size);

    /**
     * 查询一个category
     * @return
     */
    Category findOneCategoryById(Long id);

    /**
     * 保存一个category
     * @param category
     * @return
     */
    void saveCategory(Category category);

    /**
     * 更新category
     * @param category
     * @return
     */
    Category updateCategoryById(Category category);

    /**
     * 删除一个category
     * @param id
     */
    void deleteCategoryById(Long id);

}
