lowNu = int(input('Statr Number'))
upNu = int(input('End Number'))
List = []
for i in range(lowNu, upNu + 1):
    if i > 1:
        for m in range(2, i):
            if (i % m) == 0:
                break
        else:
            List.append(i)
print(List)
pass
