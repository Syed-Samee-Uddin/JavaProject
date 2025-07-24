package com.main.ecommerce.controller;

import com.main.ecommerce.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/ask")
    public String askQuestion(@RequestBody String userQuestion) {
        return chatbotService.getAnswer(userQuestion);
    }
}
