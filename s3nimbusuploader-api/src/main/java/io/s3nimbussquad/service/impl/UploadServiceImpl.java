package io.s3nimbussquad.service.impl;

import io.s3nimbussquad.service.UploadService;

public class UploadServiceImpl implements UploadService {
    @Override
    public String uploadToS3Bucket() {
        return "File uploaded successfully";
    }

    @Override
    public String logFileUploadDetails() {
        return "log entered into DB";
    }
}