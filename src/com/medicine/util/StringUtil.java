package com.medicine.util;

public class StringUtil {
    public static String[] convertStrToArray(String str){  
        String[] strArray = null;  
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    } 
    
    public static int[] StringToInt(String[] arrs){
        int[] ints = new int[arrs.length];
        for(int i=0;i<arrs.length;i++){
            ints[i] = Integer.parseInt(arrs[i]);
        }
        return ints;
    }
}
