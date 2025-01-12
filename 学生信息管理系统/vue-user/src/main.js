import Vue from 'vue'
import App from './App.vue'
import router from './router'
// 引入ElementUI组件
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import  Axios  from 'axios';
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$axios = Axios;
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
