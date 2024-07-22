import Vue from 'vue'
import Router from 'vue-router'
import dinnerScan from '@/components/dinnerScan'
import pmxScan from '@/components/scan/pmxScan'
import msg from '@/components/scan/msg'
import addEmpNo from '@/components/scan/addEmpNo'

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/', redirect: '/dinnerScan/pmxScan' },
		{ path: '*', redirect: '/dinnerScan/pmxScan' },
		{
			path: '/dinnerScan',
			name: 'dinnerScan',
			component: dinnerScan,
			children: [

				{
					path: '/dinnerScan/pmxScan',
					name: 'pmxScan',
					component: pmxScan
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
