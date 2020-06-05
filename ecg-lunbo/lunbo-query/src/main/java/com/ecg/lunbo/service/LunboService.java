package com.ecg.lunbo.service;

import java.util.List;

import com.ecg.system.pojo.Category;
import com.ecg.system.pojo.Lunbo;
import com.ecg.system.pojo.PageResult;
import com.ecg.system.pojo.Result;

public interface LunboService {
	

	public List<Lunbo> findAllContent();
	
	public PageResult findByPage(int pageNum,int pageSize);
	
	public void addContent(Lunbo content);
	
	public void updateContent(Lunbo content);
	
	public Lunbo findOneContent(Long id);
	
	public Result deleteContent(Long[] ids);
	
	public List<Lunbo> findByCategoryId(Long categoryId);
	
	public PageResult findByPage(Lunbo content,int pageNum,int pageSize);
	
	public List<Category> findAllCategory();
	
	public Category findOneCategory(Long id);
	
}
