package com.mee.common.service.impl;

import com.mee.core.dao.DBSQLDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* 字典数据缓存及刷新处理，解决页面刷新字段不显示问题
* @className    CommonDictServiceImpl
* @author       shadow
* @date         2023/6/7 14:27
* @version      1.0
*/
@Service
public class CommonDictServiceImpl {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(CommonDictServiceImpl.class);

    /**
     * DB
     */
    @Autowired
    private DBSQLDao dbSQLDao;

//    /**
//     * 获取指定字典类型
//     * @param names 字典
//     * @return 字典数据
//     */
//    public Map getDicts(String[] names) {
//        if( null==names || names.length==0 ){
//            return new HashMap(0);
//        }
//        Map<String,Object> param = new HashMap<>(1);
//        param.put("list",names);
//        List<Map> data_list = dbSQLDao.find("com.mee.xml.SysDictDetail.findByNames",param);
//        if( null==data_list || data_list.isEmpty() ){
//            return new HashMap(0);
//        }
//        // data_list to {"name1":[{"dict_id":1,"label":"开","value":"1"}],"name2":[{"dict_id":1,"label":"关","value":"0"}]}
//        Map<String,List> result_data = new HashMap<>(names.length,1);
//        for( Map<String,Object> item:data_list ){
//            item.put("l",item.remove("label"));
//            item.put("v",item.remove("value"));
//            String name = (String) item.get("name");
//            if( result_data.containsKey(name)){
//                result_data.get(name).add(item);
//            }else{
//                List<Map> sub_list = new ArrayList<>(8);
//                sub_list.add(item);
//                result_data.put(name,sub_list);
//            }
//        }
//        data_list.clear();
//        return result_data;
//    }

    public Map getDicts(String[] names) {
        if( null==names || names.length==0 ){
            return new HashMap(0);
        }
        Map<String,Object> param = new HashMap<>(1);
        param.put("list",names);
        List<Map> data_list = dbSQLDao.find("com.mee.xml.SysDictDetail.findByNames",param);
        if( null==data_list || data_list.isEmpty() ){
            return new HashMap(0);
        }
        Map<String,Map> result_data = new HashMap<>(names.length,1);
        for( Map<String,Object> item:data_list ){
            item.put("l",item.remove("label"));
            item.put("v",item.remove("value"));
            String name = (String) item.get("name");
            if( result_data.containsKey(name)){
                result_data.get(name).put(item.get("v"),item);
            }else{
                // 这里必须是TreeMap(或LinkedHashMap) 以避免排序出现错误
                Map<String,Map> sub_map = new LinkedHashMap<>(8);
                sub_map.put((String)item.get("v"),item);
                result_data.put(name,sub_map);
            }
        }
        data_list.clear();
        // {"sys_common_status":{"0":{"dict_id":15,"dict_sort":1,"v":"0","name":"sys_common_status","detail_id":10,"l":"成功"},"1":{"dict_id":15,"dict_sort":2,"v":"1","name":"sys_common_status","detail_id":11,"l":"失败"}},"sys_yes_no":{"Y":{"dict_id":11,"dict_sort":27,"v":"Y","name":"sys_yes_no","detail_id":36,"l":"是"},"N":{"dict_id":11,"dict_sort":28,"v":"N","name":"sys_yes_no","detail_id":37,"l":"否"}}}
        return result_data;
    }

}
