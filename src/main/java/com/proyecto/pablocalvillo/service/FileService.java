package com.proyecto.pablocalvillo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public abstract String saveFile(MultipartFile file) throws IOException;
	public abstract void removeFile(String name);
}
