package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EasyUITree {

	private Long id;        //s节点的id
	private String text;    //s节点的名称
	private String state;   //s节点的开关
}
