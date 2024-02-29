package me.iaksh.mapper;

import me.iaksh.entity.ChatRoom;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ChatRoomMapper {
    @Select("SELECT * FROM ChatRooms WHERE id = #{id}")
    ChatRoom getById(@Param("id") long id);

    @Select("SELECT * FROM ChatRooms LIMIT #{offset}, #{maxResults}")
    List<ChatRoom> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ChatRooms (" +
            "name, avatarUrl, lastMessageTimestamp" +
            ") VALUES " +
            "(#{chatRoom.name}, " +
            "#{chatRoom.avatarUrl}, " +
            "#{chatRoom.lastMessageTimestamp})")
    void insert(@Param("chatRoom") ChatRoom chatRoom);

    @Update("UPDATE ChatRooms SET " +
            "name = #{chatRoom.name}, " +
            "avatarUrl = #{chatRoom.avatarUrl}, " +
            "lastMessageTimestamp = #{chatRoom.lastMessageTimestamp} " +
            "WHERE id = #{chatRoom.id}")
    void update(@Param("chatRoom") ChatRoom chatRoom);

    @Delete("DELETE FROM ChatRooms WHERE id = #{id}")
    void deleteById(@Param("id") long id);
}
