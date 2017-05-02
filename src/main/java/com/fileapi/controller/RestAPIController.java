package com.fileapi.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileapi.entity.FileUploaded;
import com.fileapi.service.FileService;
import com.fileapi.util.Response;

@RestController 
@RequestMapping("/api")
public class RestAPIController {
	
	public static final Logger logger = LoggerFactory.getLogger(RestAPIController.class);
	
	@Autowired
	FileService fileService;

	/**
	 * retrieve file by name
	 */
	@RequestMapping(value = "/file/{name}", produces = "application/json")
	public ResponseEntity<?> getFileMetaDateByName(@PathVariable("name") String name) { 
		logger.info("Fetching File with name {}", name);
		FileUploaded metadata = fileService.findByName(name);
		if(metadata == null) {
			logger.error("file with name {} not found.",name);
			return new ResponseEntity<Response>(new Response("File with name " + name
					+ " not found"),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FileUploaded>(metadata, HttpStatus.OK);
	}

	/**
	 * upload name
	 */
	@RequestMapping(value = "/file/", produces = "application/json")
	public ResponseEntity<?> saveFile(@RequestParam MultipartFile file) {
		logger.info("Creating File : {}", file);
		FileUploaded matedata = new FileUploaded();
		matedata.setName(file.getOriginalFilename().replaceFirst("[.][^.]+$", "")); // removing extention
		matedata.setSize(file.getSize());
		matedata.setTimeUploaded(new Date());
		System.out.println(matedata);
		fileService.saveFile(file,matedata);
		return new ResponseEntity<Response>(new Response("file uploaded successful!"),HttpStatus.CREATED);
	}
}