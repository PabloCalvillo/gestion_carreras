package com.proyecto.pablocalvillo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.pablocalvillo.service.impl.FileServiceImpl;

@Controller
@RequestMapping("/")
public class FileController {
	
	@Autowired
	@Qualifier("fileServiceImpl")
	private FileServiceImpl fileServiceImpl;
	
	@GetMapping("/")
	private ModelAndView files() {
		return new ModelAndView("addFoto");
	}
	
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
        	return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
        }
        try {
			fileServiceImpl.saveFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new ResponseEntity<Object>("Archivo seleccionado correctamente", HttpStatus.OK);
    }
}
