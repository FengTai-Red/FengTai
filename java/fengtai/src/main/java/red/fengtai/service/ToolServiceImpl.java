package red.fengtai.service;

import java.util.List;
import java.lang.Thread;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

import red.fengtai.entity.Tool;
import red.fengtai.entity.ToolResult;
import red.fengtai.repository.ToolRepository;

@Service
public class ToolServiceImpl implements ToolService{

    private String newFileName;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日_HH时mm分ss秒");  // 设置固定的日期格式
    @Autowired
    private ToolRepository toolRepository;
    @Value("${gorit.file.root.path}")  // 将 yml 中的自定义配置注入到这里
    private String filePath;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public List<Tool> findAllTool() {
        return toolRepository.findAll();
    }

    @Override
    public Page<Tool> findPageableTool(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return toolRepository.findAll(pageable);
    }

    @Override
    public Tool findOneToolById(Long id) {
        return toolRepository.findById(id).get();
    }

    @Override
    public void saveTool(Tool tool) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            i = i + 1;
            if (newFileName != null) {
                break;
            }
        }
        tool.setPath(newFileName);
        toolRepository.save(tool);
    }

    @Override
    public Tool updateToolById(Tool tool) {
        Tool tool2 = toolRepository.findById(tool.getId()).get();
        tool.setPath(newFileName);
        BeanUtils.copyProperties(tool, tool2);
        return toolRepository.save(tool2);
    }

    @Override
    public void deleteToolById(Long id) {
        Tool tool = findOneToolById(id);
        String fileName = tool.getPath();  // 文件名
        if (fileName != null) {
            File file = new File(filePath + fileName);
            file.delete();
            toolRepository.deleteById(id);
            logger.info("删除文件：" + filePath + fileName);
        } else {
            logger.info("删除的文件文件不存在");
        }
    }

    @Override
    public String downloadFile(HttpServletResponse response, Long id) {
        Tool tool = toolRepository.findById(id).get();
        String fileName = tool.getPath();  // 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath + fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @Override
    public ToolResult fileUploads(HttpServletRequest request, MultipartFile file) throws IOException {
        // 得到格式化后的日期
        String format = simpleDateFormat.format(new Date());
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        // 时间 和 日期拼接
        newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            // 打印日志
            logger.info("上传文件：" + filePath + newFileName);
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            return ToolResult.succ("上传成功");
        } catch (IOException e) {
            System.err.println("上传失败：" + e.toString());
        }
        // 待完成 —— 文件类型校验工作

        return ToolResult.fail("上传错误");
    }
}
