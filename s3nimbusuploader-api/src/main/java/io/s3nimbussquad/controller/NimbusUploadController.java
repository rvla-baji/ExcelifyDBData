package io.s3nimbussquad.controller;

import io.s3nimbussquad.service.S3BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/nimbusUpload")
public class NimbusUploadController {

    S3BucketService s3BucketService;

    @Autowired
    public NimbusUploadController(S3BucketService s3BucketService) {
        this.s3BucketService = s3BucketService;
    }

    @PostMapping("/s3bucket")
    public ResponseEntity<String> uploadXlsFileToS3Bucket(@RequestParam("multipartFile") MultipartFile multipartFile) {

        try {
            if (multipartFile != null) {
                return new ResponseEntity<>(
                        "File Uploaded Successfully" + s3BucketService.uploadToS3Bucket(multipartFile), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error Occured", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>("Error Occured", HttpStatus.BAD_REQUEST);
        }

    }
}