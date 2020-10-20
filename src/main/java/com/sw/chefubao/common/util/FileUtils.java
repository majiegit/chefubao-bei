package com.sw.chefubao.common.util;

import cn.hutool.core.lang.UUID;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static String upload(String filePath, MultipartFile file) {
        String name = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."));
        String imgLocaltion = filePath + fileName;
        File imgFile = new File(imgLocaltion);
        if (!file.isEmpty()) {
            if (!imgFile.getParentFile().exists()) {
                imgFile.getParentFile().mkdir();
            }
            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(new File(imgLocaltion)));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }
}
