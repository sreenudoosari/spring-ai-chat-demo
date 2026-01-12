package com.sree.ai.demo;

import jakarta.validation.Valid;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class AiChatController {

    private final ChatClient chatClient;

    AiChatController(ChatClient.Builder builder) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @PostMapping
    ChatResponse chatPrompt(@RequestBody @Valid ChatRequest request) {
        String response = chatClient.prompt(request.prompt()).call().content();
        return new ChatResponse(response);
    }

}
