def cont(n):
    if n <= 1:
        return n
    else:
        return cont(n-1) + cont(n-2)
number = input('请输入一个数字')

if number.isdigit() and int(number) > 0:
    number = int(number)
    for i in range(number):
        print('第 {0} 个数字是 {1}'.format(i, cont(i)))
else:
    print('请输入正确数字')
