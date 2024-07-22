import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './actions'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex);

const vuexLocal = new VuexPersistence({
    storage: window.localStorage
})

export default new Vuex.Store({
	strict: false,
	modules:{
		mutations
	},
	actions,
	plugins: [vuexLocal.plugin]
});

