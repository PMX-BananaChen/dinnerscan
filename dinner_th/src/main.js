// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'
import router from'./router/index.js'      //---------自定义的路由文件
import axios from 'axios';
import './assets/css/base.css'                    //---------引入的全局公共css
import utils  from './assets/js/urlGet.js'          //------获取url参数
import  { ToastPlugin } from 'vux'
Vue.prototype.$axios = axios
Vue.prototype.$utils = utils; //注册全局方法

Vue.use(VueRouter)
Vue.use(ToastPlugin)

FastClick.attach(document.body)

Vue.config.productionTip = false

var globalVm=new Vue({
	router,            //-----router文件
    el: '#app',
    // store:stores,          //-----全局变量
    template: '<App/>',
    components: { App }
})

