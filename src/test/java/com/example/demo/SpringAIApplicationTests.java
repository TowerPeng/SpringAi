package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
class SpringAIApplicationTests {
	@Test
	public void testChat1(@Autowired
						 DeepSeekChatModel chatModel) {
		String call = chatModel.call("你是谁");
		System.out.println(call);
	}
	@Test
	public void testChat(@Autowired
						 DeepSeekChatModel chatModel) {
		Flux<String> stream = chatModel.stream("你是谁");

		// 阻塞输出
		stream.toIterable().forEach(System.out::print);
	}

	@Test
	public void testChatOptions(@Autowired
								DeepSeekChatModel chatModel) {
		DeepSeekChatOptions options = DeepSeekChatOptions.builder().temperature(1.9d).build();
		ChatResponse res = chatModel.call(new Prompt("请写一句诗描述清晨。", options));
		System.out.println(res.getResult().getOutput().getText());
	}
}
