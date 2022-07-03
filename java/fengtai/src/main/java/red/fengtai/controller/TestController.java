package red.fengtai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试用
 */
@RestController
@RequestMapping
public class TestController {
    
    /**
     * 测试
     * @return
     */
    @GetMapping("/test")
    public String exception(){
        return "这里是测试A";
    }
    /**
     * 测试
     * @return
     */
    @GetMapping("/test/{num}")
    public String exception(@PathVariable("num") Long num){
        return "这里是测试B，传入了参数：" + num;
    }
}