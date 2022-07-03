import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import * as ElIconModules from '@element-plus/icons'
import config from './config'
import '@/assets/font/fontello.scss'
import '@/assets/css/index.scss'
import '@/assets/css/fengtai.css'

const app = createApp(App)
app.use(store)
app.use(router)
app.use(ElementPlus)
app.config.globalProperties.$config = config;
for (const iconName in ElIconModules) {
  if (Reflect.has(ElIconModules, iconName)) {
    const item = ElIconModules[iconName]
    app.component(iconName, item)
  }
}

app.mount('#app')