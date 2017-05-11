package main

import (
	"fmt"
	"unsafe"
)

var global string
var inta int
var uin uint

func main() {

	fmt.Println("hello,world!")
	fmt.Println("Ni hao China!")
	var t = true
	//变量定义第一种
	var vname1 string
	vname1 = "value1"
	//变量定义第二种
	var vname2 = "value2"
	//变量定义第三种（一般形式，不适用于全局变量与赋值)
	vname3 := "value3"
	fmt.Println(t, vname1, vname2, vname3)
	var v1, v2, v3 uint
	v1, v2, v3 = 1, 2, 3
	v4, v5, v6 := 4, 5, 6
	var (
		a int
		b bool
	)
	// &v1获取内存地址
	fmt.Println(&v1, v2, v3, v4, v5, v6, a, b)
	const ab = "string"
	const chang = 5
	const kuan = 10
	const area = chang * kuan
	fmt.Println("面积为", area)
	const (
		u1 = "snv"
		u2 = len(u1)
		u3 = unsafe.Sizeof(vname2)
	)
	println(u1, u2, u3)
	/*
		iota在const关键字出现时将被重置为0(const内部的第一行之前)，
		const中每新增一行常量声明将使iota计数一次(iota可理解为const语句块中的行索引)。
		使用iota能简化定义，在定义枚举时很有用。
	*/
	const iotaa = iota //a=0
	const (
		iotab = iota //b=0
		iotac        //c=1
	)
	type AudioOutput int
	const (
		outMute   AudioOutput = iota //0
		outMono                      //1
		outStereo                    //2
		_                            //下划线跳过不想要的值
		_
		outSurround //5
	)
	type Allergen int
	const (
		lgEggs      Allergen = 1 << iota //1<<0=00000001
		lgChocolate                      //1<<1 = 00000010
		lgNuts                           //1<<2 = 00000100
	)
	const (
		Apple, Banana = iota + 1, iota + 2
		Cherimoya, Durian
		Elderberry, Fig
		/*
			Apple:1
			Banana:2
			Cherimoya:2
			Durian:3
			Elderberry:3
			Fig:4
		*/
	)
	const (
		ai1 = iota //0
		ai2        //1
		ai3 = 3.14
		ai4 //2
	)
	/*
		&a; 将给出变量的实际地址。
		*a; 是一个指针变量
	*/
	var ca int = 4
	var cb int32
	var cc float32
	var ptr *int
	fmt.Printf("第 1 行 - a 变量类型为 = %T\n", ca)
	fmt.Printf("第 2 行 - cb 变量类型为 = %T\n", cb)
	fmt.Printf("第 3 行 - cc 变量类型为 = %T\n", cc)

	ptr = &ca
	fmt.Printf("a 的值为  %d\n", ca)
	fmt.Printf("*ptr 为 %d\n", *ptr)
	println(ptr)
	if ca > a {
		a = 5
		fmt.Printf("a = %d\n", a)
	}
	grade := "B"
	marks := 90
	switch marks {
	case 90:
		grade = "A"
	case 80:
		grade = "B"
	case 50, 60, 70:
		grade = "C"
	default:
		grade = "D"
	}

	switch {
	case grade == "A":
		println("bast")
	case grade == "B", grade == "C":
		println("good")
	case grade == "D":
		println("normal")
	case grade == "F":
		println("bad")
	default:
		println("worse")
	}
	println("your level is", grade)

	var x interface{}

	switch i := x.(type) {
	case nil:
		fmt.Printf("x type is:%T", i)
	case int:
		fmt.Printf("x is int")
	case float64:
		println("x is float64")
	case func(int) float64:
		println("x is func(int)")
	case bool, string:
		println("x is bool or string")
	default:
		println("UNKNOW TYPE")
	}

	/*
		以下描述了 select 语句的语法：

			每个case都必须是一个通信
			所有channel表达式都会被求值
			所有被发送的表达式都会被求值
			如果任意某个通信可以进行，它就执行；其他被忽略。
			如果有多个case都可以运行，Select会随机公平地选出一个执行。其他不会执行。
			否则：
				如果有default子句，则执行该语句。
				如果没有default字句，select将阻塞，直到某个通信可以运行；Go不会重新对channel或值进行求值。
	*/
	var c1, c2, c3 chan int
	var i1, i2 int
	select {
	case i1 = <-c1:
		println("received", i1, "from c1\n")
	case c2 <- i2:
		println("sent", i2, "to c2")
	case i3, ok := (<-c3): // same as: i3, ok := <-c3
		if ok {
			println("received", i3, "from c3")
		} else {
			println("c3 is closed")
		}
	default:
		println("no communication")
	}
	/*
		Go语言的For循环有3中形式，只有其中的一种使用分号。
		和 C 语言的 for 一样：
			for init; condition; post { }
		和 C 的 while 一样：
			for condition { }
		和 C 的 for(;;) 一样：
			for { }
	*/
	bcount := 15
	var acount int

	number := [6]int{1, 2, 3, 5}
	for acount := 0; acount < 10; acount++ {
		println("a's value is ", acount)
	}
	for acount < bcount {
		acount++
		println("a's value is", acount)
	}
	for i, x := range number {
		fmt.Printf("第 %d 位 x的值= %d\n", i, x)
	}

	/* 定义局部变量 */
	var i, j int

	for i = 2; i < 100; i++ {
		for j = 2; j <= (i / j); j++ {
			if i%j == 0 {
				break // 如果发现因子，则不是素数
			}
		}
		if j > (i / j) {
			fmt.Printf("%d  是素数\n", i)
		}
	}
	agoto := 10
LOOP:
	for a < 20 {
		if a == 15 {
			a++
			goto LOOP
		}
		println("a的值是", agoto)
		a++
	}
}
