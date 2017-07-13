package cookie

import "cli/util/sqlite"

type Cookie struct {
	name string //cookie名称
	value string //cookie值
}

/**
  登陆成功将cookie存入sqlite
 */

func AddCookie(name string,value string,username string) bool{
	const  sql   = "insert into t_cookie(name,value,account) values (?,?,?)"
	return  sqlite.Insert(sql,name,value,username)
}

/**
  cookie 过期后删除
 */

func DelCookie() bool{
	const sql  = "delete from t_cookie where name = 'JSESSIONID'"
	return  sqlite.UpdateOrDelete(sql)
}

/**
  查詢cookie
 */

func QueryCookie() map[string]interface{}{
	const sql  = "SELECT * FROM t_cookie where name = 'JSESSIONID'"
	return  sqlite.FindFirst(sql)
}