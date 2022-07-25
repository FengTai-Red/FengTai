package red.fengtai.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;

import red.fengtai.entity.ImgResult;
import red.fengtai.entity.Post;
import red.fengtai.repository.PostRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private PostRepository postRepository;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");  // 设置固定的日期格式
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${img.file.root.path}")  // 将 yml 中的自定义配置注入到这里
    private String filePath;
    private String newFileName;

    @Transactional
    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findPageablePost(Integer page, Integer size) {
        Order order = new Order(Direction.DESC,"createTime");
        Sort sort = Sort.by(order,order);
        Pageable pageable = PageRequest.of(page-1, size, sort);
        return postRepository.findAll(pageable);
    }

    @Override
    public void savePost(Post post) {
        if (post.getId() == null){
            post.setCreateTime(new Date());
            post.setUpdateTime(new Date());
            post.setViews(0);
        }else {
            post.setUpdateTime(new Date());
        }
        postRepository.save(post);
    }

    @Override
    public ImgResult postImgUploads(HttpServletRequest request, MultipartFile file) throws IOException {
        // 得到格式化后的日期
        String format = simpleDateFormat.format(new Date());
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        // 时间 和 日期拼接
        newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        ImgResult imgResult = new ImgResult();
        PostServiceImpl postServiceImpl = new PostServiceImpl();
        String url = postServiceImpl.showPostImg(newFileName);
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            // 打印日志
            logger.info("Request : 文件上传：" + newFileName);
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            return imgResult.success(url);
        } catch (IOException e) {
            System.err.println("上传失败：" + e.toString());
        }
        // 待完成 —— 文件类型校验工作
        return imgResult.error(url);
    }

    @Override
    public String showPostImg(String fileName){
        String url = "http://127.0.0.1:8181/img/" + fileName;
        return url;
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post updatePostById(Post post) {
        Post post2 = postRepository.findById(post.getId()).get();
        BeanUtils.copyProperties(post, post2);
        return postRepository.save(post2);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);    
    }

    @Override
    public List<Post> findPostByCategory(String category) {
        return postRepository.findByCategory(category);
    }

    @Override
    public Post findOnePostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Page<Post> findByCategoryAndPublished(String category, Integer page, Integer size) {
        boolean published = true;
        Order order = new Order(Direction.DESC,"createTime");
        Sort sort = Sort.by(order,order);
        Pageable pageable = PageRequest.of(page-1, size, sort);
        return postRepository.findByCategoryAndPublished(category, published, pageable);
    }

    @Override
    public Page<Post> findPageablePostByPublished(Integer page, Integer size) {
        boolean published = true;
        Order order = new Order(Direction.DESC,"createTime");
        Sort sort = Sort.by(order,order);
        Pageable pageable = PageRequest.of(page-1, size, sort);
        return postRepository.findByPublished(published, pageable);

    }

}
