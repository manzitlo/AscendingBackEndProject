package org.ascending.project.service;

import org.ascending.project.ApplicationBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void uploadFileTest() {
        File file = new File("/Users/wenzheluo/Desktop/WechatIMG17.jpeg");
        fileService.UploadFile(file);
    }

}
