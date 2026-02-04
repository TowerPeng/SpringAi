package com.example.demo;

import com.alibaba.cloud.ai.dashscope.audio.DashScopeAudioTranscriptionModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeAudioTranscriptionOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.MimeTypeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;

/**
 * @Author: 彭涛
 * @Date: 2026/2/4 12:53
 */
@SpringBootTest
public class SpringAiQwenTest {
    @Test
    public void testQwen(@Autowired DashScopeChatModel dashScopeChatModel) {

        String content = dashScopeChatModel.call("你好你是谁");
        System.out.println(content);
    }
    @Test
    public void text2Img(
            @Autowired DashScopeImageModel imageModel) {
        DashScopeImageOptions imageOptions = DashScopeImageOptions.builder()
                .withModel("wanx2.1-t2i-turbo").build();

        ImageResponse imageResponse = imageModel.call(
                new ImagePrompt("程序员徐庶", imageOptions));
        String imageUrl = imageResponse.getResult().getOutput().getUrl();

        // 图片url
        System.out.println(imageUrl);

        // 图片base64
        // imageResponse.getResult().getOutput().getB64Json();

        /*
        按文件流相应
        InputStream in = url.openStream();

        response.setHeader("Content-Type", MediaType.IMAGE_PNG_VALUE);
        response.getOutputStream().write(in.readAllBytes());
        response.getOutputStream().flush();*/
    }

//    @Test
//    public void testText2Audio(@Autowired DashScopeSpeechSynthesisModel speechSynthesisModel) throws IOException {
//        DashScopeSpeechSynthesisOptions options = DashScopeSpeechSynthesisOptions.builder()
//                //.voice()   // 人声
//                //.speed()    // 语速
//                //.model()    // 模型
//                //.responseFormat(DashScopeSpeechSynthesisApi.ResponseFormat.MP3)
//                .build();
//
//        SpeechSynthesisResponse response = speechSynthesisModel.call(
//                new SpeechSynthesisPrompt("大家好， 我是人帅活好的徐庶。",options)
//        );
//
//        File file = new File( System.getProperty("user.dir") + "/output.mp3");
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
//            fos.write(byteBuffer.array());
//        }
//        catch (IOException e) {
//            throw new IOException(e.getMessage());
//        }
//    }

    private static final String AUDIO_RESOURCES_URL = "https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/paraformer/hello_world_female2.wav";

    @Test
    public void testAudio2Text(
            @Autowired
            DashScopeAudioTranscriptionModel transcriptionModel
    ) throws MalformedURLException {
        DashScopeAudioTranscriptionOptions transcriptionOptions = DashScopeAudioTranscriptionOptions.builder()
                //.withModel()   模型
                .build();
        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(
                new UrlResource(AUDIO_RESOURCES_URL),
                transcriptionOptions
        );
        AudioTranscriptionResponse response = transcriptionModel.call(
                prompt
        );

        System.out.println(response.getResult().getOutput());

    }

    @Test
    public void testMultimodal(@Autowired DashScopeChatModel dashScopeChatModel
    ) throws MalformedURLException {
        // flac、mp3、mp4、mpeg、mpga、m4a、ogg、wav 或 webm。
        var audioFile = new ClassPathResource("/files/xushu.png");

        Media media = new Media(MimeTypeUtils.IMAGE_JPEG, audioFile);
        DashScopeChatOptions options = DashScopeChatOptions.builder()
                .withMultiModel(true)
                .withModel("qwen-vl-max-latest").build();

        Prompt prompt= Prompt.builder().chatOptions(options)
                .messages(UserMessage.builder().media(media)
                        .text("识别图片").build())
                .build();
        ChatResponse response = dashScopeChatModel.call(prompt);

        System.out.println(response.getResult().getOutput().getText());
    }


    @Autowired
    private ChatClient.Builder builder;
    @Test
    public void testChatClient() {
        ChatClient chatClient =builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
        String content = chatClient.prompt()
                .user("你好")
                .call()
                .content();
        System.out.println(content);
    }

    @Test
    public void testChatClient2() {


    }

}
