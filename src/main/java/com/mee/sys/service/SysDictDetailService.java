package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysDictDetail;

/**
 * 数据字典详情(SysDictDetail2) 业务接口
 * 
 * @author  shadow
 * @version v1.0
 * @date    2023-05-15 10:27:31
 */
public interface SysDictDetailService {
    /**
     * 查询数据字典详情列表
     *
     * @param SysDictDetail2(or Map) 数据字典详情
     * @return 数据字典详情分页集合
     */
    MeeResult list(Integer page_no, Integer page_size , String dict_id, String label, String value);

    /**
     * 按主键查询数据字典详情
     *
     * @param 数据字典详情主键
     * @return 数据字典详情
     */
    MeeResult findById(String id);

    /**
     * 新增数据字典详情
     *
     * @param SysDictDetail2(or Map) 数据字典详情
     * @return 插入条数
     */
    MeeResult add(SysDictDetail sysDictDetail2);
    /**
     * 修改数据字典详情
     *
     * @param SysDictDetail2(or Map) 数据字典详情
     * @return 更新条数
     */
    MeeResult update(SysDictDetail sysDictDetail);

    /**
     * 删除数据字典详情
     *
     * @id 数据字典详情 主键
     * @return 删除条数
     */
    MeeResult deleteById(String id);

    /**
     * 批量删除数据字典详情
     *
     * @ids 数据字典详情 主键集合
     * @return 删除条数
     */
    MeeResult deleteBatch(String[] ids);

}
