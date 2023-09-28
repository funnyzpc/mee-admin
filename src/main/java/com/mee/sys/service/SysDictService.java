package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysDict;


/**
 * 数据字典(SysDict2) 业务接口
 * 
 * @author  shadow
 * @version v1.0
 * @date    2023-05-15 10:27:36
 */
public interface SysDictService {
    /**
     * 查询数据字典列表
     *
     * @param SysDict2(or Map) 数据字典
     * @return 数据字典分页集合
     */
    MeeResult<Page<SysDict>> list(Integer page_no, Integer page_size , String name, String description);

    /**
     * 按主键查询数据字典
     *
     * @param 数据字典主键
     * @return 数据字典
     */
    MeeResult<SysDict> findById(String id);

    /**
     * 新增数据字典
     *
     * @param SysDict2(or Map) 数据字典
     * @return 插入条数
     */
    MeeResult<Integer> add(SysDict sysDict2);
    /**
     * 修改数据字典
     *
     * @param SysDict2(or Map) 数据字典
     * @return 更新条数
     */
    MeeResult<Integer> update(SysDict sysDict2);

    /**
     * 删除数据字典
     *
     * @id 数据字典 主键
     * @return 删除条数
     */
    MeeResult<Integer> deleteById(String id);

    /**
     * 批量删除数据字典
     *
     * @ids 数据字典 主键集合
     * @return 删除条数
     */
    MeeResult<Integer> deleteBatch(String[] ids);

}
