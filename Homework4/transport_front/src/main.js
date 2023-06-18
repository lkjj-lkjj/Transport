import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router";
import '@/assets/css/global.css'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'


createApp(App).use(router).use(ElementPlus).mount('#app')