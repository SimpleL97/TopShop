package ua.com.funnybus.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder{
		NEW,ACT,AID
	}
	
	boolean write(Folder folder, MultipartFile file, int id);
}