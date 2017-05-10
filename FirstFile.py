#!/user/bin/python
# -*- coding: UTF-8 -*-

import sys; x = "I am king"; sys.stdout.write(x + ' in the world of WarCraft\n\n')
print('hello world')
print('你好!')

if True:
    print('this is true')
else:
    print('this is false')

print(False)

item_1 = 1
item_2 = 3
item_3 = 3
total = item_1 + \
        item_2 + \
        item_3
print(total)

days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday']

'''
多行注释
'''

"""
多行注释
"""

'''
value = input('\n\ninput on word for next step!\n')输入一个字符
print(value)
'''
'''拼接'''

any_words = 'avlwojrpekfmygnuhi '
l = "{0}{1}{2}{3}{4}{5}".format(any_words[-1].upper(), any_words[2], any_words[4],
                                any_words[1], any_words[8], any_words[-1])
o = f'{any_words[5].upper()}{any_words[-4]}{any_words[0]}{any_words[-5]}{any_words[-1]}'
v = f'{any_words[10].upper()}{any_words[4]}{any_words[6]}{any_words[8]}{any_words[1]}{any_words[8]}{any_words[6]}'
e = "{0}{1}{2}{3}".format(any_words[-7].upper(), any_words[0], any_words[-5], any_words[-6])
print(f'{e}{l}{o}{v}')
print(-1//2)  # 进行floor运算，返回比真正的商小的最接近的数字
import math
print(math.trunc(-1/2.0))
print(type(l))
print(type(item_1))
中国 = 'China'
_中国 = 'China'
'''
import calendar

print("Below is year 2016 calendar: ")
calendar.prcal(2016)
'''
a = True
b = False
c = True
print(a or b)
print(abs(-10))
print(math.e)
print(R"\n")
strn = "www.baidu.com"
print(strn.center(40, "*"))  # 居中填充
print(strn.ljust(40, "*"))  # 左填充
print(strn.rjust(40, "*"))  # 右填充
print(strn.upper())
s1 = "-"
s2 = ""
seq = ("r", "u", "n", "o", "o", "b")  # 字符串序列
print(s1.join(seq))
print(s2.join(seq))  # 返回合并字符串
print(len(s2.join(seq)))  # 返回字符串长度
intab = "aeiou"

outtab = "12345"

# 创建字符映射的转换表，对于接受两个参数的最简单的调用方式，第一个参数是字符串，表示需要转换的字符，第二个参数也是字符串表示转换的目标
trantab = str.maketrans(intab, outtab)
str1 = "this is string example....wow!!!"
print(str1.translate(trantab))

str1 = "www.w3cschool.cc"
print("菜鸟教程新地址：", str1)
print("菜鸟教程新地址：", str1.replace("w3cschool.cc", "runoob.com"))

str1 = "this is string example....wow!!!"
print(str1.replace("is", "was", 3))  # 把 将字符串中的 str1 替换成 str2,如果 max 指定，则替换不超过 max 次
strNull = None

seq = ('name', 'age', 'sex')
dict = dict.fromkeys(seq)
print("新的字典为 : %s" % str(dict))

dict = dict.fromkeys(seq, 10)
print("新的字典为 : %s" % str(dict))
dict = {'Name': 'Runoob', 'Age': 27}

print ("Age 值为 : %s" % dict.get('Age'))

print ("Sex 值为 : %s" % dict.get('Sex', "N/A"))

print('Name' in dict)

dict.setdefault('新来的', '没有就加一嘛')

print("value : %s" % dict.items())
a, b = 0, 1
while b < 100:
    print(b, end=",")
    a, b = b, a+b

Forever, count = 100, 1
while Forever != 100:
    count += 1
    print(count)
    pass
print('\n')
for x in range(1, 100, 33):
    print("%d" % x)

list=[1, 2, 3, 4]
it = iter(list)  # 创建一个迭代器对象
for x in it:
    print(x, end=" ")
print("next()\n")
'''
while True:
    try:
        print(next(it))
    except StopIteration:
        sys.exit()
'''


'''
def fibonacci(n): # 生成器函数 - 斐波那契
    a, b, counter = 0, 1, 0
    while True:
        if (counter > n):
            return
        yield a
        a, b = b, a + b
        counter += 1
f = fibonacci(10) # f 是一个迭代器，由生成器返回生成

while True:
    try:
        print(next(f), end=" ")
    except StopIteration:
        sys.exit()
'''
# 全局变量和局部变量
totalall = 0


def sum(arg1, arg2):
    totalall = arg1 + arg2
    print(totalall)
    return totalall

sum(1, 2)
print(totalall)
水果 = {'红色': '苹果', '黄色': '香蕉', '紫色': '葡萄'}
for k, v in 水果.items():
    print(k, v)
for index, ve in enumerate(水果):
    print(index, ve)
问题 = ['名字', '国家', '年龄', '爱好']
答案 = ['小王', '中国', '18', '唱歌']
for questions, answers in zip(问题, 答案):
    print('问题是你的{0}？ 回答是{1}。'.format(questions, answers))
for 倒叙 in reversed(问题):
    print(倒叙)
for pathName in sys.path:
    print(pathName)
'''
f = open('c:/tmp/newfile.txt', 'w')
f.write('我是一个新手，正在学习python\n')
f.close()
'''
print('over')


