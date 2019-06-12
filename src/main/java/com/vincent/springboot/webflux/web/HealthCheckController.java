package com.vincent.springboot.webflux.web;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthCheckController {

    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.profiles.active}")
    private String profilesActive;


    /**
     * @Description ping一下应用服务,检查是否正常运行
     */
    @GetMapping(value = "/ping")
    @ResponseBody
    public Map<String, Object> healthCheck() {
        return ImmutableMap.of("message","pong!",
                "profilesActive",profilesActive,
                "applicationName", applicationName,
                "serverPort",serverPort);
    }
}
