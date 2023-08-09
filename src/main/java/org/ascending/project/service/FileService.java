package org.ascending.project.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String clientRegion = "us-east-1";
    String bucketName = "car-insurance-customer-beproject";
    String stringObjKeyName = "sampleFile.txt";


    @Autowired
    AmazonS3 s3Client;
    public void UploadFile(File file) {
        if (file == null){
            logger.error("Cannot upload a null file");
            return;
        }

        try {
            s3Client.putObject(bucketName, file.getName(), file);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}
