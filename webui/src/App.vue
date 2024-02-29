<template>
    <div id="app">
        <!-- 上部分 -->
        <div class="chat-list" :class="{ 'mobile-view': isMobile, 'collapsed': !isChatListOpen }" style="position: fixed; top: 0; width: 100%; text-align: left; backdrop-filter: blur(10px); background-color: rgba(211, 211, 211, 0.5);">
            <div class="current-chat" @click="isChatListOpen = !isChatListOpen">
                <img :src="currentChat.avatarUrl" class="avatar">
                {{ currentChat.name }}
                <span>{{ $filters.formatTimestamp(currentChat.lastMessageTimestamp) }}</span>
            </div>
            <div v-show="isChatListOpen" v-for="chat in chats" :key="chat.id" class="chat-item"
                :class="{ 'selected-chat': chat.id === currentChat.id }" @click="selectChat(chat.id)">
                <img :src="chat.avatarUrl" class="avatar">
                {{ chat.name }}
                <span v-if="chat.newMessage" class="new-message">New</span>
            </div>
        </div>
        <!-- 中部分 -->
        <div class="chat-container" style="margin-top: 50px; margin-bottom: 50px;">
            <div class="header">Chat Room</div>
            <div class="messages">
                <div v-for="message in messages" :key="message.id" class="message">
                    <img :src="message.userAvatarUrl" class="avatar">
                    <div class="content">
                        <div class="name">{{ message.username }} <span class="uid">{{ message.userId }}</span> <span class="timestamp">{{ $filters.formatTimestamp(message.timestamp) }}</span></div>
                        <div class="text">{{ message.messageText }}</div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 底部分 -->
        <div class="input-area" style="position: relative; bottom: 0; width: 100%; display: flex; align-items: stretch;">
            <!-- 用户信息部分 -->
            <div class="user-profile" @click="editProfile">
                <img :src="userProfile.avatar" class="avatar">
                <div class="name">{{ userProfile.name }} <span class="uid">{{ userProfile.uid }}</span></div>
            </div>
            <!-- 输入消息部分 -->
            <textarea v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type a message" style="flex-grow: 1; margin-top: 10px; padding: 10px; border-radius: 5px;"></textarea>
        </div>
    </div>
</template>

<style scoped>
.chat-container {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f5f5f5;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
    overflow: hidden;
    display: flex;
    flex-direction: column;
    max-width: 600px;
    margin: auto;
}

.name {
    color: #00bfff; /* 亮蓝色 */
    text-shadow: -1px 0 #00008b, 0 1px #00008b, 1px 0 #00008b, 0 -1px #00008b; /* 深蓝色描边 */
}

.menu {
    background-color: #ffffff;
    border: 1px solid #e6e6e6;
    border-radius: 5px;
    padding: 10px;
    position: absolute;
    right: 20px;
    top: 20px;
    z-index: 1000;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.uid {
    font-size: 0.8em;
    color: #888;
    text-shadow: none;
}

.timestamp {
    font-size: 0.8em;
    color: #888;
    text-shadow: none;
}

.chat-list {
    position: fixed;
    left: 0;
    top: 0;
    width: 200px;
    padding: 20px;
    border-right: 1px solid #ccc;
}

.chat-item {
    margin-bottom: 10px;
    cursor: pointer;
}

.avatar {
    width: 50px;
    height: 50px;
    object-fit: cover;
}

.new-message {
    color: red;
    font-weight: bold;
}

.selected-chat {
    background-color: #ddd;
}

/* 折叠页样式 */
.chat-list.collapsed .chat-item {
    display: none;
}

.current-chat {
    cursor: pointer;
}

</style>

<script>
import { ref, onMounted, onUnmounted } from 'vue'
import axios from 'axios'

const apiClient = axios.create({
    baseURL: '/api'
})

export default {
    setup() {
        const newMessage = ref('')
        const messages = ref([])
        const chats = ref([])
        const userProfile = ref({
            uid: Math.random().toString(36).substring(2, 15),
            name: 'User',
            avatar: 'https://via.placeholder.com/50'
        })
        let intervalId
        let currentChat = ref(null)

        const response = apiClient.get('/chat/rooms')
        chats.value = response.data
        currentChat.value = {
            id: 0,
            name: "temp",
            avatarUrl: "",
            lastMessageTimestamp: Date.now()
        }

        function sendMessage() {
            if (newMessage.value.trim() !== '') {
                const message = {
                    chatroomId: currentChat.value.id,
                    userId: userProfile.value.uid,
                    username: userProfile.value.name,
                    userAvatarUrl: userProfile.value.avatar,
                    messageText: newMessage.value.trim(),
                    timestamp: Date.now()
                }
                apiClient.post(`/chat/rooms/${currentChat.value.id}/messages`, JSON.stringify(message), {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                })
                newMessage.value = ''
            }
        }

        function editProfile() {
            const name = prompt('Enter your name', userProfile.value.name)
            const avatar = prompt('Enter your avatar URL', userProfile.value.avatar)
            if (name) userProfile.value.name = name
            if (avatar) userProfile.value.avatar = avatar
        }

        function selectChat(id) {
            currentChat.value = chats.value.find(chat => chat.id === id)
            messages.value = []
            updateMessages()
        }


        function updateMessages() {
            if (currentChat.value) {
                apiClient.get(`/chat/rooms/${currentChat.value.id}/messages`)
                    .then(response => {
                        messages.value = response.data
                        // 检查是否有新消息
                        chats.value.forEach(chat => {
                            if (chat.id === currentChat.value.id && chat.lastUpdate > chat.lastChecked) {
                                chat.newMessage = true
                            }
                        })
                    })
            }
        }

        onMounted(() => {
            apiClient.get('/chat/rooms')
                .then(response => {
                    chats.value = response.data
                })
            intervalId = setInterval(updateMessages, 1000) // Update messages every 1 seconds
        })

        onUnmounted(() => {
            clearInterval(intervalId) // Clear the interval when the component is unmounted
        })

        return {
            newMessage,
            messages,
            chats,
            currentChat,
            userProfile,
            sendMessage,
            editProfile,
            updateMessages,
            selectChat
        }
    }
}
</script>