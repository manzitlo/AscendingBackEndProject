package org.ascending.project.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.ascending.project.ApplicationBootstrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Mock
    private AmazonS3 s3Client;

    @Mock
    private MultipartFile file;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        reset(s3Client, file);
    }

    @Test
    public void uploadFileTest_happyPath() throws IOException {
        fileService.uploadFile("car-insurance-customer-beproject", file);
        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void uploadFileTest_fileIsNull() throws IOException {
        fileService.uploadFile("car-insurance-customer-beproject", null);
    }

}

