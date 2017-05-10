import os
import sys
import time
r'''
path = r"D:\test"
today = time.strftime('%Y%m%d')
now = time.strftime('%Y%m%d%H%M%S')
for (path, dirs, files) in os.walk(path):
    for filename in files:
        newname = filename[0:-4] + "_" + now + filename[-4:]
        oldname = path + "\\" + filename
        print(oldname)
        targetname = path + "\\" + newname
        print(targetname)
        print(dirs)
        os.rename(oldname, targetname)
'''

files = os.listdir(r'D\test')  # 路径可以自己

for name in files:
    a = os.path.splitext(name)
    if a[1] == '.txt':
        newname = a[0] + '.py'
        os.rename(name, newname)