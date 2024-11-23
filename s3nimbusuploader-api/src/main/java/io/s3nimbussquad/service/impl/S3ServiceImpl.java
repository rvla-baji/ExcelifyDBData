package io.s3nimbussquad.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import io.s3nimbussquad.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class S3ServiceImpl implements S3Service {

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.region}")
    private String awsRegion;

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

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(byteArrayOutputStream.size());
        metadata.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        amazonS3.putObject(new PutObjectRequest(bucketName, keyName, multipartFile.getInputStream(), metadata));

        return UUID.randomUUID().toString();
    }

    @Override
    public String uploadByteStreamDataToS3Bucket(byte[] byteData) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(byteData);

        String keyName = "Xls Files/Org_Emp_DetailsV2.xlsx";

        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion(awsRegion)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKeyId, awsSecretKey)))
                .build();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(byteArrayOutputStream.size());
        metadata.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        amazonS3.putObject(new PutObjectRequest(bucketName, keyName, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), metadata));
        System.out.println("RestClient --> byte[] data successfully uploaded to S3 destination bucket");
        return UUID.randomUUID().toString();
    }

}