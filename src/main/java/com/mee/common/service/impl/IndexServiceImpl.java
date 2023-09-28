package com.mee.common.service.impl;

import com.mee.common.util.JacksonUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysMenu;
import com.mee.sys.vo.SysMenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author shadow
* @description 菜单业务处理类
* @date 2022/9/2 14:16
*/
@Service
public class IndexServiceImpl {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(IndexServiceImpl.class);

    /**
     * dao
     * dao
     */
    @Autowired
    private DBSQLDao dbSQLDao;

    public MeeResult<List<SysMenuVO>> buildMenu(){
        // 获取所有表数据
//        List<SysMenu> data_list = dbSQLDao.find("com.mee.xml.SysMenu.findList");
        List<SysMenu> data_list = this.getUserMenus();
        if( data_list.isEmpty() ){
            return ResultBuild.build(new ArrayList<>());
        }
        // 构建菜单结构
        List<SysMenuVO> result_list = new ArrayList<>(8);
        for( int i=0;i<data_list.size();i++ ){
            SysMenu item = data_list.get(i);
            //if( null==item.getPid() || "".equals(item.getPid()) ){
            if( "0".equals(item.getPid()) ){
                SysMenuVO sysMenu2VO = this.toVO(item);
                result_list.add(sysMenu2VO);
                // make GC work
                data_list.set(i,null);
            }
        }
        // children
        for(SysMenuVO menuVO2:result_list){
//            if(menuVO2.getSub_count()>0){
            if( null!=menuVO2 ){
                this.findChildren(data_list,menuVO2.getId(),menuVO2,0);
            }
        }
        return ResultBuild.build(result_list);
    }


    private void findChildren(List<SysMenu> menu_list, String menu_id, SysMenuVO sysMenu2VO, int count) {
        // 防止死循环
        if(count>=80){
            LOG.error("菜单存在死循环风险(>400)，请检查:{},{}", JacksonUtil.toJsonString(menu_list),menu_id);
            return;
        }
        if(null == menu_list || menu_list.isEmpty() || null == menu_id){
            LOG.error("user_menu_list 或 menu_id 为空:{},{}",JacksonUtil.toJsonString(menu_list),menu_id);
            return;
        }
        List<SysMenuVO> children_list = new ArrayList<SysMenuVO>(8);
        for( int i=0;i<menu_list.size();i++ ){
            SysMenu item = menu_list.get(i);
            if(null!=item && menu_id.equals(item.getPid())){
                children_list.add(this.toVO(item));
                menu_list.set(i,null);
            }
        }

        // 递归查找
        if(!children_list.isEmpty()){
            ++count;
            LOG.info("findChildren循环至=>{}",count);
            sysMenu2VO.setChildren(children_list);
            for(SysMenuVO item:children_list){
//                if(item.getSub_count()>0){
                if(null!=item){
                    this.findChildren(menu_list,item.getId(),item,count);
                }
            }
        }else {
            // 放进去
            // 这里置为null方便jackson序列化
            sysMenu2VO.setChildren(null);
        }
    }

    /**
     * 对象转换
     * @param sysMenu2 菜单对象
     * @return  新菜单对象
     */
    private SysMenuVO toVO(SysMenu sysMenu2 ){
        SysMenuVO sysMenu2VO = new SysMenuVO();
        sysMenu2VO.setId(sysMenu2.getId());
        sysMenu2VO.setPid(sysMenu2.getPid());
        sysMenu2VO.setType(sysMenu2.getType());
        sysMenu2VO.setTitle(sysMenu2.getTitle());
        sysMenu2VO.setIcon(sysMenu2.getIcon());
        sysMenu2VO.setPath(sysMenu2.getPath());
        sysMenu2VO.setTarget(sysMenu2.getTarget());
        sysMenu2VO.setSub_count(sysMenu2.getSub_count());
        sysMenu2VO.setShow(sysMenu2.getShow());
        sysMenu2VO.setHidden( (null!=sysMenu2.getShow() && 1==sysMenu2.getShow()) ? Boolean.FALSE:Boolean.TRUE);
        sysMenu2VO.setSort(sysMenu2.getSort());

        sysMenu2VO.setCreate_time(sysMenu2.getCreate_time());
        sysMenu2VO.setCreate_by(sysMenu2.getCreate_by());
        sysMenu2VO.setUpdate_time(sysMenu2.getUpdate_time());
        sysMenu2VO.setUpdate_by(sysMenu2.getUpdate_by());
        return sysMenu2VO;
    }

    /**
     * 获取登录用户菜单
     * @return
     */
    private List<SysMenu> getUserMenus(){
        final String user_id = ShiroUtils.getUserId();
        if( null==user_id || "".equals(user_id)){
            throw new RuntimeException(("登录用户不可为空!"));
        }
        // 管理员拥有所有菜单权限
        if("1".equals(user_id)){
            return dbSQLDao.find("com.mee.xml.SysMenu.findList");
        }
        Map<String,String> param = new HashMap<String,String>(2);
        param.put("user_id",user_id);
        return dbSQLDao.find("com.mee.xml.SysMenu.findByUserId",param);
    }


}
