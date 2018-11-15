package com.proyecto.pablocalvillo.service.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl {

	private String upload_folder = ".//src//main//resources//static//files//";

	public String saveFile(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "";
	}
	
	public void removeFile(String name) throws IOException {
		
		Path fileToDelete = Paths.get(upload_folder + name);
		Files.delete(fileToDelete);
	}

}
