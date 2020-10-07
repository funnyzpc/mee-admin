/**
 * 
 */
package com.mee.common.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author funnyzpc
 *
 */
@RestController
@RequestMapping("/common/project")
public class CommonProjectController {

	@PostMapping("/list")
	public Map<String,Object> list(Model m){
		return new HashMap<String,Object>(1,1){{
			put("data",new ArrayList<String>(1));
		}};
	}
}
