package com.mh.util;

import java.util.Random;

/**
 * @author xukh
 * @date 2019/12/3 0003 09:37
 */
public class StringUtil {

    /**
     * 判断是否是空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否不是空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * {}字符串转[{}]Json
     *
     * @param str
     * @return
     */
    public static String strToJson(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            str = '[' + str + ']';
        }
        return str;
    }

    /**
     * 生成四位编号
     *
     * @param code
     * @return
     */
    public static String formatCode(String code) {
        int length = code.length();
        Integer num = Integer.parseInt(code.substring(length - 4, length)) + 1;
        String codeNum = num.toString();
        int codeLength = codeNum.length();
        for (int i = 4; i > codeLength; i--) {
            codeNum = "0" + codeNum;
        }
        return codeNum;
    }

    //生成验证码
    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    //生成数字验证码
    public static String getNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    public static void main(String[] args) {
        System.out.println(getNumr(4));
    }
}
