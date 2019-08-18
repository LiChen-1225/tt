package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	/**
	 * 根据分页信息查询商品数据
	 */
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		Integer total = itemMapper.selectCount(null);  //s查询商品的总记录数
		Integer start = (page - 1) * rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows);
		return new EasyUITable(total, itemList);
	}

	/**
	 * 新增商品数据信息
	 * 		商品详情信息
	 */
	@Transactional  //事务控制
	@Override
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	/**
	 * 更新商品数据信息
	 * 		商品详情信息
	 */
	@Transactional  //事务控制
	@Override
	public void updateItem(Item item,ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		
		itemDesc.setItemId(item.getId()).setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
	}

	/**
	 * 批量更新商品状态信息
	 */
	@Override
	public void updateStatus(Long[] ids, Integer status) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		List<Long> idList = Arrays.asList(ids);
		updateWrapper.in("id", idList);
		itemMapper.update(item, updateWrapper);
	}

	/**
	 * 根据商品id查找数据库中商品详情信息
	 */
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectById(itemId);
	}

	/**
	 * 根据商品id 批量删除商品信息
	 * 		同时删除2张数据库表的信息    商品表  商品详情表
	 * 			商品详情表信息的删除利用xml文件实现删除
	 */
	@Transactional  //事务控制
	@Override
	public void deleteItems(Long[] ids) {
		List<Long> idsList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idsList);
		itemDescMapper.deleteItems(ids);
	}
	
	
	
}
