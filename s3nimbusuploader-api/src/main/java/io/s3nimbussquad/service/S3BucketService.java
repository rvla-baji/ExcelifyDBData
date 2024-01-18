package io.s3nimbussquad.service;

public interface S3BucketService {
    String uploadToS3Bucket();
    String logFileUploadDetails();
}