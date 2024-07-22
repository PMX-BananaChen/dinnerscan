import Vue from 'vue'
import Router from 'vue-router'
import dinnerScan from '@/components/dinnerScan'
import tymScan from '@/components/scan/tymScan'
import msg from '@/components/scan/msg'
import addEmpNo from '@/components/scan/addEmpNo'

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/', redirect: '/dinnerScan/tymScan' },
		{ path: '*', redirect: '/dinnerScan/tymScan' },
		{
			path: '/dinnerScan',
			name: 'dinnerScan',
			component: dinnerScan,
			children: [

				{
					path: '/dinnerScan/tymScan',
					name: 'tymScan',
					component: tymScan
				},
				{
					path: '/dinnerScan/msg',
					name: 'msg',
					component: msg
				},
				{
					path: '/dinnerScan/addEmpNo',
					name: 'addEmpNo',
					component: addEmpNo
				}
			]
		}
  ]
})
