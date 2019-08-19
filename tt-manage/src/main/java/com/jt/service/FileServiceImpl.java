package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jt.vo.EasyUIImage;
@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
	
	@Value("${image.localFileDir}")
	private String localFileDir;
	@Value("${image.urlPath}")
	private String urlPath;

	/**
	 * 判断文件是否为图片
	 * 	防止恶意程序   判断固有属性
	 *	将图片分目录储存
	 *	解决文件重名问题
	 */
	@Override
	public EasyUIImage uploadFile(MultipartFile uploadFile) {
		EasyUIImage uiImage = new EasyUIImage();
		//c判断是否为图片
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			uiImage.setError(1);  //c表示非图片
			return uiImage;
		}
		
		//c判断固有属性
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			Integer height = bufferedImage.getHeight();
			Integer width =  bufferedImage.getWidth();
			if(height==0 || width==0) {
				uiImage.setError(1);
				return uiImage;
			}
			uiImage.setWidth(width).setHeight(height);
			
			//c以时间格式存储
			String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			
			String localDir = localFileDir + dateDir;
			File dirFile = new File(localDir);
			if(!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			//c生成图片名称
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String realFileName = uuid + fileType;
			
			//c文件上传
			String realPath = localDir + "/" + realFileName;
			File realFile = new File(realPath);
			uploadFile.transferTo(realFile);
			
			String url = urlPath + dateDir + "/" + realFileName;
			uiImage.setUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
			uiImage.setError(1);
		}
		
		return uiImage;
	}

}
