package com.mee.sys.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.mee.common.enums.DeleteFlagEnum;
import com.mee.common.enums.StatusEnum;
import com.mee.common.service.impl.ShiroAccountLockedServiceImpl;
import com.mee.common.util.ChaosUtil;
import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.dto.SysUser2DTO;
import com.mee.sys.entity.SysUser;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.ResultBuild;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.String;
import java.lang.Integer;
import java.time.LocalDateTime;

/**
 * 系统::用户信息表(SysUser2) 业务接口
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-30 20:59:40
*/
@Service
public class SysUserServiceImpl {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysUserServiceImpl.class);

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
     * 登录用户锁定业务
     */
    @Autowired
    private ShiroAccountLockedServiceImpl shiroAccountLockedService;

    /**
     * 系统环境
     */
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 查询系统::用户信息表列表
     *
     * @param SysUser2(or Map) 系统::用户信息表
     * @return 系统::用户信息表分页集合
    */
    //@Override
    public MeeResult list(Integer page_no, Integer page_size , String user_name, String nick_name, String phone, String email, String status, String del_flag){
      LOG.info("接收到参数 {},{}, {},{},{},{},",page_no,page_size ,user_name,phone,email,status);
      Map<String,Object> param = new HashMap<String,Object>(19,1);
      //param.put("dept_id",null==dept_id||"".equals(dept_id)?null:dept_id );
      param.put("user_name",null==user_name||"".equals(user_name)?null:"%"+user_name+"%" );
      param.put("nick_name",null==nick_name||"".equals(nick_name)?null:"%"+nick_name+"%" );
      //param.put("gender",null==gender||"".equals(gender)?null:gender );
      param.put("phone",null==phone||"".equals(phone)?null:"%"+phone+"%" );
      param.put("email",null==email||"".equals(email)?null:"%"+email+"%" );
      param.put("status",null==status||"".equals(status)?null:status );
      //param.put("del_flag",null==del_flag||"".equals(del_flag)?null:del_flag );
      param.put("del_flag",DeleteFlagEnum.NORMAL.code );
      //Page list = dbSQLDao.list("com.mee.module.sys.mapper.sys_user2.findList",param,page_no,page_size);
      Page list = dbSQLDao.list("com.mee.xml.SysUser.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

    /**
     * 系统::用户信息表::按主键查询
     *
     * @param 系统::用户信息表主键
     * @return 系统::用户信息表
    */
    //@Override
    public MeeResult findById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
        LOG.error("必要参数为空:{}",id);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      SysUser sysUser2 = dbSQLDao.findOne("com.mee.xml.SysUser.findById", param);
      return ResultBuild.build(sysUser2);
    }

    /**
     * 系统::用户信息表::新增
     *
     * @param sysUser2DTO SysUser2(or Map) 系统::用户信息表
     * @return 插入条数
    */
    //@Override
    public MeeResult add(SysUser2DTO sysUser2DTO){
        LOG.info("接收到参数 {}", sysUser2DTO);
        if(null == sysUser2DTO || null==sysUser2DTO.getUser_name() ||  "".equals(sysUser2DTO.getUser_name()) || null==sysUser2DTO.getStatus() ){
            return ResultBuild.fail("参数缺失请检查~");
        }
        final String user_name = sysUser2DTO.getUser_name();
        final String pwd1 = sysUser2DTO.getPwd1();
        final String pwd2 = sysUser2DTO.getPwd2();
        final LocalDateTime now = DateUtil.now();
        final String user_id = ShiroUtils.getUserId();
        // 是否重复
        Map<String,Object> param = new HashMap<>(2,1);
        param.put("user_name",user_name.trim());
        if( !dbSQLDao.find("com.mee.xml.SysUser.findList",param).isEmpty() ){
            LOG.error("用户重复，请检查用户名:{}",sysUser2DTO);
            return ResultBuild.fail("用户重复，请检查用户名~");
        }
        // 密码是否一致
        String decPwd1 = ChaosUtil.dec(pwd1);
        String decPwd2 = ChaosUtil.dec(pwd2);
        if( null==decPwd1 || null==decPwd2 || "".equals(decPwd1) || "".equals(decPwd2) || !decPwd1.equals(decPwd2) ){
            LOG.error("密码不一致，请检查两次密码:{}",sysUser2DTO);
            return ResultBuild.fail("密码不一致，请检查~");
        }
        String enc_pwd = new Sha512Hash(decPwd1,sysUser2DTO.getId(),3).toString();
        // 主键 及 通用字段
        sysUser2DTO.setId(seqGenService.genShortPrimaryKey());
        sysUser2DTO.setDel_flag(DeleteFlagEnum.NORMAL.code);
        sysUser2DTO.setPwd_reset_time(now);
        sysUser2DTO.setCreate_time(now);
        sysUser2DTO.setCreate_by(user_id);
        sysUser2DTO.setUpdate_time(now);
        sysUser2DTO.setUpdate_by(user_id);
        sysUser2DTO.setRegister_date(now);
        sysUser2DTO.setPassword(enc_pwd);
        try {
            int insert_count = dbSQLDao.insert("com.mee.xml.SysUser.insert",sysUser2DTO);
            LOG.info("创建sys_user结果:{}",insert_count);
        }catch (Exception e){
            LOG.error("新增用户出现错误：{}",sysUser2DTO,e);
            return ResultBuild.fail("用户重复，请检查用户名~");
        }
        return ResultBuild.SUCCESS();
    }


    /**
     * 系统::用户信息表::修改
     *
     * @param SysUser2(or Map) 系统::用户信息表
     * @return 更新条数
    */
    //@Override
    public MeeResult update(SysUser sysUser2){
      LOG.info("接收到参数 {}",sysUser2);
      if(null == sysUser2 ||null==sysUser2.getId()||null==sysUser2.getUser_name()|| null==sysUser2.getStatus() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();

      // 通用字段
      sysUser2.setUpdate_time(now);
      sysUser2.setUpdate_by(user_id);

      int update_count = dbSQLDao.update("com.mee.xml.SysUser.update",sysUser2);
      LOG.info("已更新系统::用户信息表明细：{}->{}条",sysUser2,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 系统::用户信息表::删除
     *
     * @id 系统::用户信息表 主键
     * @return 删除条数
    */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public MeeResult deleteById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
          LOG.error("必要参数为空:{}",id);
          return ResultBuild.fail("必要参数为空[id]");
      }
      if( "1".equals(id) || "2".equals(id)){
          return ResultBuild.fail("指定用户不可删除![admin]");
      }

      // 删除用户角色关系
      Map<String,Object> param = new HashMap<String,Object>(4,1);
      param.put("user_id",id);
      int delete_role_user_count = dbSQLDao.delete("com.mee.xml.SysRoleUser.deleteByUserId",param);

      // 删除用户
      param.clear();
      param.put("id",id);
      param.put("del_flag",DeleteFlagEnum.DELETED.code);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysUser.deleteById", param);

      LOG.info("已删除用户{}=>{}条,角色用户{}条",id,delete_count,delete_role_user_count);
      return ResultBuild.build( delete_count );
    }

    /**
     * 系统::用户信息表::批量删除
     *
     * @ids 系统::用户信息表 主键集合
     * @return 删除条数
    */
    public MeeResult deleteBatch(String[] ids){
      if( null==ids || 0==ids.length ){
        LOG.error("必要参数为空:{}",ids);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("list",ids);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysUser.deleteBatch", param);
      LOG.info("已删除记录{}->{}条",ids,delete_count);
      return ResultBuild.build(delete_count);
    }

    public MeeResult changeStatus(SysUser sysUser2) {
        // 参数校验
        if(null == sysUser2 || null==sysUser2.getId() || null==sysUser2.getStatus()){
            return ResultBuild.FAIL();
        }
        final String status = sysUser2.getStatus();
        sysUser2.setStatus(StatusEnum.INVALID.code.equals(status)?StatusEnum.VALID.code :StatusEnum.INVALID.code );
        final int update_count = dbSQLDao.update("com.mee.xml.SysUser.changeStatus",sysUser2);
        LOG.info("已更新状态 to => {},{}条",sysUser2,update_count);
        return ResultBuild.SUCCESS();
    }

    /**
     * 重置登录密码
     * @param id 用户id
     * @param pwd 密码
     * @return
     */
    public MeeResult resetPwd(String id, String pwd) {
        if( null==id || null==pwd || "".equals(pwd) ||"".equals(pwd)  ){
            return ResultBuild.fail("必要参数为空[id,pwd]");
        }
        String dec_pwd = ChaosUtil.dec(pwd);
        if( null==dec_pwd || "".equals(dec_pwd) ){
           return ResultBuild.fail("密码不符合规范!");
        }
        Map<String,Object> query_param = new HashMap<>(2);
        query_param.put("id",id);
        final SysUser sysUser2 = dbSQLDao.findOne("com.mee.xml.SysUser.findById", query_param);
        if( null==sysUser2 ){
            return ResultBuild.fail("该用户不存在!");
        }
        final String user_name = sysUser2.getUser_name();
        String enc_pwd = new Sha512Hash(dec_pwd,id,3).toString();
        final LocalDateTime now = DateUtil.now();
        final String user_id = ShiroUtils.getUserId();
        Map<String,Object> param = new HashMap<>(8,1);
        param.put("id",id);
        param.put("password",enc_pwd);
        param.put("pwd_reset_time",now);
        param.put("update_time",now);
        param.put("update_by",user_id);
        int update_count = dbSQLDao.update("com.mee.xml.SysUser.updatePwd", param);
        LOG.info("已更新用户{}密码{}条",id,update_count);
        // 重置登录异常次数
        shiroAccountLockedService.clearCounter(user_name);
        return ResultBuild.build(update_count);
    }

    /**
     * 获取用户角色信息
     * @param userId 用户ID
     * @return 角色信息
     */
    public MeeResult getRoles(String user_id) {
        Map<String,String> param = new HashMap<>(2,1);
        param.put("user_id",user_id);
        List<Object> result = dbSQLDao.find("com.mee.xml.SysRole.findByUserId", param);
        return ResultBuild.build(result);
    }

    /**
     * 导入数据（仅测试）
     * @param file 文件
     * @param name 名称
     * @return
     */
    public MeeResult doImport(@RequestParam(value = "file",required = true) MultipartFile file,
                        @RequestParam(value = "name",required = false) String name){
        try{
            if( file.isEmpty() || !file.getOriginalFilename().endsWith(".xlsx")){
                return ResultBuild.fail("文件异常...");
            }
            LOG.info("导入文件{},name={}",file.getOriginalFilename(),name);
            return ResultBuild.SUCCESS();
        }catch(Exception ex){
            LOG.error("上传发生错误:",ex);
            return ResultBuild.fail("上传发生错误...[数据有重复]");
        }
    }

    /**
     * 导出数据
     * @param response  response
     * @param page_no   page_no
     * @param page_size page_size
     * @param user_name user_name
     * @param nick_name nick_name
     * @param phone phone
     * @param email email
     * @param status    status
     * @param del_flag  del_flag
     */
    public void doExport(HttpServletResponse response,
                         @RequestParam(required = true)Integer page_no,
                         @RequestParam(required = true)Integer page_size,
                         String user_name,String nick_name,String phone,
                         String email,String status,String del_flag){
        LOG.info("导出接收到参数 {},{}, {},{},{},{},{}",page_no,page_size ,user_name,phone,email,status,del_flag);
        Map<String,Object> param = new HashMap<String,Object>(16,1);
        param.put("user_name",null==user_name||"".equals(user_name)?null:"%"+user_name+"%" );
        param.put("nick_name",null==nick_name||"".equals(nick_name)?null:"%"+nick_name+"%" );
        param.put("phone",null==phone||"".equals(phone)?null:"%"+phone+"%" );
        param.put("email",null==email||"".equals(email)?null:"%"+email+"%" );
        param.put("status",null==status||"".equals(status)?null:status );
        param.put("del_flag",DeleteFlagEnum.NORMAL.code );
        Page list = dbSQLDao.list("com.mee.xml.SysUser.findList",param,page_no,page_size>100000?100000:page_size );
        List<SysUser> data_list = list.getData();

        String[] data_field = { "id","dept_id","user_name","nick_name","gender","phone","email","register_date","last_login_date","pwd_reset_time","status","del_flag","create_time","create_by","update_time","update_by" };
        String[] header_field= { "用户ID\nid","部门ID\ndept_id","用户名称\nuser_name","用户昵称\nnick_name","性别","手机号码","email","用户注册时间","最后登录时间","密码最后重置时间","状态1.启用 0.禁用","删除标记1.正常 0.删除","创建时间","创建人","创建时间","创建人" };

        File file = ExcelWriteUtil.toXlsxByObj( data_list,header_field,data_field);
        ResultBuild.toResponse(response,file,"用户列表01.xlsx");
    }


}
