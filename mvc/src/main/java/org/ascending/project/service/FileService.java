package org.ascending.project.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String clientRegion = "us-east-1";
//    String bucketName = "car-insurance-customer-beproject";

    @Autowired
    AmazonS3 s3Client;

    public String UploadFile(String bucketName, MultipartFile file) throws IOException {
        if (file == null){
            logger.error("Cannot upload a null file");
            return bucketName;
        }

        PutObjectRequest request = new PutObjectRequest(bucketName,
                file.getOriginalFilename(), file.getInputStream(), null);

        s3Client.putObject(request);
        return getUrl(bucketName, file.getOriginalFilename());
    }

    private String getUrl(String bucketName, String s3Key){
        URL url = s3Client.getUrl(bucketName, s3Key);
            if (url == null) {
                logger.error("Unable to fetch URL for bucket: {} and key: {}", bucketName, s3Key);
                return null;
            }
            return url.toString();
    }
}
