package com.example.chatbot;

import java.util.List;

public class ChatResponse {
    private String userMessage;
    private List<ChatHistory> chatHistory;

    public static class ChatHistory {
        private String User;
        private String Llama;

        public String getUser() {
            return User;
        }

        public String getLlama() {
            return Llama;
        }
    }
}
