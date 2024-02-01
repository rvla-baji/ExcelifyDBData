package io.s3nimbussquad.controller;

import io.s3nimbussquad.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping("/processByteArray")
    public ResponseEntity<String> uploadByteArray(@RequestBody byte[] byteData) throws IOException {

        if (byteData == null) {
            return new ResponseEntity<>("Please send valid data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("File Processed Successfully \n " + "  UUID: " + s3Service.uploadByteStreamDataToS3Bucket(byteData), HttpStatus.OK);
        }

    }
}