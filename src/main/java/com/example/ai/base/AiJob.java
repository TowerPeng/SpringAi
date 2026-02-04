package com.example.ai.base;

import java.util.Map;

/**
 * @Author: 彭涛
 * @Date: 2026/2/4 17:08
 */
public class AiJob {
    public record Job(JobType jobType, Map<String,String> keyInfos) {
    }

    public enum JobType{
        CANCEL,
        QUERY,
        OTHER,
    }
}
