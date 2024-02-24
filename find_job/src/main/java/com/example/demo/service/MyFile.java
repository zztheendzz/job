package com.example.demo.service;

import java.io.File;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


public class MyFile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	  private MultipartFile multipartFile;
	  private String description;
	  public MultipartFile getMultipartFile() {
	    return multipartFile;
	  }
	  public String uploadFile( MyFile myFile) {
		  String path = "";
		    try {
		      MultipartFile multipartFile = myFile.getMultipartFile();
		      String fileName = multipartFile.getOriginalFilename();
		      File file = new File(this.getFolderUpload(), fileName);
		      path = "\\assets\\images\\"+fileName;
		      multipartFile.transferTo(file);
		    } catch (Exception e) {
		      e.printStackTrace();

		    }
		    return path;
		  }
	  
	  public File getFolderUpload() {
			/*
			 * File sau khi được upload, file sẽ được lưu vào folder {user_folder}/Uploads.
			 */
		  	String path2 = "C:\\Users\\T\\eclipse-workspace\\ASS2\\src\\main\\resources\\static\\assets\\images";
		    File folderUpload = new File(path2);
		    if (!folderUpload.exists()) {
		      folderUpload.mkdirs();
		    }
		    return folderUpload;
		  }
	  public void setMultipartFile(MultipartFile multipartFile) {
	    this.multipartFile = multipartFile;
	  }
	  public String getDescription() {
	    return description;
	  }
	  public void setDescription(String description) {
	    this.description = description;
	  }

}
