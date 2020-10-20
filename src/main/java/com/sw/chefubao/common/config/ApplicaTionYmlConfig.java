package com.sw.chefubao.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicaTionYmlConfig {
    @Value("${image-path}")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }
}
