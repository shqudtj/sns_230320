package com.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.comon.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")	// 웹 이미지 path 예) http://localhost/images/aaa_1644651416//sun.png
		// 윈도우용 /// 3개
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);
		// 맥용 // 2개
		//.addResourceLocations("file://" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일 위치 맥은 // 2개 윈도우는 /// 3개
	}
	
}
