import React from 'react'
import {Router} from 'dva/router'
import App from './routes/app'

const cached = {};

function registerModel(app, model) {
	if(!cached[model.namespace]) {
		app.model(model);
		cached[model.namespace] = 1;
	}
}

function RouterConfig({	history,app}) {
	const routes = [
		/*
		{
		  path: '/login',
		  name: 'login',
		  getComponent(nextState, cb) {
		    require.ensure([], (require) => {
		      registerModel(app, require('./models/common'));
		      cb(null, require('./routes/login'));
		    });
		  },
		},*/
		{
			path: '/',
			component: App,
			getIndexRoute(nextState, cb) {
				require.ensure([], require => {
					registerModel(app,require('./models/dashboard'))
					cb(null, {
						component: require('./routes/dashboard')
					})
				})
		},
			childRoutes: [
			{
				path: 'dashboard',
				name: 'dashboard',
				getComponent (nextState, cb) {
						require.ensure([], require => {
							registerModel(app,require('./models/dashboard'))
							cb(null, require('./routes/dashboard'))
						})
					}
			},
			{
				path: 'packages/packageInto',//订单包裹入柜
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app,require('./models/packageInto'))
						cb(null, require('./routes/packageInto'))
					})
				}
			},
			{
				path: 'packages/goodsInto',//调运商品入柜
				getComponent(nextState, cb) {
					require.ensure([], require => {
//						registerModel(app, require('./models/updateGoodsInfo'));
//						cb(null, require('./routes/updateGoodsInfo'))
						registerModel(app, require('./models/goodsInto'));
						cb(null, require('./routes/goodsInto'))
					})
				}
			},
			
			{
				path: 'packages/stockdis',//库存位移
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app,require('./models/stockdis'))
						cb(null, require('./routes/stockdis'))
					})
				}
			},
			{
				path: 'packages/pickup',//客户取件
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app,require('./models/pickup'))
						cb(null, require('./routes/pickup'))
					})
				}
			},
			{
				path: 'packages/overdue',//过期处理
				name: 'pcpublish_table',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/overdue'));
						cb(null, require('./routes/overdue'))
					})
				}
			},
			{
				path: 'update/updatePackage',//订单包裹更新
				name: 'pcpublish_table',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/updatePackage'));
						cb(null, require('./routes/updatePackage'))
					})
				}
			}, {
				path: 'update/updateGoodsInfo',//商品信息更新
				name: 'pcpublish_table',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/updateGoodsInfo'));
						cb(null, require('./routes/updateGoodsInfo'))
					})
				}
			},{
				path: 'review/scanSale',//扫码销售
				name: 'station',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/scanSale'));
						cb(null, require('./routes/scanSale'))
					})
				}
			}, {
				path: 'review/wechatpay',//微信支付结果
				name: 'review_effects',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/wechatpay'));
						cb(null, require('./routes/wechatpay'))
					})
				}
			}, {
				path: 'review/cardpay',//银梨支付记录
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/cardpay'));
						cb(null, require('./routes/cardpay'))
					})
				}
			},
			{
				path: 'stats/station',//站点管理
				name: 'stats-basic',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/station'));
						cb(null, require('./routes/station'))
					})
				}
			}, {
				path: 'stats/icebox',//冷物柜管理
				name: 'stats-archive',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/icebox'));
						cb(null, require('./routes/icebox'))
					})
				}
			}, {
				path: 'stats/shelf',//货架管理
				name: 'stats-archive',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/shelf'));
						cb(null, require('./routes/shelf'))
					})
				}
			},
			{
				path: 'card/open',//开通银犁卡
				name: 'system-role',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/open'));
						cb(null, require('./routes/open'))
					})
				}
			},{
				path: 'card/recharge',//办卡充值
				name: 'system-role',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/recharge'));
						cb(null, require('./routes/recharge'))
					})
				}
			}, {
				path: 'card/resetpwd',//修改密码
				name: 'system-module',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/resetpwd'));
						cb(null, require('./routes/resetpwd'))
					})
				}
			}, {
				path: 'card/frozen',//冻结激活
				name: 'system-manager',
				getComponent(nextState, cb) {
					require.ensure([], require => {
						registerModel(app, require('./models/frozens'));
						cb(null, require('./routes/frozens'))
					})
				}
			}]

		}
	]

	return <Router history = {history} routes = {routes}/>
}

export default RouterConfig;