package com.mee.module.sv1.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.module.sv1.entity.TOfflineStore;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.String;
import java.lang.Integer;
import java.time.LocalDate;

/**
 * 测试::线下店铺(TOfflineStore) 业务接口
 * 
 * @author  shadow
 * @version v1.0
 * @date    2023-06-16 11:25:46
 * @protocol http
 * @api_method TOfflineStoreService_invoker
 */
public interface TOfflineStoreService{

    /**
     *  查询测试::线下店铺列表
     * @param page_no 分页
     * @param page_size
     * @param open_date
     * @param code
     * @param name
     * @param nick_name
     * @param addr
     * @param brand
     * @param status 状态
     * @return 测试::线下店铺分页集合
     */
    MeeResult<Page<TOfflineStore>> list(Integer page_no, Integer page_size , LocalDate open_date, String code, String name, String nick_name, String addr, Integer brand, Integer status);


    /**
     * 按主键查询测试::线下店铺
     * @param id 主键
     * @return 表数据{"aa":1}
     */
    MeeResult findById(String id);

    /**
     * 新增测试::线下店铺
     *
     * @param TOfflineStore(or Map) 测试::线下店铺
     * @return 插入条数
     */
    MeeResult add(TOfflineStore tOfflineStore);
    /**
     * 修改测试::线下店铺
     *
     * @param TOfflineStore(or Map) 测试::线下店铺
     * @return 更新条数
     */
    MeeResult update(TOfflineStore tOfflineStore);

    /**
     * 删除测试::线下店铺
     *
     * @id 测试::线下店铺 主键
     * @return 删除条数
     */
    MeeResult deleteById(String id);

    /**
     * 批量删除测试::线下店铺
     *
     * @ids 测试::线下店铺 主键集合
     * @return 删除条数
     */
    MeeResult deleteBatch(String[] ids);

    /**
     *  导出
     * @param response  响应体
     * @param pageNo    分页
     * @param pageSize  分页大小
     * @param openDate  开店日
     * @param code      店铺编号
     * @param name      店铺名称
     * @param nickName  昵称/简称
     * @param addr      地址
     * @param brand     品牌
     * @param status    状态
     */
    void doExport(HttpServletResponse response, Integer pageNo, Integer pageSize, LocalDate openDate, String code, String name, String nickName, String addr, Integer brand, Integer status);

    /**
     * 导入用户信息
     * @param file 用户信息
     * @return
     */
    MeeResult<String> doImport(MultipartFile file)throws Exception;

}
