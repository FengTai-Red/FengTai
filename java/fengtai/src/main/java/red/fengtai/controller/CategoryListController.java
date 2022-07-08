package red.fengtai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import red.fengtai.entity.Post;
import red.fengtai.service.PostService;

/**
 * 分类管理
 */
@RestController
@RequestMapping
public class CategoryListController {

    @Autowired
    private PostService postService;

    // /**
    //  * 根据category查询post
    //  * @param category
    //  * @return
    //  */
    // @GetMapping("/categoryList/{category}")
    // public List<Post> findPostByCategory(@PathVariable("category") String category){
    //     return postService.findPostByCategory(category);
    // }

    /**
     * 根据category查询published为ture的post
     * @param category
     * @return
     */
    @GetMapping("/categoryList/published/{category}/page/{page}/{size}")
    public Page<Post> findByCategoryAndPublished(@PathVariable("category") String category, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return postService.findByCategoryAndPublished(category, page, size);
    }
}
