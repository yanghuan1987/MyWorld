import random
# HandList = ['石头', '剪子', '布']
totalMoney = 1000
times = 0
while totalMoney > 0:
    Hand = input('请输入你的手势：石头--1，剪子---2，布---3，4---退出,赢了+10分，输了扣100\n')
    RandHand = random.randint(1, 3)
    if Hand.isdigit():
        intHand = int(Hand)
        times += 1
        # HandSystem = HandList[RandHand]
        if intHand > 4:
            print('你打错数字了哦！\n')
        elif intHand == 4:
            break
        else:
            if intHand == RandHand:
                print('平局！\n')
            elif intHand == 1 and RandHand == 2:
                totalMoney += 10
                print('你赢了！加10分，你还剩 %d 分\n' % totalMoney)
            elif intHand == 1 and RandHand == 3:
                totalMoney -= 100
                print('你输了！减100分，你还剩 %d 分\n' % totalMoney)
            elif intHand == 2 and RandHand == 1:
                totalMoney -= 100
                print('你输了！减100分，你还剩 %d 分\n' % totalMoney)
            elif intHand == 2 and RandHand == 3:
                totalMoney += 10
                print('你赢了！加10分，你还剩 %d 分\n' % totalMoney)
            elif intHand == 3 and RandHand == 2:
                totalMoney -= 100
                print('你输了！减100分，你还剩 %d 分\n' % totalMoney)
            elif intHand == 3 and RandHand == 1:
                totalMoney += 10
                print('你赢了！加10分，你还剩 %d 分\n' % totalMoney)
    else:
        print('只能输入1-4的数字哦！\n')
print('你一共完了 %d 次\n' % times)
