package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	public ItemCatService itemCatService;
	
	/**
	 * 根据商品分类Id 查询商品分类名称
	 */
	@RequestMapping("/queryItemName")
	public String findItemCatNameByCatId(Long itemCatId) {
		return itemCatService.findItemCatNameByCatId(itemCatId);
	}
	
	/**
	 * 展示商品分类信息列表
	 *    defaultValue: 当参数为null时，设定默认值
	 *    name/value: 获取用户传递的数据信息
	 *    required：  是否为必须传递的数据
	 */
	@RequestMapping("/list")
	public List<EasyUITree> findItemCatList(@RequestParam(defaultValue = "0",name = "id") Long parentId){
		//s查询一级商品分类信息
		return itemCatService.findEasyUITreeList(parentId);
	}
}
