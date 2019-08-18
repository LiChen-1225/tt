package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;


@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 根据分页查询商品信息
	 */
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page,rows);
	}
	
	/**
	 * 新增商品数据信息
	 *   全局异常处理机制
	 */
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		itemService.saveItem(item,itemDesc);
		return SysResult.success();
	}
	
	/**
	 * 实现商品数据信息的修改
	 */
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}
	
	/**
	 * 实现商品的下架操作
	 */
	@RequestMapping("/instock")
	public SysResult instock(Long[] ids) {
		Integer status = 2;  //表示下架
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}
	
	/**
	 * 实现商品的上架操作
	 */
	@RequestMapping("/reshelf")
	public SysResult reshelf(Long[] ids) {
		Integer status = 1;  //表示上架
		itemService.updateStatus(ids, status);
		return SysResult.success();
	}
	
	/**
	 * 商品详情信息展现
	 * 		根据商品id获取服务器商品详情信息数据
	 */
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId) {
		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		return SysResult.success(itemDesc);
	}
	
	/**
	 * 实现商品信息的删除操作
	 */
	@RequestMapping("/delete")
	public SysResult deleteItems(Long[] ids) {
		itemService.deleteItems(ids);
		return SysResult.success();
	}
}
