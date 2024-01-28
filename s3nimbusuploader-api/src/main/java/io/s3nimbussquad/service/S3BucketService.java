package io.s3nimbussquad.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3BucketService {
    String uploadToS3Bucket(MultipartFile multipartFile) throws IOException;
    String logFileUploadDetails();
}