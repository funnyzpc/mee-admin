package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.DateUtil;
import com.mee.common.util.JacksonUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysMenu;
import com.mee.sys.vo.SysMenu2TreeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统::新菜单表(SysMenu2) 业务接口
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-05 21:48:15
*/
@Service
public class SysMenuServiceImpl {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    /**
    *   Dao
    */
    @Autowired
    public DBSQLDao dbSQLDao;
    /**
    * 主键生成器
    */
    @Autowired
    public SeqGenServiceImpl seqGenService;

    /**
     * 查询系统::新菜单表列表
     *
     * @param ... (or Map) 系统::新菜单表
     * @return 系统::新菜单表分页集合
    */
    public MeeResult list(Integer page_no, Integer page_size , Integer type, String title, String path, String target, String permission, Integer show){
      LOG.info("接收到参数 {},{}, {},{},{},{},{},{},",page_no,page_size,type,title,path,target,permission,show);
      Map<String,Object> param = new HashMap<String,Object>(8,1);
      param.put("type",null==type||"".equals(type)?null:type );
      param.put("title",null==title||"".equals(title)?null:title );
      param.put("path",null==path||"".equals(path)?null:path );
      param.put("target",null==target||"".equals(target)?null:target );
      param.put("permission",null==permission||"".equals(permission)?null:permission );
      param.put("show",null==show||"".equals(show)?null:show );
      Page list = dbSQLDao.list("com.mee.xml.SysMenu.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

    /**
     * 系统::新菜单表::按主键查询
     *
     * @param  id 系统::新菜单表主键
     * @return 系统::新菜单表
    */
    public MeeResult<SysMenu> findById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
        LOG.error("必要参数为空:{}",id);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      SysMenu sysMenu = dbSQLDao.findOne("com.mee.xml.SysMenu.findById", param);
      return ResultBuild.build(sysMenu);
    }

//    /**
//     * 系统::新菜单表::新增
//     *
//     * @param sysMenu2(or Map) 系统::新菜单表
//     * @return 插入条数
//    */
//    @Override
//    public Map add(SysMenu2 sysMenu2){
//      LOG.info("接收到参数 {}", sysMenu2);
//      if(null == sysMenu2
//       || null==sysMenu2.getId() || null==sysMenu2.getType() || null==sysMenu2.getTitle() || null==sysMenu2.getPath() || null==sysMenu2.getShow() ){
//          return ResultBuild.fail("参数缺失请检查~");
//      }
//      final LocalDateTime now = DateUtil.now();
//      final String user_id = ShiroUtils.getUserId();
//      // 主键
//      sysMenu2.setId(seqGenService.genPrimaryKey());
//      // 通用字段
//      sysMenu2.setCreate_time(now);
//      sysMenu2.setCreate_by(user_id);
//      sysMenu2.setUpdate_time(now);
//      sysMenu2.setUpdate_by(user_id);
//      int insert_count = dbSQLDao.create("com.mee.xml.SysMenu.insert",sysMenu2);
//      return ResultBuild.success(insert_count);
//    }

    @Transactional(rollbackFor = Exception.class,readOnly = false)
    public MeeResult add(SysMenu sysMenu) {
        // check
        if(null == sysMenu ||
                null == sysMenu.getPid() ||
                null == sysMenu.getType() ||
                null == sysMenu.getTitle() ||
                null == sysMenu.getPath() ||
                null == sysMenu.getSort()){
            LOG.info("缺少参数~:{}",JacksonUtil.toJsonString(sysMenu));
            //return ResultBuild.fail("缺少参数[pid,type,title,path,sort]，请检查~");
            return ResultBuild.fail("缺少参数[菜单类型,菜单名称,排序]，请检查~");
        }
        final String user_id = ShiroUtils.getUserId();
        final String id = seqGenService.genShortPrimaryKey();
        final LocalDateTime now = DateUtil.now();
        sysMenu.setId(id);
        //sysMenu.setPid("".equals(sysMenu.getPid())?null:sysMenu.getPid());
        sysMenu.setSub_count(0);
        sysMenu.setCreate_by(user_id);
        sysMenu.setCreate_time(now);
        sysMenu.setUpdate_by(user_id);
        sysMenu.setUpdate_time(now);
        int insert_count = dbSQLDao.insert("com.mee.xml.SysMenu.insert",sysMenu);
        // 存在父级->更新父级sub_count
        //if(null != sysMenu.getPid()){
        if( !"0".equals(sysMenu.getPid()) ){
            int update_count = dbSQLDao.update("com.mee.xml.SysMenu.updateSubCount",sysMenu);
            LOG.info("更新{}条",update_count);
        }
        LOG.info("已写入数据{}条",insert_count);
        return ResultBuild.SUCCESS();
    }

    /**
     * 更新
     * @param sysMenu 数据
     * @return 更新结果
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public MeeResult update(SysMenu sysMenu) {
        if(null == sysMenu ||
                null==sysMenu.getId() ||
                null == sysMenu.getType() ||
                null == sysMenu.getTitle() ||
                null == sysMenu.getPath() ||
                null == sysMenu.getSort()){
            LOG.info("缺少参数~:{}",JacksonUtil.toJsonString(sysMenu));
            return ResultBuild.fail("缺少必要参数[id,type,title,path,sort]，请检查~");
        }

        final String user_id = ShiroUtils.getUserId();

        // 先查询
        Map<String,Object> param = new HashMap<>(1,1);
        param.put("id",sysMenu.getId());
        final SysMenu oldSysMenu = dbSQLDao.findOne("com.mee.xml.SysMenu.findById", param);
        if(null == oldSysMenu){
            LOG.error("未找到记录：{}",JacksonUtil.toJsonString(sysMenu));
            return ResultBuild.fail("未找到当前记录");
        }
        
        if( !oldSysMenu.getPid().equals(sysMenu.getPid()) ){
            LOG.error("上级id不一致，pid不可修改：{}",JacksonUtil.toJsonString(sysMenu));
            return ResultBuild.fail("上级id不一致");
        }


        // 更新
        final LocalDateTime now = DateUtil.now();
        sysMenu.setUpdate_by(user_id);
        sysMenu.setUpdate_time(now);
        int update_count = dbSQLDao.update("com.mee.xml.SysMenu.update", sysMenu);

        // 更新sub_count
        param.clear();
        param.put("pid", sysMenu.getPid());
        param.put("update_by",user_id);
        param.put("update_time",now);
        int update_sub_count = dbSQLDao.update("com.mee.xml.SysMenu.updateSubCount", param);
        LOG.info("已更新记录{}条，子级{}条",update_count,update_sub_count);
        return ResultBuild.build(update_count);
    }

    /**
     * 菜单删除操作
     *
     * @param id 主键
     * @return 删除结果
     */
    @Transactional(rollbackFor = Exception.class)
    public MeeResult deleteById(String id){
        if(null == id){
            LOG.error("参数为空 ~ ");
            return ResultBuild.fail("参数为空～");
        }
        Map<String,Object> param = new HashMap<>(1,1);
        param.put("pid",id);
        List<SysMenu> sub_list = dbSQLDao.find("com.mee.xml.SysMenu.findList",param);
        if( !sub_list.isEmpty() ){
            return ResultBuild.fail("存在子级，请先删除子级～");
        }

        // 如果有子级，则需要手动删除子级才可删除当前级
        param.clear();
        param.put("id",id);
        SysMenu sysMenu2 = dbSQLDao.findOne("com.mee.xml.SysMenu.findById",param);
        int delete_count = dbSQLDao.delete("com.mee.xml.SysMenu.deleteById",param);
        // 再更新sub_count
        int update_sub_count = this.updateSubCount(sysMenu2);
        LOG.info("{}=>已删除{}条，更新{}条",id,delete_count,update_sub_count);
        return ResultBuild.SUCCESS();
    }

    /**
     * 更新子级数,主意这个没有事物
     * @return 更新数
     */
    private int updateSubCount(SysMenu sysMenu2){
        if( null==sysMenu2 || null==sysMenu2.getPid() ||  "".equals(sysMenu2.getPid()) ){
            return 0;
        }
        final String pid = sysMenu2.getPid();
        final LocalDateTime now = DateUtil.now();
        final String user_id = ShiroUtils.getUserId();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setPid(pid);
        sysMenu.setUpdate_by(user_id);
        sysMenu.setUpdate_time(now);
        if(null != sysMenu.getPid()){
            int update_count = dbSQLDao.update("com.mee.xml.SysMenu.updateSubCount",sysMenu);
            LOG.info("更新{}条",update_count);
            return update_count;
        }
        return 0;
    }
    /**
     * 系统::新菜单表::批量删除
     *
     * @ids 系统::新菜单表 主键集合
     * @return 删除条数
    */
    public MeeResult deleteBatch(String[] ids){
      if( null==ids || 0==ids.length ){
        LOG.error("必要参数为空:{}",ids);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("list",ids);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysMenu.deleteBatch", param);
      LOG.info("已删除记录{}->{}条",ids,delete_count);
      return ResultBuild.build(delete_count);
    }

    public MeeResult menuAll(String title) {
        List<SysMenu> data_list = dbSQLDao.find("com.mee.xml.SysMenu.findList");
        if( data_list.isEmpty() ){
           return ResultBuild.build(new String[]{});
        }
        // 结构排序
        List<SysMenu2TreeVO> result_list = new ArrayList<>(data_list.size());
        for(int i=0;i<data_list.size();i++){
            SysMenu item = data_list.get(i);
            if( null==item ){
                continue;
            }
            // 顶级菜单的pid都是0
            //if( null==item.getPid() || "".equals(item.getPid()) ){
            if( "0".equals(item.getPid()) ){
                result_list.add(this.toTreeVO(item,0));
                // 间接的移除
                data_list.set(i,null);
                for( int j=0;j<data_list.size();j++){
                    SysMenu item2 = data_list.get(j);
                    if( null!=item2 ){
                        this.findLowerItem(data_list, item.getId(),result_list,0);
                    }
                }
            }
        }
        // 只能在这个地方过滤，否则前端展开后不完整
//        if( null!=title && !"".equals(title) ){
//            List<SysMenu2TreeVO> result_list2 = new ArrayList<>(data_list.size());
//            String id = null;
//            Boolean is_find = Boolean.FALSE;
//            for( SysMenu2TreeVO item:result_list2 ){
//                if( Boolean.TRUE.equals(is_find) || item.getTitle().startsWith(title) ){
//                    if( id!=null && !id.equals(item.getPid())  ){
//                        break;
//                    }
//                    result_list2.add(item);
//                    id =  item.getId();
//                    is_find = true;
//                }
//            }
//            result_list=result_list2;
//        }
        if( null!=title && !"".equals(title=title.trim()) ){
            List<SysMenu2TreeVO> result_list2 = new ArrayList<>(data_list.size());
            Boolean is_find = Boolean.FALSE;
            for( SysMenu2TreeVO item:result_list ){
                //if( Boolean.TRUE.equals(is_find) && ( null==item.getPid() || "".equals(item.getPid())) ){
                if( Boolean.TRUE.equals(is_find) && "0".equals(item.getPid()) ){
                    break;
                }
                if( Boolean.TRUE.equals(is_find) || item.getTitle().startsWith(title) ){
                    // 第一行 使之显示
                    if( Boolean.FALSE.equals(is_find) ){
                        item.setLevel(0);
                    }
                    result_list2.add(item);
                    is_find = true;
                }
            }
            result_list=result_list2;
        }
        return ResultBuild.build(result_list);
    }

    private void findLowerItem(List<SysMenu> menu_list, String menu_id, List<SysMenu2TreeVO> result_list, int count) {
        // 防止死循环
        if(count>=80){
            LOG.error("菜单存在死循环风险(>400)，请检查:{},{}", JacksonUtil.toJsonString(menu_list),menu_id);
            return;
        }
        if(null == menu_list || menu_list.isEmpty() || null == menu_id){
            LOG.error("menu_list 或 menu_id 为空:{},{}",JacksonUtil.toJsonString(menu_list),menu_id);
            return;
        }
        count++;
        for( int i=0;i<menu_list.size();i++ ){
            SysMenu item = menu_list.get(i);
            if(null!=item && menu_id.equals(item.getPid())){
                result_list.add(this.toTreeVO(item,count));
                menu_list.set(i,null);
                for( int j=0;j<menu_list.size();j++){
                    SysMenu item2 = menu_list.get(j);
                    if( null!=item2 ){
                        this.findLowerItem(menu_list, item.getId(),result_list,count);
                    }
                }
            }
        }
//        count++;


//        // 递归查找
//        if(!children_list.isEmpty()){
//            ++count;
//            LOG.info("findChildren循环至=>{}",count);
//            sysMenu2VO.setChildren(children_list);
//            for(SysMenu2VO item:children_list){
////                if(item.getSub_count()>0){
//                if(null!=item){
//                    this.findChildren(menu_list,item.getId(),item,count);
//                }
//            }
//        }else {
//            // 放进去
//            // 这里置为null方便jackson序列化
//            sysMenu2VO.setChildren(null);
//        }

    }

    /**
     * 对象转换
     * @param sysMenu2 菜单对象
     * @return  新菜单对象
     */
    private SysMenu2TreeVO toTreeVO( SysMenu sysMenu2 ,int level){
        SysMenu2TreeVO sysMenu2TreeVO = new SysMenu2TreeVO();
        sysMenu2TreeVO.setId(sysMenu2.getId());
        sysMenu2TreeVO.setPid(sysMenu2.getPid());
        sysMenu2TreeVO.setType(sysMenu2.getType());
        sysMenu2TreeVO.setTitle(sysMenu2.getTitle());
        sysMenu2TreeVO.setIcon(sysMenu2.getIcon());
        sysMenu2TreeVO.setPath(sysMenu2.getPath());
        sysMenu2TreeVO.setTarget(sysMenu2.getTarget());
        sysMenu2TreeVO.setPermission(sysMenu2.getPermission());
        sysMenu2TreeVO.setSub_count(sysMenu2.getSub_count());
        sysMenu2TreeVO.setShow(sysMenu2.getShow());
        //sysMenu2TreeVO.setHidden( (null!=sysMenu2.getShow() && 1==sysMenu2.getShow()) ? Boolean.FALSE:Boolean.TRUE);
        sysMenu2TreeVO.setSort(sysMenu2.getSort());

        sysMenu2TreeVO.setCreate_time(sysMenu2.getCreate_time());
        sysMenu2TreeVO.setCreate_by(sysMenu2.getCreate_by());
        sysMenu2TreeVO.setUpdate_time(sysMenu2.getUpdate_time());
        sysMenu2TreeVO.setUpdate_by(sysMenu2.getUpdate_by());
        sysMenu2TreeVO.setLevel(level);
        sysMenu2TreeVO.setLevel_locking((level+1)*16);
        return sysMenu2TreeVO;

    }

}
