package http_client

import (
	"io/ioutil"
	"net/http"
	"strings"
	"net/url"
	"encoding/json"
	"bytes"
	"crypto/tls"
)
func PostSync(url string,params map[string]string,successFn func(string,[]*http.Cookie),failFn func(error,int)) {
	s := "";
	//遍历map
	if(len(params)>0){
		i:=0
		for k, v := range params {
			if i==0{
				s+=k+"="+v
			}else{
				s+="&"+k+"="+v
			}
			i++
		}
	}
	resp, err := http.Post(url,"application/x-www-form-urlencoded",strings.NewReader(s))
	if err != nil {
		// handle error
		failFn(err,0)
		return
	}
	statusCode:=resp.StatusCode
	if statusCode!=200{
		failFn(err,statusCode)
	}
	defer resp.Body.Close()
	cookies:=resp.Cookies()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		// handle error
		failFn(err,1)
		return
	}

	successFn(string(body),cookies)
}
func GetSync(url string,params map[string]string) interface{} {

	s := "?";
	if strings.Index(url,"?")>0{
		s=""
	}
	//遍历map
	if(len(params)>0){
		url+=s
		for k, v := range params {
			url+="&"+k+"="+v
		}
	}
	resp, err := http.Get(url)
	if err != nil {
		// handle error
		return nil
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		// handle error
		return nil
	}

	return string(body)
}

func Get(url string,params map[string]string,successFn func(string),failFn func(error)) {
	s := "?";
	if strings.Index(url,"?")>0{
		s=""
	}
	//遍历map
	if(len(params)>0){
		url+=s
		for k, v := range params {
			url+="&"+k+"="+v
		}
	}

	resp, err := http.Get(url)
	if err != nil {
		// handle error
		failFn(err)
		return
	}

	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		// handle error
		failFn(err)
		return
	}

	successFn(string(body))
}
func Post(url string,params map[string]string,successFn func(string),failFn func(error)) {
	s := "";

	//遍历map
	if(len(params)>0){
		i:=0
		for k, v := range params {
			if i==0{
				s+=k+"="+v
			}else{
				s+="&"+k+"="+v
			}
			i++
		}
	}

	resp, err := http.Post(url,"application/x-www-form-urlencoded",strings.NewReader(s))
	if err != nil {
		// handle error
		failFn(err)
		return
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		// handle error
		failFn(err)
		return
	}

	successFn(string(body))
}

func Request(requestUrl string,method string,headers map[string]string,cookies []*http.Cookie,params map[string]string) interface{}{
	values := url.Values{}
	if(len(params)>0) {
		for k, v := range params {
			values.Set(k, v)
		}
	}
	data := ioutil.NopCloser(strings.NewReader(values.Encode())) //把form数据编下码
	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{Transport: tr}
	req, _ := http.NewRequest(method, requestUrl, data)
	if method=="POST"{
		req.Header.Set("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
		req.Header.Set("Cache-Control","no-cache")
	}

	if(len(headers)>0) {
		for k, v := range headers {
			req.Header.Set(k, v)
		}
	}
	if(len(cookies)>0) {
		for _, v := range cookies {
			req.AddCookie(v)
		}
	}
	resp, err := client.Do(req) //发送
	if err != nil {
		// handle error
		return nil
	}
	statusCode:=resp.StatusCode
	if statusCode!=200{
		return "0"
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		// handle error
		return nil
	}
	return string(body)
}

func RequestJson(requestUrl string,method string,headers map[string]string,cookies []*http.Cookie,params map[string]interface{}) interface{}{
	bytesData, err := json.Marshal(params)
	if err != nil {
		return nil
	}
	reader := bytes.NewReader(bytesData)
	client := &http.Client{}
	req, _ := http.NewRequest(method, requestUrl, reader)
	if method=="POST"{
		req.Header.Set("Content-Type", "application/json;charset=UTF-8")
		req.Header.Set("Cache-Control","no-cache")
	}

	if(len(headers)>0) {
		for k, v := range headers {
			req.Header.Set(k, v)
		}
	}
	if(len(cookies)>0) {
		for _, v := range cookies {
			req.AddCookie(v)
		}
	}
	resp, err := client.Do(req) //发送
	if err != nil {
		// handle error
		return nil
	}
	statusCode:=resp.StatusCode
	if statusCode!=200{
		return "0"
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		// handle error
		return nil
	}
	return string(body)
}

