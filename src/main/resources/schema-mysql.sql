CREATE TABLE IF NOT EXISTS SPRING_AI_CHAT_MEMORY (
                                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `conversation_id` VARCHAR(36) NOT NULL,
    `content` TEXT NOT NULL,
    `type` VARCHAR(10) NOT NULL,
    `timestamp` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `SPRING_AI_CHAT_MEMORY_CONVERSATION_ID_TIMESTAMP_IDX` (`conversation_id`, `timestamp`)
    )ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;