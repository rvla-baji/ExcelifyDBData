package io.s3nimbussquad.controller;

import io.s3nimbussquad.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/nimbusDataUpload")
public class NimbusUploadController {

    private S3Service s3Service;

    @Autowired
    NimbusUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/uploadToS3Bucket")
    public ResponseEntity<String> uploadXlsFileToS3Bucket(@RequestParam("multipartFile") MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        try {
            if (!multipartFile.isEmpty()) {
                String uuid = s3Service.uploadToS3Bucket(multipartFile);
                String responseMessage = "File " + filename + " Uploaded Successfully.\n UUID: " + uuid;
                return ResponseEntity.ok(responseMessage);
            } else {
                return ResponseEntity.status(500).body("Please send file which consists data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload the file");
        }
    }
}