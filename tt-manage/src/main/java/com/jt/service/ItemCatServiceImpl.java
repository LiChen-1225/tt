package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	/**
	 * 获取商品分类名称
	 */
	@Override
	public String findItemCatNameByCatId(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat.getName();
	}

	/**
	 * 1.查询数据库中商品分类信息
	 * 2.将数据库对象转换为  VO 对象
	 */
	public List<ItemCat> findItemCatList(Long parentId){
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId);
		List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
		return itemCatList;
	}
	
	/**
	 *  查询商品分类信息列表
	 */
	@Override
	public List<EasyUITree> findEasyUITreeList(Long parentId) {
		List<ItemCat> itemCatList = findItemCatList(parentId);
		List<EasyUITree> treeList = new ArrayList<>();
		for (ItemCat itemCat : itemCatList) {
			EasyUITree easyUITree = new EasyUITree();
			//s如果是父级菜单，则节点关闭，否则开启
			String state = itemCat.getIsParent()?"closed":"open";
			easyUITree.setId(itemCat.getId()).setText(itemCat.getName()).setState(state);
			treeList.add(easyUITree);
		}
		return treeList;
	}

}
