package red.fengtai.service;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import red.fengtai.entity.ImgResult;
import red.fengtai.entity.Post;
import red.fengtai.entity.ToolResult;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

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
    Page<Post> findByCategoryAndPublished(String category, Integer page, Integer size);

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
     * 文章图片上传
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    ImgResult postImgUploads(HttpServletRequest request, MultipartFile file) throws IOException;

    /**
     * 显示文章图片
     * @param fileName
     * @return
     */
    String showPostImg(String fileName);

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
