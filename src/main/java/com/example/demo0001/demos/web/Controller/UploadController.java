package com.example.demo0001.demos.web.Controller;

import com.example.demo0001.demos.web.pojo.Result;
import com.example.demo0001.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件名字：{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件的url：{}",url);
        return Result.success(url);
    }
}
