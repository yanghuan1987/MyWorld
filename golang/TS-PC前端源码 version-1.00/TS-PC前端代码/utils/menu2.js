module.exports = [
  {
    key: 'dashboard',
    name: '首页',
    icon: 'laptop'
  },
  {
    key:'publishdata',
    name:'数据发布',
    icon:'laptop',
    action:'publishdata_pcpublish',
  },
  {
    key: 'review',
    
    name: '数据审核',
    icon: 'laptop',
    clickable: false,
    child: [
      {
        key: 'list',
        action:'review_list',
        name: '日报数据审核'
      },
      {
        key: 'revieweffects',
        action:'review_effects',
        name: '日报审核结果'
      }
    ]
  },
  {
    key: 'stats',
    name: '查询统计',
    icon: 'camera-o',
    clickable: false,
    child: [
      {
        key: 'basic',
        action:'basic_list',
        name: '常规统计'
      },
      {
        key: 'archive',
        action:'archive_list',
        name: '整编数据'
      }
    ]
  },
  {
    key: 'station',
    action:'station_list',
    name: '站点管理',
    icon: 'laptop'
  }
];
