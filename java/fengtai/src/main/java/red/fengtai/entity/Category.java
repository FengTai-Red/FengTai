package red.fengtai.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID
    private String name;  // 分类名
    private String description;  // 简介
    private String icon;  // 图标
    private String background; // 背景

    public Category() {
    }

    public Category(Long id, String name, String description, String icon, String background) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.background = background;
    }

    @Override
    public String toString() {
        return "Category [background=" + background + ", description=" + description + ", icon=" + icon + ", id=" + id
                + ", name=" + name + "]";
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    
}
