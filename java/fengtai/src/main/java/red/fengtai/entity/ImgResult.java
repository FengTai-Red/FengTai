package red.fengtai.entity;

import java.util.HashMap;
import java.util.Map;


/**
 * Post文件上传用的class
 * 一个通用返回结果集，一般情况下返回的数据格式如下
 * {
 *     "errno": 0, // 注意：值是数字，不能是字符串
 *     "data": {
 *         "url": "xxx", // 图片 src ，必须
 *         "alt": "yyy", // 图片描述文字，非必须
 *         "href": "zzz" // 图片的链接，非必须
 *     }
 * }
 */

public class ImgResult {
    private Integer errno;
    private Map<String, String> data;
    
    public ImgResult() {
    }

    public ImgResult success(String url) {
        Map<String, String> data = new HashMap<>();
        ImgResult imgResult = new ImgResult();
        imgResult.setErrno(0);
        data.put("url", url);
        data.put("alt", "图片描述文字，非必须");
        data.put("href", url);
        imgResult.setData(data);
        return imgResult;
    }

    public ImgResult error(String url) {
        Map<String, String> data = new HashMap<>();
        ImgResult imgResult = new ImgResult();
        imgResult.setErrno(0);
        data.put("url", url);
        data.put("alt", "出问题了");
        data.put("href", url);
        imgResult.setData(data);
        return imgResult;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }


}
