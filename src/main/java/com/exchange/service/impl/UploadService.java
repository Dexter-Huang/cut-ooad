package com.exchange.service.impl;

import com.exchange.vo.ApiResponse;
import com.exchange.config.COSProperties;
import com.exchange.dto.ReviewFileDto;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author ：cjy
 * @description ：
 * @CreateTime ：Created in 2019/9/7 16:09
 */
@Service
@Slf4j
public class UploadService {
    String key;

    @Autowired
    private COSClient cosClient;
    @Autowired
    private COSProperties cosProperties;

    // 支持的文件类型
//    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg");
    public ReviewFileDto uploadReviewImage(MultipartFile file) {

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            UUID uuid = UUID.randomUUID();
            // 指定要上传到 COS 上对象键 此key是文件唯一标识
            key = uuid.toString().replace("-","")+".jpg";
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosProperties.getBucketName(), key, file.getInputStream(),objectMetadata);

            //使用cosClient调用第三方接口
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            log.info(putObjectRequest+"");
            //返回路径

        }catch (Exception e){
            e.printStackTrace();
        }
        //拼接返回路径
        String imagePath = "https://" + cosProperties.getBucketName() + ".cos." + cosProperties.getRegionName() + ".myqcloud.com/" + key;
        return ReviewFileDto.buildSuccess(imagePath,key);
    }


    public ApiResponse uploadImage(MultipartFile file, Integer reviewId, Integer index) {

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            UUID uuid = UUID.randomUUID();
            // 指定要上传到 COS 上对象键 此key是文件唯一标识
            key = uuid.toString().replace("-","")+".jpg";
//            key = reviewId+"_"+index+".jpg";
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosProperties.getBucketName(), key, file.getInputStream(),objectMetadata);

            //使用cosClient调用第三方接口
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            log.info(putObjectRequest+"");
            //返回路径

        }catch (Exception e){
            e.printStackTrace();
        }
        //拼接返回路径
        String imagePath = "https://" + cosProperties.getBucketName() + ".cos." + cosProperties.getRegionName() + ".myqcloud.com/" + key;
        return ApiResponse.success(imagePath,imagePath);
    }

    public ApiResponse uploadVideo(MultipartFile file, Integer reviewId){
        try {

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            UUID uuid = UUID.randomUUID();
            // 指定要上传到 COS 上对象键 此key是文件唯一标识
//            key = uuid.toString().replace("-","")+".mp4";
            key = reviewId+".mp4";
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosProperties.getBucketName(), key, file.getInputStream(),objectMetadata);

            //使用cosClient调用第三方接口
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            log.info(putObjectRequest+"");
            //返回路径

        }catch (Exception e){
            e.printStackTrace();
        }
        //拼接返回路径
        String imagePath = "https://" + cosProperties.getBucketName() + ".cos." + cosProperties.getRegionName() + ".myqcloud.com/video/review/" + key;
        return ApiResponse.success(imagePath,imagePath);
    }
}
