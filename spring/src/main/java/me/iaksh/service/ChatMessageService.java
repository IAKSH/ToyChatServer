package me.iaksh.service;

import me.iaksh.entity.ChatMessage;
import me.iaksh.mapper.ChatMessageMapper;

import java.util.List;

public class ChatMessageService {

    private ChatMessageMapper chatMessageMapper;

    public void setChatMessageMapper(ChatMessageMapper chatMessageMapper) {
        this.chatMessageMapper = chatMessageMapper;
    }

    public void addMessage(ChatMessage message) {
        chatMessageMapper.insert(message);
    }

    public List<ChatMessage> getAllMessages() {
        return chatMessageMapper.getAll();
    }

    public List<ChatMessage> getMessagesByRoomId(long id) {
        return chatMessageMapper.getByChatRoomId(id);
    }

    public void deleteMessage(long id) {
        chatMessageMapper.deleteById(id);
    }

    public void updateMessage(ChatMessage chatMessage) {
        chatMessageMapper.update(chatMessage);
    }
}
