package red.fengtai.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import red.fengtai.entity.Tool;
import red.fengtai.entity.ToolResult;

public interface ToolService {

    /**
     * 查询所有Tool
     * @return
     */
    public List<Tool> findAllTool();
    
    /**
     * 翻页查询数据
     * @param page 页数
     * @param size 每页数量
     * @return
     */
    Page<Tool> findPageableTool(Integer page, Integer size);

    /**
     * 查询一个tool
     * @return
     */
    Tool findOneToolById(Long id);

    /**
     * 保存一个tool
     * @param tool
     * @return
     */
    void saveTool(Tool tool);

    /**
     * 更新tool
     * @param tool
     * @return
     */
    Tool updateToolById(Tool tool);

    /**
     * 删除一个tool
     * @param id
     */
    void deleteToolById(Long id);

    /**
     * 下载文件
     * @param response
     * @param id
     * @return
     */
    String downloadFile(HttpServletResponse response, Long id);

    /**
     * 上传文件
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    ToolResult fileUploads(HttpServletRequest request, MultipartFile file) throws IOException;
}
