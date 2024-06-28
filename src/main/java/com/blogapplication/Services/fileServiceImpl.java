package com.blogapplication.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //  file name
        String fileName = file.getOriginalFilename();
        // abc.png

        //  random name generated for file
        if(fileName != null && (fileName.endsWith(".png") || fileName.endsWith(".jpg"))){
        String randomId = UUID.randomUUID().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String randomFileName = randomId.concat(fileExtension);
        
        //  fullpath
        String filePath = path + File.separator + randomFileName;

        //  create folder if not created
        File f = new File(path);
        if(!f.exists() ){
            f.mkdir();
        }

        //  file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return randomFileName;
    }
    else{
        throw new IllegalArgumentException("file name with .jpg and .png only will be accepted");
    }
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        InputStream is = new FileInputStream(filePath);  
        return is;
    }
    
}
