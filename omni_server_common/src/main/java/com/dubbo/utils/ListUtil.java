package com.dubbo.utils;


import java.util.List;

public class ListUtil {

    public static Boolean isNotEmpty(List objectList){
        return objectList!=null&&objectList.size()>0;
    }
}
