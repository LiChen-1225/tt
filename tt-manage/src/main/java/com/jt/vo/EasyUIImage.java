package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIImage {

	private Integer error = 0;    // 1 代表上传失败
	private String url;           //s图片访问地址
	private Integer width;
	private Integer height;
}
