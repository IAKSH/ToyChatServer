import { createApp } from 'vue'
import App from './App.vue'

const app = createApp(App)

app.config.globalProperties.$filters = {
    formatTimestamp(timestamp) {
        const date = new Date(timestamp)
        return date.toLocaleString()
    }
}

app.mount('#app')