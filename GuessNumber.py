import random
print('Let us play a game.')
print('I hava a number,you guess it,I will tell you it big or small')
number = random.randint(1, 500)
print('input the number and you have 6 times to guess it\n ')
count = 0
total_times = 10  # 总共可输入次数
limit_times = 6  # 限制次数，一般情况小于等于总次数
for i in range(0, total_times):
    count += 1
    less_times = limit_times - i
    value = input('you have ' + str(less_times) + ' times,input the number\n ')
    if value.isdigit():
        # 超过限制次数
        if count == limit_times:
            print('\nI am sorry,you have no chance')
            break
        # 输入数字小于随机数
        if int(value) < number:
            print('input number is small')
            result = 'fault'
            continue
        # 输入数字大于随机数
        elif int(value) > number:
            print('input number is big')
            result = 'fault'
            continue
        else:
            print('you get it!')
            result = 'win'
            break
    else:
        print('You waste a time,please input a number!')
if result == 'win':
    print('\nYou are Winner!')
