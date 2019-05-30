package com.winnerbook.base.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Administrator
 * 
 */
public class Tools {

    public static boolean isEmpty(String str) {
        return (null == str || str.trim().length() < 1);
    }

    /**
     * 如果源字符串为NULL，则返回目标字符串；否则返回源字符串本身。
     * 
     * @param src
     *            源字符串
     * @param des
     *            目标字符串
     * @return
     */
    public static String convertToStr(String src, String des) {
        if (src == null || src.equals("")) {
            return des;
        } else {
            return src;
        }
    }

    /**
     * 如果源为NULL，则返回目标字符串；否则返回源字符串本身。
     * 
     * @param src
     *            源
     * @param des
     *            目标字符串
     * @return
     */
    public static String convertToStr(Object src, String des) {
        if (src == null) {
            return des;
        } else {
            return src.toString();
        }
    }

    /**
     * 如果源字符串为NULL，则返回目标整数，否则返回由src转换后的整数
     * 
     * @param src
     *            源字符串
     * @param des
     *            目标整数
     * @return
     */
    public static int convertToInt(String src, int des) {
        int tmp = des;
        if (src == null || src.equals("")) {
            return des;
        } else {
            try {
                tmp = Integer.parseInt(src);
            } catch (Exception e) {
            }
            return tmp;
        }
    }

    /**
     * 如果源为NULL，则返回目标整数，否则返回由src转换后的整数
     * 
     * @param src
     *            源
     * @param des
     *            目标整数
     * @return
     */
    public static int convertToInt(Object src, int des) {
        int tmp = des;
        if (src == null) {
            return des;
        } else {
            try {
                tmp = Integer.parseInt(src.toString());
            } catch (Exception e) {
            }
            return tmp;
        }
    }

    /**
     * 如果源字符串为NULL，则返回目标浮点数，否则返回由src转换后的浮点数
     * 
     * @param src
     *            源字符串
     * @param des
     *            目标浮点数
     * @return
     */
    public static float converToFloat(String src, float des) {
        float tmp = des;
        if (src == null) {
            return des;
        } else {
            try {
                tmp = Float.parseFloat(src);
            } catch (Exception e) {
            }
            return tmp;
        }
    }

    public static long converToLong(String src, long des) {
        long tmp = des;
        if (null == src) {
            return des;
        } else {
            try {
                tmp = Long.parseLong(src);
            } catch (Exception e) {
                return des;
            }
            return tmp;
        }
    }

    public static long converToLong(Object src, long des) {
        long tmp = des;
        if (null == src)
            return des;

        try {
            tmp = Long.parseLong(src.toString());
        } catch (Exception e) {
            return des;
        }
        return tmp;
    }

    /**
     * 如果源字符串为NULL，则返回目标整数，否则返回由src转换后的整数
     * 
     * @param src
     *            源字符串
     * @param des
     *            目标整数
     * @return
     */
    public static short convertToShort(String src, short des) {
        short tmp = des;
        if (src == null || src.equals("")) {
            return des;
        } else {
            try {
                tmp = Short.parseShort(src);
            } catch (Exception e) {
            }
            return tmp;
        }
    }

    /**
     * 如果源字符串为NULL，则返回目标整数，否则返回由src转换后的整数
     * 
     * @param src
     *            源字符串
     * @param des
     *            目标整数
     * @return
     */
    public static short convertToShort(Object src, short des) {
        short tmp = des;
        if (src == null || src.equals("")) {
            return des;
        } else {
            try {
                tmp = Short.parseShort(src.toString());
            } catch (Exception e) {
            }
            return tmp;
        }
    }

    public static String replace(String src, String def, String tmp) {
        if (Tools.isEmpty(src) || Tools.isEmpty(def))
            return null;
        while (src.indexOf(def) > 0) {
            src.replaceAll(def, tmp);
        }
        return src;
    }

    // 格式文本内容
    public static String tranStr(String oldStr) {
        int k = oldStr.indexOf(String.valueOf(("  ")));
        while (k >= 0) {
            oldStr = oldStr.substring(0, k) + "&nbsp;&nbsp;"
                    + oldStr.substring(k + 2, oldStr.length());
            k = oldStr.indexOf(String.valueOf("  "));
        }

        int i = oldStr.indexOf(String.valueOf((char) 13));
        while (i > 0) {
            oldStr = oldStr.substring(0, i) + "<br>"
                    + oldStr.substring(i + 1, oldStr.length());
            i = oldStr.indexOf(String.valueOf((char) 13));
        }
        int j = oldStr.indexOf(String.valueOf((char) 10));
        while (j > 0) {
            oldStr = oldStr.substring(0, j) + ""
                    + oldStr.substring(j + 1, oldStr.length());
            j = oldStr.indexOf(String.valueOf((char) 10));
        }
        return oldStr;
    }

    /**
     * 合同生成的方法
     * 
     * @param str
     * @param total
     * @return
     */
    public static String getContractNum(String str, Long total) {
        if (Tools.isEmpty(str)) {
            str = "";
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMM");
        String sj_int = dateformat.format(new Date());
        int random = (int) (Math.random() * 10e5);
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.applyPattern("0000");
        String contractNum = str + sj_int + random + df.format(total);
        return contractNum;
    }

    /**
     * 合同生成的方法 加竖线的
     * 
     * @param str
     * @param total
     * @return
     */
    public static String getContractNumAdd(String str, Long total) {
        if (Tools.isEmpty(str)) {
            str = "";
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMM");
        String sj_int = dateformat.format(new Date());
        int random = (int) (Math.random() * 10e5);
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.applyPattern("0000");
        String contractNum = str + "|" + sj_int.substring(0, 4) + "|"
                + sj_int.substring(4, 6) + "|" + random + "|"
                + df.format(total);
        return contractNum;
    }
    
    //生成企业编号
	public static String getBusNumber(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String busNumberStr = "BN";
		String date = sdf.format(new Date());
		Random random = new Random();//默认构造方法
		int i2 = random.nextInt(1000000);
		return busNumberStr+date+i2;
	}

   
}
