package com.mee.common.util.excel;


import org.springframework.util.ConcurrentReferenceHashMap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author funnyzpc
 */
public class ExcelDataReflectUtil {

    private static  Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentReferenceHashMap(256);
    private static  Field[] NO_FIELDS = new Field[0];

    protected static Object[] fieldValues(final Object obj, final String[] fieldNames,Object[] valueList) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field[] fields = superClass.getDeclaredFields();
                for(int k=0;k<fields.length;k++){
                    if ((!Modifier.isPublic(fields[k].getModifiers()) ||
                            !Modifier.isPublic(fields[k].getDeclaringClass().getModifiers()) ||
                            Modifier.isFinal(fields[k].getModifiers())) &&
                            !fields[k].isAccessible()) {
                        fields[k].setAccessible(true);
                    }
                    //makeAccessible(fields[k]);
                    /**
                     * 需要排序，否则顺序不一致
                     */
                    for(int j=0;j<fieldNames.length;j++){
                        if(fields[k].getName().equals(fieldNames[j])){
                            valueList[j] = fields[k].get(obj);
                            break;
                        }
                    }
                }
                return valueList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //这里新增一个,否则数组越界
        return new Object[fieldNames.length];
    }

    protected static Object[] fieldValues2(final Object obj, final String[] fieldNames,Object[] valueList) {
        int fieldNamesLen = fieldNames.length;
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field[] fields = superClass.getDeclaredFields();
                Arrays.stream(fields).forEach(item->{
                    if ((!Modifier.isPublic(item.getModifiers()) ||
                            !Modifier.isPublic(item.getDeclaringClass().getModifiers()) ||
                            Modifier.isFinal(item.getModifiers())) &&
                            !item.isAccessible()) {
                        item.setAccessible(true);
                    }
                    //需要排序，否则顺序不一致
                    IntStream.range(0,fieldNamesLen).forEach(j->{
                        try {
                            if (item.getName().equals(fieldNames[j])) {
                                valueList[j] = item.get(obj);
                                return;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
                });
                return valueList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //这里新增一个,否则数组越界
        return new Object[fieldNames.length];
    }

    /**
     * 取表头名称，逻辑保留
     * @param clazz
     * @return
     */
    private static String[] fieldNames(Class<?> clazz) {
        Field[] result = declaredFieldsCache.get(clazz);
        if (result == null) {
            result = clazz.getDeclaredFields();
            declaredFieldsCache.put(clazz, result.length == 0 ? NO_FIELDS : result);
        }
        String[] cellNames = new String[result.length];
        for(int i=0;i<result.length;i++){
            cellNames[i] = result[i].getName();
        }
        return cellNames;
    }

}