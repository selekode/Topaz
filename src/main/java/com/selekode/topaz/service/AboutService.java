package com.selekode.topaz.service;

import com.selekode.topaz.model.AboutInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AboutService {
    @Value("${app.data.dir}")
    private String datasourceUrl;

    public AboutInfo getDatasourceUrl() {
        AboutInfo aboutInfo = new AboutInfo();
        aboutInfo.setDatabasePath(datasourceUrl);

        return aboutInfo;
    }
}
