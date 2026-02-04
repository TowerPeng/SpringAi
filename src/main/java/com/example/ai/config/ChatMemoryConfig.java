//package com.example.demo.config;
//
//import org.springframework.ai.chat.memory.ChatMemory;
//import org.springframework.ai.chat.memory.MessageWindowChatMemory;
//import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author: 彭涛
// * @Date: 2026/2/4 15:32
// */
//@Configuration
//public class ChatMemoryConfig {
//
//
//    @Bean
//    ChatMemory chatMemory(JdbcChatMemoryRepository chatMemoryRepository) {
//        return MessageWindowChatMemory
//                .builder()
//                .maxMessages(5)
//                .chatMemoryRepository(chatMemoryRepository).build();
//    }
//
//}