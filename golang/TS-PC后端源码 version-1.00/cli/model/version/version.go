package version

import "cli/util/sqlite"

/**
  查询版本号
 */
func VersionCheck() map[string]interface{}{
	const  sql   = "select * from t_version order by id DESC"
	return  sqlite.FindFirst(sql)
}