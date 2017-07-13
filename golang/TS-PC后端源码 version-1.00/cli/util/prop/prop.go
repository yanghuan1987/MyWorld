package prop

import (
	"gopkg.in/ini.v1"
)

var cfg *ini.File
var err error

func init() {
	//dir:=util.GetCurrentDirectory()
	cfg, err = ini.Load("config.ini")
	if err != nil {
		println(err.Error())
		panic(err)
	}
}
func Get(section string, key string) string {

	val, err := cfg.Section(section).GetKey(key)
	if err != nil {
		println(err.Error())
	}
	return val.String()
}
