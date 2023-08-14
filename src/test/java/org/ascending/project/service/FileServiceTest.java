package org.ascending.project.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.ascending.project.ApplicationBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Autowired
    private AmazonS3 s3Client;

    @Mock
    private MultipartFile file;

    @Test
    public void uploadFileTest_happyPath() throws IOException {
//        File file = new File("/file.txt");
        fileService.UploadFile("car-insurance-customer-beproject", file);
        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class));
    }

    @Test
    public void uploadFileTest_fileIsNull(){

    }

}
