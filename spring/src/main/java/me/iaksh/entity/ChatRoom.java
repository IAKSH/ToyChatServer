package me.iaksh.entity;

import java.sql.Timestamp;

public class ChatRoom extends AbstractEntity {
    private String name;
    private String avatarUrl;
    private Timestamp lastMessageTimestamp;

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setLastMessageTimestamp(Timestamp lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Timestamp getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }
}