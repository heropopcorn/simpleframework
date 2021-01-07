package org.simpleframework.util;

import java.util.Collection;

/**
*
* 校验工具类
* @Author xuan
* @Date  2021/1/6 10:27
*
*/
public class ValidationUtil {

    /**
     * 判断集合是否为空或者null
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object [] array){
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }
}
