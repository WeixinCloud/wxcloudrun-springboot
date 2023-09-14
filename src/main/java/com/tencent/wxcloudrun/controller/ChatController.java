package com.tencent.wxcloudrun.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.dto.ChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@Slf4j
public class ChatController {

    @Value("${OpenAIAuth:abc}")
    private String auth;

    @PostMapping(value = "/chat")
    public String chat(@RequestBody ChatRequest request) {

        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", auth);

        // 创建请求体
        String jsonBody = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + request.getUser() + "\"}], \"temperature\": 0.7 }";

        // 创建HTTP请求实体
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        // 发送POST请求
        ResponseEntity<String> response = restTemplate.exchange("http://43.134.34.78/chat", HttpMethod.POST, entity, String.class);

        // 打印响应结果
        log.info("Response status code: {}, body: {}", response.getStatusCode(), response.getBody());

        // 解析JSON响应
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode contentNode = root.at("/choices/0/message/content");
            String content = contentNode.asText();
            return "Server Reply: " + content;
        } catch (Exception e) {
            log.error("error", e);
        }
        return "Server Reply: Hello GPT!";
    }
}
