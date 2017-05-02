package com.fileapi.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fileapi.dao.FileDAO;
import com.fileapi.entity.FileUploaded;
import com.fileapi.util.Response;
@Transactional
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileDAO fileDAO;

	@Override
	public FileUploaded findByName(String name) {
		return fileDAO.findByName(name);
	}

	@Override
	public boolean isFileExist(FileUploaded file) {
		return fileDAO.isFileExist(file.getName());
	}

	@Override
	public Response saveFile(MultipartFile file, FileUploaded metadata) {
		/**
		 * save meta-data to database
		 */
		fileDAO.saveFile(metadata);

		/**
		 * save file to disk
		 */
		try {
			File dir = new File("C:/FileUpload/");
			String name = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			String path = dir.getAbsolutePath() + File.separator + name;
			File savedFile = new File(path);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(savedFile));
			stream.write(bytes);
			stream.close();

			return new Response("Successfully uploaded file: " + name);
		} catch (Exception e) {
			return new Response("Failed to upload file: " + e.getMessage());
		}
	}

}
