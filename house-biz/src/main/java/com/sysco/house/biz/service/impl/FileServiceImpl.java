package com.sysco.house.biz.service.impl;

import com.google.common.io.Files;
import com.sysco.house.biz.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.path}")
    private String filePath;

    @Override
    public List<String> getImgPath(List<MultipartFile> files) {
        List<String> paths = new ArrayList<>();
        files.forEach(file ->{
            File localFile = null;
            if(!file.isEmpty()){
                try {
                    localFile = saveToLocal(file);
                    String s = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
                    paths.add(s);
                } catch (Exception e) {
                   throw new IllegalArgumentException(e);
                }
            }
        });
        return paths;
    }

    /**
     * 保存文件到本地
     * @param file
     * @return
     * @throws IOException
     */
    public File saveToLocal(MultipartFile file) throws IOException {
        File newFile = new File(filePath + "\\\\" + System.currentTimeMillis() + "\\\\" + file.getOriginalFilename());
        if(!newFile.exists()){
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        }
        Files.write(file.getBytes(), newFile);
        return newFile;
    }
}
