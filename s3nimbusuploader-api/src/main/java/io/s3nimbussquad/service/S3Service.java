package io.s3nimbussquad.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    String uploadToS3Bucket(MultipartFile multipartFile) throws IOException;

    public String uploadByteStreamDataToS3Bucket(byte[] byteData) throws IOException;
}