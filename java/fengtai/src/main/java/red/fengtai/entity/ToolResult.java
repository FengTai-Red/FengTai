package red.fengtai.entity;

/**
 * Tool文件下载用的class
 * 一个通用返回结果集，一般情况下返回的数据格式如下
 * {
 *	"code": 200,
 *	"msg": "success",
 *	"data": ""
    }
 */
public class ToolResult {
    private Object data;
    private String msg;
    private int code;

	// getter setter 省略，构造方法省略
    // 操作成功返回数据
    public static ToolResult succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static ToolResult succ(String msg) {
        return succ(200, msg, null);
    }


    public static ToolResult succ(int code, String msg, Object data) {
        ToolResult r = new ToolResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static ToolResult succ(String msg, Object data) {
        return succ(200,msg,data);
    }

    // 操作异常返回
    public static ToolResult fail(int code, String msg, Object data) {
        ToolResult r = new ToolResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static ToolResult fail(String msg) {
        return fail(400,msg,null);
    }

    public static ToolResult fail(int code, String msg) {
        return fail(code,msg,"null");
    }

    public static ToolResult fail(String msg, Object data) {
        return fail(400,msg,data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
}
