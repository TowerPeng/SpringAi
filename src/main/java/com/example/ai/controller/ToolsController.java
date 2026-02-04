package com.example.ai.controller;

import com.example.ai.service.ToolService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 彭涛
 * @Date: 2026/2/4 17:22
 */
@RestController
public class ToolsController {

    ChatClient chatClient;
    public ToolsController (ChatClient.Builder ChatClientBuilder, ToolService toolService){
        this.chatClient = ChatClientBuilder
                .defaultTools(toolService)
                .build();
    }

    @RequestMapping("tools")
    public String tools(@RequestParam(value = "message",defaultValue = "讲")String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

}
