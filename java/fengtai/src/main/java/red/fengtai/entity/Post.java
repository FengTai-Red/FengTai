package red.fengtai.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "post")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID
    private String title;  // 标题
    private String content;  // 内容
    private String firstPicture;  // 首图
    private Integer views;  // 浏览数
    private boolean published;  // 是否为发布状态
    private String description;  // 描述
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;  // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;  // 更新时间
    private String category;  // 分类
    
    public Post() {
    }

    public Post(Long id, String title, String content, String firstPicture, Integer views, boolean published,
            String description, Date createTime, Date updateTime, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.views = views;
        this.published = published;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Post [category=" + category + ", content=" + content + ", createTime=" + createTime + ", description="
                + description + ", firstPicture=" + firstPicture + ", id=" + id + ", published=" + published
                + ", title=" + title + ", updateTime=" + updateTime + ", views=" + views + "]";
    }

}
