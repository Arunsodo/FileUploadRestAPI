package com.fileapi.dao;

import org.springframework.web.multipart.MultipartFile;

import com.fileapi.entity.FileUploaded;

public interface FileDAO {
	
	FileUploaded findByName(String name);
	
	boolean isFileExist(String name);
	
	void saveFile(FileUploaded metadata);
}
