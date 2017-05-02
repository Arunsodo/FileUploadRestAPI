package com.fileapi.service;

import org.springframework.web.multipart.MultipartFile;

import com.fileapi.entity.FileUploaded;
import com.fileapi.util.Response;

public interface FileService {
	
	FileUploaded findByName(String name);
	
	boolean isFileExist(FileUploaded file);
	
	Response saveFile(MultipartFile file, FileUploaded metadata);
}
