package red.fengtai.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    /**
     * MD5加密
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++){
                i = byteDigest[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            //  32位加密
            return buffer.toString();
            //  16位加密
            // return buffer.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("h11131")); /// h11131: caa520791371eb53a68184434c7eb06f
    }
}
