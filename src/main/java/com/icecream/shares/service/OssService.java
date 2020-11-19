package com.icecream.shares.service;

import com.aliyun.oss.OSS;
import com.icecream.shares.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface OssService {


    /**
     * 上传封面图
     */
    void updateCover(MultipartFile image, Post post) throws Exception;

    /**
     * 上传其他图片
     * @param images
     * @param post
     */
    @Async
    void updateImages(File[] images, Post post);


    void updateHeadImage(File image);
}
