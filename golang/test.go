package main

import (
	"fmt"
	"math"
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
	ca := 4
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
	println("the max is ", max(bcount, agoto))
	astring, bstring := swap("mama", "baba")
	println(astring, bstring)
	print(bcount, agoto)
	swaps(&bcount, &agoto)
	print(bcount, agoto)

	getSquareRoot := func(xc float64) float64 {
		return math.Sqrt(xc)
	}

	println(getSquareRoot(9))

	//闭包
	/* nextNumber 为一个函数，函数 i 为 0 */
	nextNumber := getSequence()

	/* 调用 nextNumber 函数，i 变量自增 1 并返回 */
	println(nextNumber())
	println(nextNumber())
	println(nextNumber())

	/* 创建新的函数 nextNumber1，并查看结果 */
	nextNumber1 := getSequence()
	println(nextNumber1())
	println(nextNumber1())
	var cc1 Circle
	cc1.radius = 10.00
	println("Area of Circle(cc1) =", cc1.getArea())

	var balance [5]float32
	balance = [5]float32{100.0, 2.1, 3.2, 41.2, 44.4}
	var vka = [...]int{1, 2, 3, 4, 5}
	balance[2] = 50.0
	vka[4] = 2
	var array = [3][4]int{
		{0, 1, 2, 3},   /*  第一行索引为 0 */
		{4, 5, 6, 7},   /*  第二行索引为 1 */
		{8, 9, 10, 11}, /*  第三行索引为 2 */
	}
	ar := array[2][3]
	println(ar)

	var bal = []int{1000, 2, 4, 5}
	var avg int
	avg = getAverage(bal, 4)
	println(avg)

	var ip *int
	println("目前ip是空指针", ip)
	fmt.Printf("目前ip是空指针 %x\n", ip)
	println("ip是不是空指针", ip == nil)

	ip = &bcount
	println("bcount地址：", &bcount)
	println("bcount指针：", *ip)
	bcount = 20
	println("bcount地址：", &bcount)
	println("bcount指针：", *ip)

	//指针数组
	arraya := []int{10, 100, 1000}
	const MAX int = 3
	var icount int
	var ptrcount [MAX]*int
	for icount = 0; icount < MAX; icount++ {
		ptrcount[icount] = &arraya[icount]
	}
	for icount = 0; icount < MAX; icount++ {
		fmt.Printf("a[%d] = %d\n", icount, *ptrcount[icount])
		println(ptrcount[icount])
	}
	//语言指向指针的指针
	var aptr int
	var pptr *int
	var ppptr **int

	aptr = 3000
	pptr = &aptr
	ppptr = &pptr
	println(aptr)
	println(*pptr)
	println(**ppptr)

	var Book1 Books
	var Book2 Books
	/* book 1 描述 */
	Book1.title = "Go 语言"
	Book1.author = "www.runoob.com"
	Book1.subject = "Go 语言教程"
	Book1.bookId = 6495407

	/* book 2 描述 */
	Book2.title = "Python 教程"
	Book2.author = "www.runoob.com"
	Book2.subject = "Python 语言教程"
	Book2.bookId = 6495700

	/* 打印 Book1 信息 */
	fmt.Printf("Book 1 title : %s\n", Book1.title)
	fmt.Printf("Book 1 author : %s\n", Book1.author)
	fmt.Printf("Book 1 subject : %s\n", Book1.subject)
	fmt.Printf("Book 1 bookId : %d\n", Book1.bookId)

	/* 打印 Book2 信息 */
	fmt.Printf("Book 2 title : %s\n", Book2.title)
	fmt.Printf("Book 2 author : %s\n", Book2.author)
	fmt.Printf("Book 2 subject : %s\n", Book2.subject)
	fmt.Printf("Book 2 bookId : %d\n", Book2.bookId)

	printBook(&Book1)
	printBook(&Book2)

	/*
		语言切片(Slice)
		Go 语言切片是对数组的抽象。
		Go 数组的长度不可改变，在特定场景中这样的集合就不太适用，
		Go中提供了一种灵活，功能强悍的内置类型切片("动态数组"),
		与数组相比切片的长度是不固定的，可以追加元素，在追加时可能使切片的容量增大。
		或使用make()函数来创建切片:
		var slice1 []type = make([]type, len)
		也可以简写为
		slice1 := make([]type, len)
		也可以指定容量，其中capacity为可选参数。
		make([]T, length, capacity)
	*/
	var numberArray = make([]int, 3, 5)
	printSlice(numberArray)
	numberArray = nil
	printSlice(numberArray)
	numberArray = []int{0, 1, 2, 3, 4, 5, 6, 7, 8}
	printSlice(numberArray)
	printSlice(numberArray[1:4])
	printSlice(numberArray[:3])
	printSlice(numberArray[4:])
	numberArray = append(numberArray, 9)
	printSlice(numberArray)
	numberArray1 := make([]int, len(numberArray), (cap(numberArray))*2)
	copy(numberArray1, numberArray)
	printSlice(numberArray1)
	sum := 0
	for _, num := range numberArray {
		sum += num
	}
	println(sum)

	for i, num := range numberArray {
		if num == 3 {
			println("index = ", i)
		}
	}
	//range也可以用在map的键值对上。
	kvs := map[string]string{"a": "apple", "b": "banana"}
	for k, v := range kvs {
		fmt.Printf("%s -> %s \n", k, v)
	}
	//range也可以用来枚举Unicode字符串。第一个参数是字符的索引，第二个是字符（Unicode的值）本身。
	for i, c := range "go" {
		println(i, c)
	}
}
func printSlice(x []int) {
	fmt.Printf("len=%d cap=%d slice=%v\n", len(x), cap(x), x)
}
func max(num1, num2 int) int {
	var result int
	if num1 > num2 {
		result = num1
	} else {
		result = num2
	}
	return result
}
func swap(x, y string) (string, string) {
	return y, x
}

/*
	参数的引用传递
	影响实际参数
*/
func swaps(x *int, y *int) {
	var temp int
	temp = *x
	*x = *y
	*y = temp
}

/*
	 Go 语言支持匿名函数，可作为闭包。匿名函数是一个"内联"语句或表达式。
	 匿名函数的优越性在于可以直接使用函数内的变量，不必申明。

	以下实例中，我们创建了函数 getSequence() ，返回另外一个函数。该函数的目的是在闭包中递增 i 变量，代码如下
*/
func getSequence() func() int {
	i := 0
	return func() int {
		i++
		return i
	}
}

/*
	Go 语言中同时有函数和方法。一个方法就是一个包含了接受者的函数，
	接受者可以是命名类型或者结构体类型的一个值或者是一个指针。
	所有给定类型的方法属于该类型的方法集。下面定义一个结构体类型和该类型的一个方法：
*/
type Circle struct {
	radius float64
}

//该 method 属于 Circle 类型对象中的方法

func (c Circle) getArea() float64 {
	//c.radius 即为 Circle 类型对象中的属性
	return 3.14 * c.radius * c.radius
}

/*
	以下实例，实例中函数接收整型数组参数，另一个参数指定了数组元素的个数，并返回平均值：
*/
func getAverage(arr []int, size int) int {
	var i, sum int
	var avg int
	for i = 0; i < size; i++ {
		sum += arr[i]
	}
	avg = sum / size

	return avg
}

/*
	定义结构体
	结构体定义需要使用 type 和 struct 语句。struct 语句定义一个新的数据类型，
	结构体有中一个或多个成员。type 语句设定了结构体的名称。结构体的格式如下：
*/
type Books struct {
	title   string
	author  string
	subject string
	bookId  int
}

func printBook(book *Books) {
	fmt.Printf("Book title : %s\n", book.title)
	fmt.Printf("Book author : %s\n", book.author)
	fmt.Printf("Book subject : %s\n", book.subject)
	fmt.Printf("Book bookId : %d\n", book.bookId)
}
