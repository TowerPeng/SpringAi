package com.example.ai.service;

import org.springframework.stereotype.Service;

/**
 * @Author: 彭涛
 * @Date: 2026/2/4 17:24
 */
@Service
public class TicketService {

    public void cancel(String ticketNumber,String name){
        System.out.println("退票成功");
    }

}
