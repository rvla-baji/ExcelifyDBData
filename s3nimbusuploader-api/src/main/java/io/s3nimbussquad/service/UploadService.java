package io.s3nimbussquad.service;

public interface UploadService {
    String uploadToS3Bucket();
    String logFileUploadDetails();
}