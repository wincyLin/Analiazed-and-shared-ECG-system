package com.ecg.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecg.system.mapper.JianceMapper;
import com.ecg.system.pojo.Jiance;
import com.ecg.system.pojo.PageResult;
import com.ecg.system.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;




@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private JianceMapper jianceMapper;

	//查询所有用户
		@Override
		public List<Jiance> findAll() {
			List<Jiance>list= jianceMapper.selectByExample(null);
			return list;
		}
		//根据ID查询某一用户
		@Override
		public Jiance findOne(Integer id) {
			return jianceMapper.selectByPrimaryKey(id);
		}
		//增加用户
		@Override
		public void addUsers(Jiance users) {
			jianceMapper.insert(users);

		}
		//批量删除用户
		@Override
		public void deleteUsers(Integer[] ids) {
			for (Integer id : ids) {
				jianceMapper.deleteByPrimaryKey(id);
			}

		}
		//修改更新
		@Override
		public void updateUsers(Jiance users) {
			jianceMapper.updateByPrimaryKey(users);
			
		}
		
		//分页
			@Override
			public PageResult findUsersByPage(int pageNo, int pageSize) {
				PageResult pageResult=new PageResult();
				//分页第一步：设置mybatis的分页拦截，然后重构sql分页语句
				PageHelper.startPage(pageNo, pageSize);
				List<Jiance>list=jianceMapper.selectByExample(null);
				//分页的第二步骤：获取分页bean，里面提供分页所需参数
				PageInfo pageInfo=new PageInfo<>(list);
				
				pageResult.setRows(list);
				pageResult.setTotal(pageInfo.getTotal());
				
				return pageResult;
			}
			
			//根据账号进行条件查询
			@Override
			public Jiance findByAccount(String phone) {
				return jianceMapper.selectByOne(phone);
			}
	

}
