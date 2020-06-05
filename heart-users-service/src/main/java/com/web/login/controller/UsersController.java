package com.web.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.heart.pojo.PageResult;
import com.web.heart.pojo.Result;
import com.web.heart.pojo.Users;
import com.web.login.service.UsersService;


@RestController//@Controller+@ResponseBody
@CrossOrigin//解决ajax跨域访问的问题
public class UsersController {
	@Autowired
	private UsersService usersService;
	//查询所有用户
	@RequestMapping("/loadAllUsers")
	public List<Users> loadAllUsers(){
		return usersService.findAll();
	}
	//根据ID查询
	@RequestMapping("/loadOneUsers/{id}")
	public Users findOneUsers(@PathVariable Integer id) {
		return usersService.findOne(id);
		
	}
		
	//增加
		@RequestMapping(value="/addUsers",method=RequestMethod.POST)	
		//@ResponseBody java类型-->json
		//@RequestBody  json-->java类型
		public Result addUsers(@RequestBody Users users){
			System.out.println(users.getAccount());
			System.out.println(users.getPass());
			System.out.println(users.getIden());

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
		public Result updateUsers(@RequestBody Users users){
			System.out.println(users.getAccount());
			System.out.println(users.getPass());
			System.out.println(users.getIden());

			
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
		@RequestMapping("/loadByAccount/{account}")
		public Users findByAccount(@PathVariable String account) {
			return usersService.findByAccount(account);
			
		}
}
