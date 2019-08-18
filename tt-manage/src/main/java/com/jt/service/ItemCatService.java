package com.jt.service;

import java.util.List;
import com.jt.vo.EasyUITree;

public interface ItemCatService {

	String findItemCatNameByCatId(Long itemCatId);

	List<EasyUITree> findEasyUITreeList(Long parentId);

}
