package red.fengtai.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tool")
public class Tool {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID
    private String name;  // 工具名
    private String description;  // 简介
    private String path;  // 下载地址

    public Tool() {
    }

    public Tool(Long id, String name, String description, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Tool [description=" + description + ", id=" + id + ", name=" + name + ", path=" + path + "]";
    }

    
}
