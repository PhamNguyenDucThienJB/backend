package vn.edu.hcmuaf.fit.shoe.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    public byte[] readFileContentImage(String fileName) {
        try {
            Path file = Paths.get("src/main/resources/static/image").resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()|| resource.isReadable()){
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }else{
                throw new RuntimeException("Could not read file "+fileName);
            }
        } catch (Exception e){
            throw new RuntimeException("Could not read file "+fileName,e);
        }
    }
    public byte[] readFileContentThumbnail(String fileName) {
        try {
            Path file = Paths.get("src/main/resources/static/thumbnail").resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()|| resource.isReadable()){
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }else{
                throw new RuntimeException("Could not read file "+fileName);
            }
        } catch (Exception e){
            throw new RuntimeException("Could not read file "+fileName,e);
        }
    }
 }
