package com.ecg.system.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecg.system.pojo.Jiance;
import com.ecg.system.pojo.PageResult;
import com.ecg.system.pojo.Result;
import com.ecg.system.service.UsersService;


@RestController
@CrossOrigin
public class JianceController {
	
	

	@Autowired
	private UsersService usersService;
	
	//查询所有用户
		@RequestMapping("/loadAllUsers")
		public List<Jiance> loadAllUsers(){
			return usersService.findAll();
		}
		//根据ID查询
		@RequestMapping("/loadOneUsers/{id}")
		public Jiance findOneUsers(@PathVariable Integer id) {
			return usersService.findOne(id);
			
		}
			
		//增加
			@RequestMapping(value="/addUsers",method=RequestMethod.POST)	
			//@ResponseBody java类型-->json
			//@RequestBody  json-->java类型
			public Result addUsers(@RequestBody Jiance users){
				System.out.println(users.getName());
				System.out.println(users.getPhone());
				System.out.println(users.getAge());
				System.out.println(users.getSex());
				System.out.println(users.getPic());
				System.out.println(users.getZhenduan());

				Result result=new Result();
				try {
					usersService.addUsers(users);
					result.setSuccess(true);
				} catch (Exception e) {
					result.setSuccess(false);
					e.printStackTrace();
				}
				return result;	
				
			}
			
			//批量删除
			@RequestMapping(value="/deleteUsers",method=RequestMethod.GET)	
			//@ResponseBody java类型-->json
			//@RequestBody  json-->java类型
			public Result deleteUsers(Integer[] ids){
				//System.out.println(ids[0]);
				
				Result result=new Result();
				try {
					usersService.deleteUsers(ids);
					result.setSuccess(true);
				} catch (Exception e) {
					result.setSuccess(false);
					e.printStackTrace();
				}
				return result;	
			}
			
			//更新
			@RequestMapping(value="/updateUsers",method=RequestMethod.POST)	
			//@ResponseBody java类型-->json
			//@RequestBody  json-->java类型
			public Result updateUsers(@RequestBody Jiance users){
				System.out.println(users.getName());
				System.out.println(users.getPhone());
				System.out.println(users.getAge());
				System.out.println(users.getSex());
				System.out.println(users.getPic());
				System.out.println(users.getZhenduan());

				
				Result result=new Result();
				try {
					usersService.updateUsers(users);
					result.setSuccess(true);
				} catch (Exception e) {
					result.setSuccess(false);
					e.printStackTrace();
				}
				return result;	
			}
			//分页
			@RequestMapping("/findUsersByPage")	
			public PageResult findUsersByPage(int pageNo,int pageSize){
				return usersService.findUsersByPage(pageNo, pageSize);
				
			}
			
			//根据账号进行条件查询
			@RequestMapping("/loadByPhone/{phone}")
			public Jiance findByAccount(@PathVariable String phone) {
				return usersService.findByAccount(phone);
				
			}
			


}
