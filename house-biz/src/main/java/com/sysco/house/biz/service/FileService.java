package com.sysco.house.biz.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    /**
     * 获取本地图片路径
     * @param files
     * @return
     */
    List<String> getImgPath(List<MultipartFile> files);
}
