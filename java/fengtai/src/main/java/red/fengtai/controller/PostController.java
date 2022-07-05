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

import red.fengtai.entity.Post;
import red.fengtai.service.PostService;
/**
 * 首页
 */
@RestController
@RequestMapping
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 全部
     * @return
     */
    @GetMapping("/post/findAll")
    public List<Post> findAllPost() {
        return postService.findAllPost();
    }

    /**
     * 根据id查询post
     * @param id
     * @return
     */
    @GetMapping("/post/{id}")
    public Post findOnePostById(@PathVariable("id") Long id){
        return postService.findOnePostById(id);
    }

    /**
     * 翻页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/post/page/{page}/{size}")
    public Page<Post> findPageable(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return postService.findPageablePost(page, size);
    }

    /**
     * 查询published为ture的post
     * @return
     */
    @GetMapping("/post/published/page/{page}/{size}")
    public Page<Post> findPageablePostByPublished(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return postService.findPageablePostByPublished(page, size);
    }

    /**
     * 更新post
     * @param post
     * @return
     */
    @PutMapping("/admin/post/updatePost")
    public String updatePostById(@RequestBody Post post){
        Post post2 = postService.updatePostById(post);
        if (post2 != null){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 删除一个post
     * @param id
     */
    @DeleteMapping("/admin/post/deletePost/{id}")
    public void deletePost(@PathVariable("id") Long id){
        postService.deletePostById(id);
    }

    /**
     * 保存一个post
     * @param post
     * @return
     */
    @PostMapping("/admin/post/save")
    public String savaUser(@RequestBody Post post){
        if (post != null){
            postService.savePost(post);
            return "success";
        }else{
            return "error";
        }
    }


}
