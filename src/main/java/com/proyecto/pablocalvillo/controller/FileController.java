package com.proyecto.pablocalvillo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.pablocalvillo.service.impl.FileServiceImpl;

@Controller
@RequestMapping("/cars/file")
public class FileController {
	
	@Autowired
	@Qualifier("fileServiceImpl")
	private FileServiceImpl fileServiceImpl;
	
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileServiceImpl.saveFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getName();
    }
	
	

}
