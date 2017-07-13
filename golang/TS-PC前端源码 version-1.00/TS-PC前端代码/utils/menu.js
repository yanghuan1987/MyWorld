module.exports = [
	{
		key: 'dashboard',
		name: '首页',
		icon: 'laptop'
	},
	{
	key: 'packages',
	name: '入库取件',
	icon: 'laptop',
	child: [{
		key: 'packageInto',
		name: '订单包裹入柜',
		action: 'review_release',
	}, {
		key: 'goodsInto',
		name: '调运商品入柜',
		action: 'review_release',
	}, 
//	{
//		key: 'goodsDis',
//		name: '调运商品位移',
//		action: 'review_release',
//	}, 
	{
		key: 'stockdis',
		name: '库存位移',
		action: 'review_release',
	}, {
		key: 'pickup',
		name: '客户取件',
		action: 'review_release',
	}, {
		key: 'overdue',
		name: '过期处理',
		action: 'review_relase',
	}]
}, {
	key: 'update',
	name: '数据更新',
	icon: 'laptop',
	action: 'publishdata_pcpublish',
	child: [{
		key: 'updatePackage',
		name: '订单包裹更新',
		action: 'review_relase',
	}, {
		key: 'updateGoodsInfo',
		name: '商品信息更新',
		action: 'review_relase',
	}, ]
}, {
	key: 'review',
	name: '商品销售',
	icon: 'laptop',
	clickable: false,
	child: [{
		key: 'scanSale',
		action: 'review_release',
		name: '扫码销售'
	}, {
		key: 'wechatpay',
		action: 'review_effects',
		name: '微信支付结果'
	}, {
		key: 'cardpay',
		action: 'review_release',
		name: '银犁卡支付记录'
	}]
}, {
	key: 'stats',
	name: '自提点管理',
	icon: 'camera-o',
	clickable: false,
	child: [{
		key: 'station',
		action: 'basic_list',
		name: '站点管理'
	}, {
		key: 'icebox',
		action: 'archive_list',
		name: '冷物柜管理'
	}, {
		key: 'shelf',
		action: 'review_relase',
		name: '货架管理'
	}]
}, {
	key: 'card',
	name: '银犁卡管理',
	icon: 'camera-o',
	clickable: false,
	child: [
	 {
		key: 'open',
		action: 'manager_list',
		name: '银犁卡开通'
	},
	{
		key: 'recharge',
		action: 'role_list',
		name: '办卡充值'
	}, {
		key: 'resetpwd',
		action: 'module_list',
		name: '修改密码'
	}, {
		key: 'frozen',
		action: 'manager_list',
		name: '冻结激活'
	}]
}];