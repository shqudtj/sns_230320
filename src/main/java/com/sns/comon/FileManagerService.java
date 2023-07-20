package com.sns.comon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

	// 실제 업로드가 된 이미지가 저장될 경로(서버의 주소)
	// 학원용
	public static final String FILE_UPLOAD_PATH = "D:\\NBS\\6_spring_project\\sns\\workspace\\images/";
	// 집윈도우용
	//public static final String FILE_UPLOAD_PATH = "E:\\개발\\6_sns\\workspace\\images/";
	// 맥용
	// public static final String FILE_UPLOAD_PATH = "E:\\개발\\6_sns\\workspace\\images/";
	
	// input: MultipartFile(이미지파일), loginId
	// output: web image path(String)
	public String saverFile(String loginId, MultipartFile file) {
		// 파일 디렉토리(=폴더)		예) aaaa(아이디)_167845645(현재시간 밀리세컨초단위)/sun(파일명).png
		String directoryName = loginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		// 폴더 생성
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			// 폴더생성 실패시 이미지 경로를 null로 리턴
			return null;
		}
		
		// 파일 업로드: byte 단위 업로드
		// byte[] byte = file.getBytes(); 를 try/catch한다
		try {
			byte[] bytes = file.getBytes();
			// ★★★ 한글 이름 이미지는 올릴 수 없으므로 나중에 영문자로 바꿔서 올리기
			Path path = Paths.get(filePath + file.getOriginalFilename()); // 디렉토리경로 + 사용자가 올린 파일명
			Files.write(path, bytes);	// 파일 업로드
		} catch (IOException e) {	
			e.printStackTrace();	// 콘솔에 에러내용을 뜨게 하는 것 => 즉 위 행위가 실패했다는 뜻
			return null; 	// 이미지 업로드 실패했으니 오류나지않게 null로 리턴을 함
		}
		
		// 파일 업로드를 성공했으면 웹 이미지 url path를 리턴
		// 예) /images/aaaa_1689861196123/tavern-7411977_1280.jpg
		return "/images/" + directoryName + file.getOriginalFilename();
	}
	
}



