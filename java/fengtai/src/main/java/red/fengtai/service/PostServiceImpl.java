package red.fengtai.service;

import java.util.List;
import java.util.Date;

import red.fengtai.entity.Post;
import red.fengtai.repository.PostRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private PostRepository postRepository;

    @Transactional
    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findPageablePost(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return postRepository.findAll(pageable);
    }

    @Override
    public void savePost(Post post) {
        if (post.getId() == null){
            post.setCreateTime(new Date());
            post.setUpdateTime(new Date());
            post.setViews(0);
        }else {
            post.setCreateTime(new Date());
        }
        postRepository.save(post);
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
    public List<Post> findByCategoryAndPublished(String category, boolean published) {
        return postRepository.findByCategoryAndPublished(category, published);
    }

    @Override
    public List<Post> findByPublished(boolean published) {
        return postRepository.findByPublished(published);
    }

}
