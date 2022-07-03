package red.fengtai.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID
    private String username;  // 用户名
    private String password;  // 密码
    private Boolean jurisdiction;  // 权限

    public User() {
    }

    public User(Long id, String username, String password, Boolean jurisdiction) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.jurisdiction = jurisdiction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Boolean jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
 
}
