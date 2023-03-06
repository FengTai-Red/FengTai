package red.fengtai.controller;

import java.util.List;
import java.io.IOException;

import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import red.fengtai.entity.Tool;
import red.fengtai.entity.ToolResult;
import red.fengtai.service.ToolServiceImpl;

@Api(value = "文件上传，下载相关功能")
@RestController
@RequestMapping
public class ToolController {
    
    @Autowired
    private ToolServiceImpl toolService;

    /**
     * 查询所有Tool
     * @return
     */
    @GetMapping("/tool/findAll")
    public List<Tool> findAllTool(){
        return toolService.findAllTool();
    }

    /**
     * 翻页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/tool/page/{page}/{size}")
    public Page<Tool> findPageable(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return toolService.findPageableTool(page, size);
    }

    /**
     * 根据id查询tool
     * @param id
     * @return
     */
    @GetMapping("/tool/{id}")
    public Tool findOneToolById(@PathVariable("id") Long id){
        return toolService.findOneToolById(id);
    }

    /**
     * 更新tool
     * @param tool
     * @return
     */
    @PutMapping("/admin/tool/updateTool")
    public String updateToolById(@RequestBody Tool tool){
        Tool tool2 = toolService.updateToolById(tool);
        if (tool2 != null){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 删除一个tool
     * @param id
     */
    @DeleteMapping("/admin/tool/deleteTool/{id}")
    public void deleteTool(@PathVariable("id") Long id){
        toolService.deleteToolById(id);
    }

    /**
     * 保存一个tool
     * @param tool
     * @return
     */
    @PostMapping("/admin/tool/save")
    public String savaUser(@RequestBody Tool tool){
        if (tool != null){
            toolService.saveTool(tool);
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 下载tool
     * @param request
     * @param response
     * @param id
     * @return
     */
    @GetMapping("/tool/download/{id}")
    public String downloadFile(HttpServletResponse response, @PathVariable("id") Long id) {
       return toolService.downloadFile(response, id);
    }

    /**
     * 文件上传 （可以多文件上传）
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/admin/tool/upload")
    public ToolResult fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        return toolService.fileUploads(request, file);
    }

}
