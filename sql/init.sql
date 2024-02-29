CREATE DATABASE IF NOT EXISTS chat;
USE chat;

CREATE TABLE ChatRooms (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    avatarUrl TEXT,
    lastMessageTimestamp TIMESTAMP
);

CREATE TABLE ChatMessages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    chatroomId INT,
    userId VARCHAR(100),
    username VARCHAR(100),
    userAvatarUrl TEXT,
    messageText TEXT,
    timestamp TIMESTAMP,
    FOREIGN KEY (chatroomId) REFERENCES ChatRooms(id)
);