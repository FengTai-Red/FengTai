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

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findPageableCategory(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findOneCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void saveCategory(Category category) { 
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategoryById(Category category) {
        Category category2 = categoryRepository.findById(category.getId()).get();
        BeanUtils.copyProperties(category, category2);
        return categoryRepository.save(category2);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }


}
