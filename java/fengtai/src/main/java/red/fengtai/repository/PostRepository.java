package red.fengtai.repository;

import red.fengtai.entity.Post;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    /**
     * 按category查询
     * @param category
     * @return
     */
    List<Post> findByCategory(String category);

    /**
     * 根据published查询
     * @param published
     * @return
     */
    Page<Post> findByPublished(boolean published, Pageable pageable);

    /**
     * 按category和published查询
     * @param category
     * @param published
     * @return
     */
    List<Post> findByCategoryAndPublished(String category, boolean published);

}
