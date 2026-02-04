package com.example.ai.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Author: 彭涛
 * @Date: 2026/2/4 17:24
 */
@Service
public class ToolService {

    @Autowired
    private TicketService ticketService;

    @Tool(description = "退票")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancel(@ToolParam(description = "预定号") String ticketNumber, @ToolParam(description = "用户名") String name) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        ticketService.cancel(ticketNumber,name);
        return "退票成功";
    }


}
