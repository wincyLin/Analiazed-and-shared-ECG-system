package com.ecg.lunbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecg.lunbo.service.LunboService;
import com.ecg.system.pojo.Category;
import com.ecg.system.pojo.Lunbo;
import com.ecg.system.pojo.PageResult;
import com.ecg.system.pojo.Result;


@RestController
@CrossOrigin
public class LunboController {

	@Autowired
	private LunboService lunboService;
	
	
	@RequestMapping("/findByCategoryId")
	public List<Lunbo> findByCategoryId(Long categoryId){
		return lunboService.findByCategoryId(categoryId);
	}
	
	@RequestMapping("/findAllContent")
	public List<Lunbo> findAllContent(){
		return lunboService.findAllContent();
	}
	
	@RequestMapping("/findByPage")
	public PageResult findByPage(Integer pageNum, Integer pageSize){			
		return lunboService.findByPage(pageNum, pageSize);
	}
	
	@RequestMapping("/addContent")
	public Result addContent(@RequestBody Lunbo content){
		try {
			lunboService.addContent(content);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	@RequestMapping("/updateContent")
	public Result updateContent(@RequestBody Lunbo content){
		try {
			lunboService.updateContent(content);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	@RequestMapping("/findAllCategory")
	public List<Category> findAllCategory(){			
		return lunboService.findAllCategory();
	}
	
	@RequestMapping("/findOneCategory")
	public Category findOneCategory(Long id){
		return lunboService.findOneCategory(id);		
	}

	
	@RequestMapping("/findOneContent")
	public Lunbo findOneContent(Long id){
		return lunboService.findOneContent(id);		
	}
	
	@RequestMapping("/deleteContent")
	public Result deleteContent(Long [] ids){
		
			return lunboService.deleteContent(ids);
		
	}
	
//	@RequestMapping("/searchContent")
//	public PageResult searchContent(@RequestBody Lunbo content, Integer pageNum, Integer pageSize){
//		return lunboService.findByPage(content, pageNum, pageSize);		
//	}
//	
	
}
