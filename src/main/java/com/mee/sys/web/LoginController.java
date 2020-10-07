package com.mee.sys.web;
import com.mee.core.configuration.ShiroUtils;
import com.mee.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * @author funnyzpc
 * 主页面
 */
@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String index(){
		return "login";
	}

	/** 认证不通过走这里 **/
	@PostMapping("/login")
	public String login(SysUser sysUser){
		/*
		if(null == username || null == passsword){
			return "login";
		}
		return "redirect:main";
		 */
		String userName = sysUser.getUsername();
		sysUser.setUser_name(userName);
		Subject currentUser = SecurityUtils.getSubject();
		//是否验证通过
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token =new UsernamePasswordToken(userName,sysUser.getPassword());
			try {
				token.setRememberMe(false);
				//存入用户
				currentUser.login(token);
				SysUser user = ShiroUtils.getUser();
				if(null!=user && null!=user.getId()) {
					//跳转到 get请求的登陆方法
					//view.setViewName("redirect:/"+prefix+"/index");
					return "redirect:main";
				}else {
					return "redirect:login";
				}
			}catch (Exception e) {
				log.error("登录出现异常了: ",e);
				return "redirect:login";
			}
		}else {
			SysUser user = ShiroUtils.getUser();
			if (null != user  && null!=user.getId()) {
				//跳转到 get请求的登陆方法
				//view.setViewName("redirect:/"+prefix+"/index");
				return "redirect:main";
			} else {
				return "redirect:login";
			}
		}
		// model.addAttribute("msg","用户名或密码错误,请核实!");
		// return "main";
	}


/*	@GetMapping("/main")
	public String main(){
		return "decorator/main";
	}*/

	/*
	@RequestMapping(method=RequestMethod.POST)
	public String login(String userid, String password, HttpServletRequest request,Model m){
		IUser u = auth.getUser(userid, password);
		if (u != null){
			ServiceContext.getInstance().setUser(u);
			return "redirect:main";
		}else{
			request.getSession().invalidate();
			m.addAttribute("post", "1");
			return "login";
		}
	}
	*/
	
	@GetMapping("/logout")
	public String logout(){
		// ServiceContext.getInstance().setUser(null);
		// 退出前要清空
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:login";
	}

}
