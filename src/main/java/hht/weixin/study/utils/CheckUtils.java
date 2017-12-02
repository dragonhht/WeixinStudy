package hht.weixin.study.utils;

import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 校验微信token工具类.
 *
 * @author: huang
 * Date: 17-12-2
 */
public class CheckUtils {
    private static final String token = "dragonhht";

    public static boolean checkSignature(String signature, String timetamp, String nonce) throws NoSuchAlgorithmException {
        // 排序
        String[] array = new String[]{token, timetamp, nonce};
        Arrays.sort(array);

        // 生成字符串
        StringBuffer text = new StringBuffer();
        for (int i=0; i < array.length; i++) {
            text.append(array[i]);
        }

        // 加密
        String temp = toSHA1(text.toString());

        return temp.equals(signature);
    }

    public static String toSHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");

        // 加密
        byte[] a = md.digest(input.getBytes());

        return new String(Hex.encode(a));
    }
}
