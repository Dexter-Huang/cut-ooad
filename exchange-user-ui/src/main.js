import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import "./axios"
import "./permission"

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

import Prism from 'prismjs';

import Bus from '@/views/map/bus/bus.js'/// mitt 总线程引入
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import Plugin from 'v-fit-columns';
import XLSX from 'xlsx'

import vue3videoPlay from 'vue3-video-play' // 引入组件
import 'vue3-video-play/dist/style.css' // 引入css

VueMarkdownEditor.use(vuepressTheme, {
	Prism,
});


const app=createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
	app.component(key, component)
}

app.use(vue3videoPlay)
app.use(XLSX)
app.use(Plugin)
app.use(SockJS)
app.use(Stomp)
app.use(Bus)

app.use(VueMarkdownEditor);

app.use(store)
app.use(router)
app.use(ElementPlus)
app.mount('#app')

