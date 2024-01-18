package io.s3nimbussquad.service.impl;


import io.s3nimbussquad.service.S3BucketService;
import org.springframework.stereotype.Service;

@Service
public class S3BucketServiceImpl implements S3BucketService {
    @Override
    public String uploadToS3Bucket() {
        return "File uploaded successfully";
    }

    @Override
    public String logFileUploadDetails() {
        return "log entered into DB";
    }
}