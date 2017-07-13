import React, { PropTypes } from 'react'
import { Breadcrumb, Icon } from 'antd'
import styles from './main.less'
import { menu } from '../../utils'
import { menu2 } from '../../utils'

let pathSet = []
const getPathSet = function (menuArray, parentPath) {
  parentPath = parentPath || '/'
  menuArray.map(item => {
    pathSet[(parentPath + item.key).replace(/\//g, '-').hyphenToHump()] = {
      path: parentPath + item.key,
      name: item.name,
      icon: item.icon || '',
      clickable: item.clickable === undefined
    }
    if (item.child) {
      getPathSet(item.child, parentPath + item.key + '/')
    }
  })
}
const permissions =localStorage.getItem("permissions")==undefined?'':localStorage.getItem("permissions")
if(permissions!=""){
    //to do 灵活控制菜单
    getPathSet(menu2)
}else{
    getPathSet(menu)
}

function Bread ({ location }) {
  let pathNames = []
  location.pathname.substr(1).split('/').map((item, key) => {
    if (key > 0) {
      pathNames.push((pathNames[key - 1] + '-' + item).hyphenToHump())
    } else {
      pathNames.push(('-' + item).hyphenToHump())
    }
  })
  const breads = pathNames.map((item, key) => {
    if (!(item in pathSet)) {
      item = 'Dashboard'
    }
    return (
      <Breadcrumb.Item key={key} {...((pathNames.length - 1 === key) || !pathSet[item].clickable) ? '' : { href: '#' + pathSet[item].path }}>
        {pathSet[item].icon
          ? <Icon type={pathSet[item].icon} />
          : ''}
        <span>{pathSet[item].name}</span>
      </Breadcrumb.Item>
    )
  })

  return (
    <div className={styles.bread}>
      <Breadcrumb>
        <Breadcrumb.Item href='#/'><Icon type='home' />
          <span>主页</span>
        </Breadcrumb.Item>
        {breads}
         {//<div style={{float:'right'}}>
           // 二氧化硫SO2（μg/m³）、二氧化氮NO2（μg/m³）、颗粒物PM10（μg/m³）、一氧化碳CO（mg/m³）、臭氧O3（μg/m³）、颗粒物PM2.5（μg/m³）
         //</div>
         }
      </Breadcrumb>
     
    </div>
  )
}

Bread.propTypes = {
  location: PropTypes.object
}

export default Bread
