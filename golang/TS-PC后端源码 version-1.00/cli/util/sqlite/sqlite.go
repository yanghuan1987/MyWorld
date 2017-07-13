package sqlite

import (
	_ "github.com/mattn/go-sqlite3"
	"database/sql"
	"log"
	"strconv"
	"cli/util/prop"
	"math"
)
var dbmap *sql.DB

func initDb() *sql.DB {
	if dbmap != nil {
		return dbmap
	}
	path:=prop.Get("db", "path")
	dbmap, err := sql.Open("sqlite3", path)
	if err != nil {
		log.Fatal(err)
		return nil
	}
	dbmap.SetMaxOpenConns(2000)
	dbmap.SetMaxIdleConns(1000)
	dbmap.Ping()
	return dbmap
}

func Insert(sql string,params ...interface{}) bool {
	db:=initDb()
	if db==nil{
		return false
	}
	defer db.Close()
	stmt, err:= db.Prepare(sql)
	if err!=nil{

		return false
	}
	defer stmt.Close()
	_,err=stmt.Exec(params...)
	if err!=nil{

		return false
	}
	return true
}

func UpdateOrDelete(sql string, params ...interface{}) bool {
	dbmap := initDb()
	if dbmap==nil{

		return false
	}
	defer dbmap.Close()
	stmt, err := dbmap.Prepare(sql)

	if err != nil {
		// proper error handling instead of panic in your app

		return false
	}
	defer stmt.Close() // Close the statement when we leave main() / the program terminates
	ret, err := stmt.Exec(params...)
	if err != nil {

		return false
	}
	_, err = ret.RowsAffected()
	if err != nil {

		return false
	}
	return true
}

func FindFirst(sql string, params ...interface{}) map[string]interface{} {
	dbmap := initDb()
	if dbmap==nil{

		return nil
	}
	defer dbmap.Close()
	sql = sql + " limit 1"
	rows, err := dbmap.Query(sql, params...)
	if err != nil {

		return nil
	}
	defer rows.Close()


	columns, _ := rows.Columns()
	scanArgs := make([]interface{}, len(columns))
	values := make([]interface{}, len(columns))
	record := make(map[string]interface{})
	for i := range values {
		scanArgs[i] = &values[i]
	}
	var len = 0
	for rows.Next() {
		//将行数据保存到record字典
		err = rows.Scan(scanArgs...)
		if err != nil {
			// proper error handling instead of panic in your app

		}
		for i, col := range values {
			if col != nil {
				switch col.(type) {
				case []byte:
					record[columns[i]] = string(col.([]uint8))
				default:
					record[columns[i]] = col
				}
			}
		}
		len++
		break
	}
	if len > 0 {
		return record
	}
	return nil
}

func Count(sql string, params ...interface{}) int {
	dbmap := initDb()
	if dbmap==nil{
		return 0
	}
	defer dbmap.Close()
	sql = sql + " limit 1"
	rows, err := dbmap.Query(sql, params...)
	if err != nil {
		return 0
	}
	defer rows.Close()


	columns, _ := rows.Columns()
	scanArgs := make([]interface{}, len(columns))
	values := make([][]byte, len(columns))
	var count = "0"
	for i := range values {
		scanArgs[i] = &values[i]
	}

	for rows.Next() {
		//将行数据保存到record字典
		err = rows.Scan(scanArgs...)

		for _, col := range values {
			if col != nil {
				count = string(col)
				break
			}
		}
		break
	}
	num, _ := strconv.Atoi(count)
	return num
}
func FindAll(sql string, params ...interface{}) []map[string]interface{} {
	dbmap := initDb()
	records := []map[string]interface{}{}
	if dbmap==nil{
		return records
	}
	defer dbmap.Close()

	rows, err := dbmap.Query(sql, params...)
	if err != nil {
		return records
	}
	defer rows.Close()


	columns, _ := rows.Columns()
	scanArgs := make([]interface{}, len(columns))
	values := make([][]byte, len(columns))

	for i := range values {
		scanArgs[i] = &values[i]
	}

	for rows.Next() {
		//将行数据保存到record字典
		err = rows.Scan(scanArgs...)
		record := make(map[string]interface{})
		for i, col := range values {
			if col != nil {
				record[columns[i]] = string(col)
			}
		}
		records = append(records, record)
	}
	return records
}

func Paginate(col string, val int64, count int, query string, sqlExceptSelect string, params ...interface{}) map[string]interface{} {
	if count == 0 {
		count = 20
	}
	dbmap := initDb()
	defer dbmap.Close()

	data := make(map[string]interface{})

	queryCount := "select count(*) as count " + sqlExceptSelect
	totalRows := Count(queryCount, params...)
	if totalRows == 0 {
		data["total"] = 0
		return data
	}

	sql := query + " " + sqlExceptSelect + " group by " + col + " having " + col + " < " + strconv.FormatInt(val, 10) + " order by " + col + " desc limit " + strconv.Itoa(count)
	if val == 0 {
		sql = query + " " + sqlExceptSelect + " group by " + col + " order by " + col + " desc limit " + strconv.Itoa(count)

	}
	rows, err := dbmap.Query(sql, params...)
	defer rows.Close()

	if err != nil {
		println(err.Error())
	}
	columns, _ := rows.Columns()
	scanArgs := make([]interface{}, len(columns))
	values := make([][]byte, len(columns))
	records := []map[string]string{}
	for i := range values {
		scanArgs[i] = &values[i]
	}

	for rows.Next() {
		//将行数据保存到record字典
		err = rows.Scan(scanArgs...)
		record := make(map[string]string)
		for i, col := range values {
			if col != nil {
				record[columns[i]] = string(col)
			}
		}
		records = append(records, record)
	}
	data["total"] = totalRows
	data["count"] = count
	data["data"] = records
	return data
}

/**
   分页
 */
func Paginate2(offset int, length int, query string, sqlExceptSelect string, params ...interface{}) map[string]interface{} {
	dbmap := initDb()
	defer dbmap.Close()
	data := make(map[string]interface{})

	queryCount := "select count(*) as count " + sqlExceptSelect
	totalRows := Count(queryCount, params...)
	if totalRows == 0 {
		data["totalRows"] = 0
		data["totalPages"] = 0
		data["list"]=[]map[string]interface{}{}
		return data
	}
	sql := query + " " + sqlExceptSelect + " limit " + strconv.Itoa(offset) + "," + strconv.Itoa(length)
	rows, err := dbmap.Query(sql, params...)
	defer rows.Close()

	if err != nil {
		println(err.Error())
	}
	columns, _ := rows.Columns()
	scanArgs := make([]interface{}, len(columns))
	values := make([][]byte, len(columns))
	records := []map[string]string{}
	for i := range values {
		scanArgs[i] = &values[i]
	}

	for rows.Next() {
		//将行数据保存到record字典
		err = rows.Scan(scanArgs...)
		record := make(map[string]string)
		for i, col := range values {
			if col != nil {
				record[columns[i]] = string(col)
			}
		}
		records = append(records, record)
	}
	totalPages := int(math.Floor(float64(totalRows)/float64(length)) + 1)
	if totalRows%length == 0 {
		totalPages = totalRows / length
	}
	data["totalRows"] = totalRows
	data["totalPages"] = totalPages
	data["list"] = records
	return data
}
