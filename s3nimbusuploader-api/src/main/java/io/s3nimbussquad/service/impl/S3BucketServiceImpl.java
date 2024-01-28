package io.s3nimbussquad.service.impl;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.s3nimbussquad.service.S3BucketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class S3BucketServiceImpl implements S3BucketService {
    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${file.path}")
    private String filePath;

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Override
    public String uploadToS3Bucket(MultipartFile multipartFile) throws IOException {

        byte[] excelByteStream = multipartFile.getBytes();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(excelByteStream);
        String keyName = "Xls Files/Org_Emp_Details.xlsx";

        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion(awsRegion)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKeyId, awsSecretKey)))
                .build();
        return "S3 Operation Completed";
    }

    @Override
    public String logFileUploadDetails() {
        return null;
    }
}