package red.fengtai.service;

import java.util.List;

import red.fengtai.entity.Post;

import org.springframework.data.domain.Page;

/**
 * 文章
 */
public interface PostService {
    
    /**
     * 查询所有数据
     * @return
     */
    List<Post> findAllPost();

    /**
     * 翻页查询数据
     * @param page 页数
     * @param size 每页数量
     * @return
     */
    Page<Post> findPageablePost(Integer page, Integer size);

    /**
     * 根据id查找一个post
     * @return
     */
    Post findOnePostById(Long id);

    /**
     * 根据category查询
     * @param category
     * @return
     */
    List<Post> findPostByCategory(String category);

    /**
     * 按category和published查询
     * @param category
     * @param published
     * @return
     */
    List<Post> findByCategoryAndPublished(String category, boolean published);

    /**
     * 根据published分页查询
     * @param category
     * @return
     */
    Page<Post> findPageablePostByPublished(Integer page, Integer size);

    /**
     * 保存一个Post
     * @param post
     */
    void savePost(Post post);

    /**
     * 根据ID查询Post
     * @param id
     */
    Post findPostById(Long id);

    /**
     * 更新Post
     * @param post
     */
    Post updatePostById(Post post);

    /**
     * 删除Post
     * @param post
     */
    void deletePostById(Long id);
}
