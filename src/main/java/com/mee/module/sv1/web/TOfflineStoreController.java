package com.mee.module.sv1.web;


import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mee.module.sv1.service.TOfflineStoreService;
import com.mee.module.sv1.entity.TOfflineStore;

import java.lang.String;
import java.lang.Integer;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 测试::线下店铺web接口(TOfflineStoreController)
 *
 * @author  shadow
 * @version v1.0
 * @date    2023-06-16 11:25:46
 */
@Controller
@RequestMapping("/sv1/t_offline_store")
public class TOfflineStoreController{

    /**
    * 业务处理类
    */
    @Autowired
    private TOfflineStoreService tOfflineStoreService;

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sv1:t_offline_store:list")
    @GetMapping
    public String index(){
        return "sv1/t_offline_store";
    }

    /**
     * 查询 测试::线下店铺 列表
     */
    @RequiresPermissions("sv1:t_offline_store:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<TOfflineStore>> list(
        @RequestParam(defaultValue="1")Integer page_no,
        @RequestParam(defaultValue="10")Integer page_size,
        @JsonSerialize(using =LocalDateSerializer.class)
        @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
        @DateTimeFormat(pattern="yyyy-MM-dd")
        LocalDate open_date,
        String code,
        String name,
        String nick_name,
        String addr,
        Integer brand,
        Integer status
    ){
        return tOfflineStoreService.list(page_no,page_size,open_date,code,name,nick_name,addr,brand,status);
    }


    /**
     * 测试::线下店铺::详细信息
     * @param id 主鍵
     * @return 主鍵記錄
     */
    @RequiresPermissions("sv1:t_offline_store:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<TOfflineStore> findById(@RequestParam(required = true)String id){
        return tOfflineStoreService.findById( id );
    }

    /**
     * 测试::线下店铺::新增
     * @param tOfflineStore 测试::线下店铺:對象
     * @return 新增結果
     */
    @RequiresPermissions("sv1:t_offline_store:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) TOfflineStore tOfflineStore){
        return tOfflineStoreService.add( tOfflineStore );
    }

    /**
     * 测试::线下店铺::修改
     * @param tOfflineStore 测试::线下店铺:對象
     * @return 修改結果
     */
    @RequiresPermissions("sv1:t_offline_store:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) TOfflineStore tOfflineStore ){
        return tOfflineStoreService.update( tOfflineStore );
    }

    /**
     * 测试::线下店铺::删除
     * @param id 主鍵
     * @return 刪除結果
     */
    @RequiresPermissions("sv1:t_offline_store:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteById(@RequestParam(required = true) String id){
        return tOfflineStoreService.deleteById(id);
    }

    /**
     * 测试::线下店铺:批量删除
     * @param ids 逐漸列表
     * @return 批量刪除結果
     */
    @RequiresPermissions("sv1:t_offline_store:delete")
    @DeleteMapping("/delete_batch")
    @ResponseBody
    public MeeResult<Integer> deleteBatch(@RequestBody(required = true) String[] ids){
        return tOfflineStoreService.deleteBatch(ids);
    }

     /**
     *  导出 导出店铺信息
     * @param response  响应体
     * @param page_no    分页
     * @param page_size  分页大小
     * @param open_date  开店日
     * @param code      店铺编号
     * @param name      店铺名称
     * @param nick_name  昵称/简称
     * @param addr      地址
     * @param brand     品牌
     * @param status    状态
     */
    @RequiresPermissions("sv1:t_offline_store:export")
    @GetMapping("export")
    public void doExport(HttpServletResponse response,
                         @RequestParam(defaultValue="1")Integer page_no,
                         @RequestParam(defaultValue="10")Integer page_size,
                         @JsonSerialize(using =LocalDateSerializer.class)
                         @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
                         @DateTimeFormat(pattern="yyyy-MM-dd")
                         LocalDate open_date,
                         String code,
                         String name,
                         String nick_name,
                         String addr,
                         Integer brand,
                         Integer status){
        tOfflineStoreService.doExport( response,page_no,page_size,open_date,code,name,nick_name,addr,brand,status );
    }

    /**
     * 导入店铺信息
     * @param file 文件
     * @return
     */
    @RequiresPermissions("sv1:t_offline_store:import")
    @PostMapping("import")
    @ResponseBody
    public MeeResult<String> doImport(@RequestParam(value = "file",required = true) MultipartFile file
                        /*@RequestParam(value = "name",required = false) String name*/)throws Exception{
        return tOfflineStoreService.doImport(file);
    }

}
