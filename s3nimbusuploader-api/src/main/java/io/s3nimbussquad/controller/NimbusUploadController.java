package io.s3nimbussquad.controller;

import io.s3nimbussquad.service.S3BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/nimbusUpload")
public class NimbusUploadController {


    private S3BucketService s3BucketService;

    @Autowired
    public NimbusUploadController(S3BucketService s3BucketService)
    {
        this.s3BucketService=s3BucketService;
    }

    @PostMapping("/s3bucket")
    public ResponseEntity<String> uploadXlsFileToS3Bucket(MultipartFile multipartFile) {

        return new ResponseEntity<>(
                "File Uploaded Successfully", HttpStatus.OK);
    }
}