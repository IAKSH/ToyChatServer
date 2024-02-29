package me.iaksh.service;

import me.iaksh.entity.ChatRoom;
import me.iaksh.mapper.ChatRoomMapper;

import java.util.List;

public class ChatRoomService {

    private ChatRoomMapper chatRoomMapper;

    public void setChatRoomMapper(ChatRoomMapper chatRoomMapper) {
        this.chatRoomMapper = chatRoomMapper;
    }

    public void addNewChatRoom(ChatRoom chatRoom) {
        chatRoomMapper.insert(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomMapper.getAll(0,10240);
    }

    public ChatRoom getChatRoom(long id) {
        return chatRoomMapper.getById(id);
    }

    public void updateChatRoom(ChatRoom chatRoom) {
        chatRoomMapper.update(chatRoom);
    }
}
