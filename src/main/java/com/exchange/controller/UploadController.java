package com.exchange.controller;

import com.exchange.entity.ReviewFile;
import com.exchange.entity.constant.FileType;
import com.exchange.service.ReviewFileService;
import com.exchange.service.impl.UploadService;
import com.exchange.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ReviewFileService reviewFileService;

    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg","video/mp4");


    /**
     * 上传图片
     * @param
     * @return
     */
    @PostMapping(value = "/image1",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse uploadImageOne(@RequestPart("file") MultipartFile file) throws IOException {
        // 1、图片信息校验
        // 1)校验文件类型
        if(!suffixes.contains(file.getContentType())){
            return ApiResponse.fail("文件类型错误");
        }

        // 2)校验图片内容
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image == null) {
            // logger.info("上传失败，文件内容不符合要求");
            return ApiResponse.fail("上传失败，文件内容不符合要求");
        }

        System.out.println(file.getOriginalFilename());
        return uploadService.uploadImage(file,-1,-1);

    }

    @PostMapping("/file")
    public ApiResponse uploadImage2(@RequestParam("file") MultipartFile[] filelist, @RequestParam("reviewId")Integer reviewId){
        int len=filelist.length;
        if(len>6){
            return ApiResponse.fail("图片最多发送5个");
        }
        for(int i=0;i<len;i++){
            if (!suffixes.contains(filelist[i].getContentType())) {
                // logger.info("上传失败，文件类型不匹配：{}", type);
                return ApiResponse.fail("有部分文件类型不符");
            }
        }

        //如果为视频
        if(filelist[0].getContentType().equals("video/mp4")){
            ReviewFile reviewFile=new ReviewFile();
            reviewFile.setFileType(FileType.VIDEO);
            reviewFile.setFilePath(uploadService.uploadVideo(filelist[0],reviewId).getMsg());
            reviewFile.setReviewId(reviewId);
            reviewFileService.addOne(reviewFile);
        }
        //如果为照片
        else{
            for (int i=0;i<filelist.length;i++){
                ReviewFile reviewFile=new ReviewFile();
                reviewFile.setFileType(FileType.IMG);
                reviewFile.setFilePath(uploadService.uploadImage(filelist[i],reviewId,i).getMsg());
                reviewFile.setReviewId(reviewId);
                reviewFileService.addOne(reviewFile);
            }
        }

        return ApiResponse.success("成功上传","成功上传");
    }

    @PostMapping("/video")
    public ApiResponse uploadVideo(@RequestParam("file") MultipartFile[] filelist, @RequestParam("reviewId")Integer reviewId) {
        System.out.println("reviewId===========");
        System.out.println(reviewId);
        int len=filelist.length;
        if(len>3){
            return ApiResponse.fail("视频最多发送3个");
        }
        for(int i=0;i<len;i++){
            if (!filelist[i].getContentType().equals("video/mp4")) {
                // logger.info("上传失败，文件类型不匹配：{}", type);
                return ApiResponse.fail("有部分文件类型不符");
            }
        }
        return ApiResponse.success("成功上传"+filelist.length+"个视频");
    }

}

