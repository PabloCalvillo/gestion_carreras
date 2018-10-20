package com.proyecto.pablocalvillo.service.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl {
	
	private String file_folder = ".//src//main//resources//files//";
	
	public void saveFile(MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(file_folder + file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	

}
