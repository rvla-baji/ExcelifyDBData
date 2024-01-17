package io.s3nimbussquad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/nimbusUpload")
public class NimbusUploadController {
    @PostMapping("/s3bucket")
    public ResponseEntity<String> uploadXlsFileToS3Bucket(MultipartFile multipartFile) {

        return new ResponseEntity<>(
                "File Uploaded Successfully", HttpStatus.OK);
    }
}