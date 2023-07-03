package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.sys.vo.server.Server;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
*   服务器信息
*   @className  SysServerController
*   @author     shadow
*   @date       2023/6/18 10:40 PM
*   @version    v1.0
*/
@Controller
@RequestMapping("/sys/server_info")
public class SysServerController {

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sys:server_info:list")
    @GetMapping
    public String index(){
        return "sys/server_info";
    }


    /**
     * 服务器信息
     * @return
     * @throws Exception
     */
    @RequiresPermissions("sys:server_info:list")
    @GetMapping("list")
    @ResponseBody
    public MeeResult<Server> getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return ResultBuild.build(server);
    }
}
