package com.mee.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther shadow
 * @description 数据分片
 */
public class DataPartitionUtil {

    private static final int DATA_SPLIT_GROP_SIZE = 1000;

    public static List<List<Map>> doSplit(List<Map> dataList){
        return doSplit(dataList,DATA_SPLIT_GROP_SIZE);
    }

    /** 批次插入前写入数据 **/
    public  static List<List<Map>> doSplit(List<Map> dataList,int limit){
        if(dataList.size()<= limit){
            return new ArrayList<List<Map>>(1){{
                add(dataList);
            }};
        }
        List<List<Map>> mList=new ArrayList<List<Map>>(4);
        // 分组大小
        int groupCount=(dataList.size()% limit ==0)?dataList.size()/ limit :dataList.size()/ limit +1;
        List<Map> group;
        for(int i=0;i<groupCount;i++){
            //不是最后一组数据
            if(i<(groupCount-1)){
                group=new ArrayList<Map>();
                group.addAll(dataList.subList((i==0)?0:i* limit, (i+1)* limit));
                mList.add(group);
            }else{
                //最后一组数据这样处理
                group=new ArrayList<Map>();
                group.addAll(dataList.subList(i*limit, dataList.size()));
                mList.add(group);
            }
        }
        return mList;
    }

    /**
     * 通用数据分割模块
     * @param dataList
     * @param limit
     * @param <T>
     * @return
     */
    public static <T extends Object> List<List<T>> doSplitObj(List<T> dataList, int limit){
        if(dataList.size()<= limit){
            return new ArrayList<List<T>>(1){{
                add(dataList);
            }};
        }
        List<List<T>> mList=new ArrayList<List<T>>(4);
        // 分组大小
        int groupCount=(dataList.size()% limit ==0)?dataList.size()/ limit :dataList.size()/ limit +1;
        List<T> group;
        for(int i=0;i<groupCount;i++){
            //不是最后一组数据
            if(i<(groupCount-1)){
                group=new ArrayList<T>();
                group.addAll(dataList.subList((i==0)?0:i* limit, (i+1)* limit));
                mList.add(group);
            }else{
                //最后一组数据这样处理
                group=new ArrayList<T>();
                group.addAll(dataList.subList(i*limit, dataList.size()));
                mList.add(group);
            }
        }
        return mList;
    }
}
