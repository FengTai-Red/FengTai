package red.fengtai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import red.fengtai.entity.Navigation;
import red.fengtai.service.NavigationServiceImpl;

@RestController
@RequestMapping
public class NavigationController {
    
    
    @Autowired
    private NavigationServiceImpl navigationService;

    /**
     * 查询所有Navigation
     * @return
     */
    @GetMapping("/navigation/findAll")
    public List<Navigation> findAllNavigation(){
        return navigationService.findAllNavigation();
    }

    /**
     * 翻页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/navigation/page/{page}/{size}")
    public Page<Navigation> findPageable(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return navigationService.findPageableNavigation(page, size);
    }

    /**
     * 根据id查询navigation
     * @param id
     * @return
     */
    @GetMapping("/navigation/{id}")
    public Navigation findOneNavigationById(@PathVariable("id") Long id){
        return navigationService.findOneNavigationById(id);
    }

    /**
     * 更新navigation
     * @param navigation
     * @return
     */
    @PutMapping("/admin/navigation/updateNavigation")
    public String updateNavigationById(@RequestBody Navigation navigation){
        Navigation navigation2 = navigationService.updateNavigationById(navigation);
        if (navigation2 != null){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 删除一个navigation
     * @param id
     */
    @DeleteMapping("/admin/navigation/deleteNavigation/{id}")
    public void deleteNavigation(@PathVariable("id") Long id){
        navigationService.deleteNavigationById(id);
    }

    /**
     * 保存一个navigation
     * @param navigation
     * @return
     */
    @PostMapping("/admin/navigation/save")
    public String savaUser(@RequestBody Navigation navigation){
        if (navigation != null){
            navigationService.saveNavigation(navigation);
            return "success";
        }else{
            return "error";
        }
    }

}
