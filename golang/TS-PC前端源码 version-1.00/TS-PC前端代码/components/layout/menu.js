import React from 'react'
import { Menu, Icon } from 'antd'
import { Link } from 'dva/router'
import {menu,menu2} from '../../utils'

const permissions =localStorage.getItem("permissions")==undefined?'':localStorage.getItem("permissions")
let array=permissions.split(",")
let newMenu=menu

if(permissions!=""){
    //to do 灵活控制菜单
    newMenu=menu2
}

/*
if(permissions!=""){
 newMenu=filterMenu(menu)
}*/

const topMenus = newMenu.map(item => item.key)

//重置menu
function filterMenu(menu){
  let newMenu=[]
  menu.map(function(item){
    if(item.child){
      //遍历chilld
      let flag=0
      let objs=[]
      item.child.map(function(child){
        if(permissions.indexOf(','+child.action+',')>=0){
          objs=objs.concat(child)
          flag++
        }
      })
      //如果存在flag>0,新增
      if(flag>0){
          newMenu=newMenu.concat({
            key: item.key,
            name: item.name,
            icon:item.icon,
            clickable: item.clickable,
            child:objs
          })
      }
      
    }else{
        if(permissions.indexOf(','+item.action+',')>=0){
          newMenu=newMenu.concat(item)
        }
    }
  })
  return newMenu
}
const getMenus = function (menuArray, siderFold, parentPath) {
  parentPath = parentPath || '/'
  return menuArray.map(item => {
    
    if (item.child) {
      
      return (
        <Menu.SubMenu key={item.key} title={<span>{item.icon ? <Icon type={item.icon} /> : ''}{siderFold && topMenus.indexOf(item.key) >= 0 ? '' : item.name}</span>}>
          {getMenus(item.child, siderFold, parentPath + item.key + '/')}
        </Menu.SubMenu>
      )
    } else {
      return (
        <Menu.Item key={item.key}>
          <Link to={parentPath + item.key}>
            {item.icon ? <Icon type={item.icon} /> : ''}
            {siderFold && topMenus.indexOf(item.key) >= 0 ? '' : item.name}
          </Link>
        </Menu.Item>
      )
    }
  })
}

function Menus ({ siderFold, darkTheme, location, isNavbar, handleClickNavMenu, navOpenKeys, changeOpenKeys }) {
  const menuItems = getMenus(newMenu, siderFold)

  const onOpenChange = (openKeys) => {
    const latestOpenKey = openKeys.find(key => !(navOpenKeys.indexOf(key) > -1))
    const latestCloseKey = navOpenKeys.find(key => !(openKeys.indexOf(key) > -1))
    let nextOpenKeys = [];
    if (latestOpenKey) {
      nextOpenKeys = getAncestorKeys(latestOpenKey).concat(latestOpenKey)
    }
    if (latestCloseKey) {
      nextOpenKeys = getAncestorKeys(latestCloseKey)
    }
    changeOpenKeys(nextOpenKeys)
  }
  const getAncestorKeys = (key) => {
    const map = {
      // navChildParent: ['navParent'],
      navigation2: ['navigation']
    }
    return map[key] || []
  }
  let menuProps = {}
  if(!siderFold) {//菜单栏收起时，不能操作openKeys
    menuProps = {
      onOpenChange: onOpenChange,
      openKeys: navOpenKeys
    }
  }

  return (
    <Menu
      {...menuProps}
      mode={siderFold ? 'vertical' : 'inline'}
      theme={darkTheme ? 'dark' : 'light'}
      onClick={handleClickNavMenu}
      defaultSelectedKeys={[location.pathname.split('/')[location.pathname.split('/').length - 1] || 'dashboard']}>
      {menuItems}
    </Menu>
  )
}

export default Menus
