package com.example.chatbot;

import java.util.List;

public class ChatResponse {
    private String userMessage;
    private List<ChatHistory> chatHistory;

    public String getUserMessage() {
        return userMessage;
    }

    public List<ChatHistory> getChatHistory() {
        return chatHistory;
    }

    public static class ChatHistory {
        private String User;
        private String Llama;

        // Constructor
        public ChatHistory(String user, String llama) {
            this.User = user;
            this.Llama = llama;
        }

        public String getUser() {
            return User;
        }

        public String getLlama() {
            return Llama;
        }
    }
}
