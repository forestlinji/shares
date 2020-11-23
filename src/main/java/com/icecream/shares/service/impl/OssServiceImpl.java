package com.icecream.shares.service.impl;

import com.aliyun.oss.OSS;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.service.OssService;
import com.icecream.shares.service.PostService;
import com.icecream.shares.service.UserInfoService;
import com.icecream.shares.utils.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class OssServiceImpl implements OssService {
    @Autowired
    OSS ossClient;
    @Autowired
    PostService postService;
    @Autowired
    UserInfoService userInfoService;


    @Override
    public void updateCover(MultipartFile image, Post post) throws Exception {
        String link = "shares/post/" + System.currentTimeMillis() + image.getOriginalFilename();
        File cover = FileUtil.MultipartFileToFile(image);
        ossClient.putObject("forestj", link, cover);
        cover.delete();
        post.setCoverLink(link);
        postService.updateById(post);
        return;
    }

    @Override
    @Async
    public void updateImages(File[] images, Post post) {
        List<String> urls = new ArrayList<>();
        Arrays.stream(images).forEach(image->{
            String link = "shares/post/" + System.currentTimeMillis() + image.getName();
            ossClient.putObject("forestj", link, image);
            urls.add(link);
            image.delete();
        });
        String picLink = String.join("OVENKFIWHF", urls);
        System.out.println(picLink);
        post.setPicLink(picLink);
        post.setCheckState(1);
        postService.updateById(post);
    }

    @Override
    public void updateHeadImage(File image) {
        String link = "shares/head/" + System.currentTimeMillis() + image.getName();
        ossClient.putObject("forestj", link, image);
        int userId = Integer.parseInt(LoginInterceptor.getUserId());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setHeadLink(link);
        userInfoService.updateById(userInfo);
        image.delete();
    }
}
