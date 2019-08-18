package com.jt.thro;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

@RestController     //定义全局异常处理
@Slf4j              //引入日志API
public class SysResultControllerAdvice {

	//当发生何种类型异常时使用该处理方式
	@ExceptionHandler(RuntimeException.class)
	public SysResult sysResultException(Exception exception) {
		exception.printStackTrace();
		log.error("服务器异常:"+exception.getMessage());
		return SysResult.fail();
	}
}
