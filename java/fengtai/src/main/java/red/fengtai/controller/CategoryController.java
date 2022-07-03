package red.fengtai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import red.fengtai.entity.Category;
import red.fengtai.service.CategoryService;

/**
 * 分类管理
 */
@RestController
@RequestMapping
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询全部
     * @return
     */
    @GetMapping("/category/findAll")
    public List<Category> findAllPost() {
        return categoryService.findAll();
    }

    /**
     * 翻页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/category/page/{page}/{size}")
    public Page<Category> findPageable(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return categoryService.findPageableCategory(page, size);
    }

    /**
     * 根据id查询category
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    public Category findOneCategoryById(@PathVariable("id") Long id){
        return categoryService.findOneCategoryById(id);
    }

    /**
     * 更新category
     * @param category
     * @return
     */
    @PutMapping("/admin/category/updateCategory")
    public String updateCategoryById(@RequestBody Category category){
        Category category2 = categoryService.updateCategoryById(category);
        if (category2 != null){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 删除一个category
     * @param id
     */
    @DeleteMapping("/admin/category/deleteCategory/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
    }

    /**
     * 保存一个category
     * @param category
     * @return
     */
    @PostMapping("/admin/category/save")
    public String savaUser(@RequestBody Category category){
        if (category != null){
            categoryService.saveCategory(category);
            return "success";
        }else{
            return "error";
        }
    }

}
