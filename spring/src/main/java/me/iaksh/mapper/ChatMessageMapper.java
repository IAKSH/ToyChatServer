package me.iaksh.mapper;

import me.iaksh.entity.ChatMessage;
import me.iaksh.entity.ChatRoom;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ChatMessageMapper {
    //@Select("SELECT * FROM ChatMessages LIMIT #{offset}, #{maxResults}")
    //List<ChatMessage> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

    @Select("SELECT * FROM ChatMessages")
    List<ChatMessage> getAll();

    @Select("SELECT * FROM ChatMessages WHERE chatroomId = #{chatroomId}")
    List<ChatMessage> getByChatRoomId(@Param("chatroomId") long chatroomId);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ChatMessages (chatroomId, userId, username, userAvatarUrl, messageText, timestamp)" +
            "VALUES (#{chatMessage.chatroomId}, #{chatMessage.userId}, #{chatMessage.username}, #{chatMessage.userAvatarUrl}, #{chatMessage.messageText}, #{chatMessage.timestamp})")
    void insert(@Param("chatMessage") ChatMessage chatMessage);

    @Update("UPDATE ChatMessages SET " +
            "chatroomId = #{chatMessage.chatroomId}, " +
            "userId = #{chatMessage.userId}, " +
            "username = #{chatMessage.username}, " +
            "userAvatarUrl = #{chatMessage.userAvatarUrl}, " +
            "messageText = #{chatMessage.messageText}, " +
            "timestamp = #{chatMessage.timestamp} " +
            "WHERE id = #{chatMessage.id}")
    void update(@Param("chatMessage") ChatMessage chatMessage);

    @Delete("DELETE FROM ChatMessages WHERE id = #{id}")
    void deleteById(@Param("id") long id);
}
