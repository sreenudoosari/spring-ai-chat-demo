package com.sree.ai.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Integration test for AIChatController.
 * Verifies end-to-end request/response flow with AI provider.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
//@ActiveProfiles("gemini")
//@ActiveProfiles("docker-smollm2")
//@ActiveProfiles("docker-gemma3")
//@ActiveProfiles("docker")
class AiChatControllerTest {

    @Autowired
    MockMvcTester mockMvcTester;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void givenChatPrompt_whenRequestSent_thenModelResponds() throws Exception {
        ChatRequest chatPrompt =
                new ChatRequest("Tell a very short joke in french");

        MvcTestResult chatResult = mockMvcTester.post().uri("/api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(chatPrompt))
                .exchange();
        log.info("\n Prompt sent : {}",chatPrompt.prompt());

        ChatResponse response = objectMapper.readValue(
                chatResult.getResponse().getContentAsString(),
                ChatResponse.class
        );
        log.info("\n ChatResponse received:\n {}" , response.content());
        assertThat(chatResult).hasStatus(HttpStatus.OK);
        assertThat(response.content()).isNotBlank();
    }
}
