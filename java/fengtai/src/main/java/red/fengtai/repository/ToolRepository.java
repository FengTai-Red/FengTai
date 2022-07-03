package red.fengtai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import red.fengtai.entity.Tool;

public interface ToolRepository extends JpaRepository<Tool, Long>{
    
}
