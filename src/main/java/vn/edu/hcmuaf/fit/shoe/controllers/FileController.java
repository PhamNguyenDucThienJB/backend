package vn.edu.hcmuaf.fit.shoe.controllers;

import vn.edu.hcmuaf.fit.shoe.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    @Autowired
    FileService fileService ;
    @RequestMapping(value = "/thumbnail/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> readDetailFileThumbnail(@PathVariable String filename){

        System.out.println(filename);
        try{
            byte[] bytes = fileService.readFileContentThumbnail(filename);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e){
            return ResponseEntity
                    .noContent().build();
        }

    }
    @RequestMapping(value = "/image/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> readDetailFileImage(@PathVariable String filename){
        try{
            byte[] bytes = fileService.readFileContentImage(filename);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e){
            return ResponseEntity
                    .noContent().build();
        }

    }

}
