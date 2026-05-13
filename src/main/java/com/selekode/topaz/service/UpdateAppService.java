package com.selekode.topaz.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selekode.topaz.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateAppService {

    @Value("${app.version}")
    private String currentVersion;

    private final String UPDATE_URL = Constants.LATEST_VERSION_TOPAZ;

    public Map<String, Object> checkUpdate() {
        Map<String, Object> result = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            JsonNode root = restTemplate.getForObject(UPDATE_URL, JsonNode.class);
            String latestVersion = root.path("topaz").asText();

            boolean updateAvailable = !currentVersion.equals(latestVersion);

            result.put("updateAvailable", updateAvailable);
            result.put("currentVersion", currentVersion);
            result.put("latestVersion", latestVersion);
        } catch (Exception e) {
            result.put("updateAvailable", false);
            result.put("error", "No se pudo verificar la actualización");
        }
        return result;
    }
}