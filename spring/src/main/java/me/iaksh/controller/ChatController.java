package me.iaksh.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import me.iaksh.entity.ChatMessage;
import me.iaksh.entity.ChatRoom;
import me.iaksh.service.ChatMessageService;
import me.iaksh.service.ChatRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private static ChatRoomService chatRoomService;
    private static ChatMessageService chatMessageService;

    public void setChatMessageService(ChatMessageService chatMessageService) {
        ChatController.chatMessageService = chatMessageService;
    }

    public void setChatRoomService(ChatRoomService chatRoomService) {
        ChatController.chatRoomService = chatRoomService;
    }

    @GetMapping(value = "/test", produces = "application/json;charset=UTF-8")
    public String testFunc() {
        JSONObject result = new JSONObject();
        result.put("message", "你好");
        return result.toJSONString();
    }

    @GetMapping(value = "/rooms", produces = "application/json;charset=UTF-8")
    public String getAllChatRooms() {
        return JSON.toJSONString(chatRoomService.getAllChatRooms());
    }

    @PostMapping(value = "/rooms",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addChatRoom(@RequestBody String json) {
        ChatRoom chatRoom = JSON.parseObject(json, ChatRoom.class);
        chatRoomService.addNewChatRoom(chatRoom);

        JSONObject result = new JSONObject();
        result.put("success", true);
        return result.toJSONString();
    }

    @GetMapping(value = "/rooms/{id}", produces = "application/json;charset=UTF-8")
    public String getChatRoomById(@PathVariable("id") long id) {
        return JSON.toJSONString(chatRoomService.getChatRoom(id));
    }

    @GetMapping(value = "/rooms/{id}/messages", produces = "application/json;charset=UTF-8")
    public String getMessagesByChatRoomId(@PathVariable("id") long id) {
        return JSON.toJSONString(chatMessageService.getMessagesByRoomId(id));
    }

    @PostMapping(value = "/rooms/{id}/messages",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addMessageToChatRoom(@RequestBody String json) {
        ChatMessage chatMessage = JSON.parseObject(json,ChatMessage.class);
        chatMessageService.addMessage(chatMessage);

        JSONObject result = new JSONObject();
        result.put("success", true);
        return result.toJSONString();
    }
}
