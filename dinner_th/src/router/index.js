import Vue from 'vue'
import Router from 'vue-router'
import dinnerTHHome from '@/components/dinnerTHHome'
import dinnerTH from '@/components/dinner/dinnerTH'
import msg from '@/components/dinner/msg'
Vue.use(Router)

export default new Router({
  routes: [
    { path: '/', redirect: '/dinnerTHHome/dinnerTH' },
    { path: '*', redirect: '/dinnerTHHome/dinnerTH' },
    {
			path: '/dinnerTHHome',
			name: 'dinnerTHHome',
			component: dinnerTHHome,
			children: [
				{
					path: '/dinnerTHHome/dinnerTH',
					name: 'dinnerTH',
					component: dinnerTH
				},
				{
					path: '/dinnerTHHome/msg',
					name: 'msg',
					component: msg
				}
			]
		}
  ]
})
