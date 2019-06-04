package com.coco.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Classname RandomSecuritycode
 * @Description TODO
 * @Date 19-5-29 下午4:09
 * @Created by xns
 */
public class RandomSecuritycode {
    /**
    * @Description Generate random verification code
    * @return java.lang.String
    *
    **/
    public static String achieveCode() {  //由于数字 1 、 0 和字母 O 、l 有时分不清楚，所以，没有数字 1 、 0
        String[] beforeShuffle= new String[] {"0","1","2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(beforeShuffle);//将数组转换为集合
        Collections.shuffle(list);  //打乱集合顺序
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)); //将集合转化为字符串
        }
        return sb.toString().substring(3, 9);  //截取字符串第4到8
    }
}
