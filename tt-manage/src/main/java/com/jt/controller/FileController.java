package com.jt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.EasyUIImage;
@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	/**
	 *   文件上传,重定向到上传页面
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/file")
	public String file(MultipartFile fileImage) throws IllegalStateException, IOException {
		File dirFile = new File("D:/1-JT/tt-image");
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String fileName = fileImage.getOriginalFilename();
		File file = new File("D:/1-JT/tt-image"+fileName);
		fileImage.transferTo(file);
		return "redirect:/file.jsp";
	}
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public EasyUIImage uploadFile(MultipartFile uploadFile) {
		return fileService.uploadFile(uploadFile);
	}

}
