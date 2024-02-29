package me.iaksh.entity;

import java.sql.Timestamp;

public class ChatMessage extends AbstractEntity {
    private long chatroomId;
    private String userId;
    private String username;
    private String userAvatarUrl;
    private String messageText;
    private Timestamp timestamp;

    public void setChatroomId(long chatroomId) {
        this.chatroomId = chatroomId;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getMessageText() {
        return messageText;
    }

    public long getChatroomId() {
        return chatroomId;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
