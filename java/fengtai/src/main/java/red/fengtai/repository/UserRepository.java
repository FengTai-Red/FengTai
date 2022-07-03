package red.fengtai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import red.fengtai.entity.User;


@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsernameAndPassword(String username, String password);

    @Modifying  
    @Query(value = "update user set jurisdiction = ?2 where id = ?1", nativeQuery = true)
    void updateJurisdictionById(@Param("id") Long id, @Param("jurisdiction") Boolean jurisdiction);
}
