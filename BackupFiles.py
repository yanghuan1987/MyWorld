# !C:\Users\Administrator\PycharmProjects\untitled
# FileName:BackupFiles.py

import os
import time

# 1.The files and directories to be backed up are specified in a list.
source = [r'D:\PMS-本地结构备份', r'D:\CMS设计文档']

# 2.The backup must be stored in a main backup directory
target_dir = r'D:\数据zip备份'

# 3.The files are backed up into a zip files
# 4.The current day is the name of the subdirectories in the main directory
today = target_dir + time.strftime('%Y%m%d')
# The crrent time is the name of the zip archive
now = time.strftime('%H%M%S')

# Take a comment from the user to create the name of the zip file
comment = input('Enter a comment ---->')
if len(comment) == 0:  # check if a comment was entered
    target = today + os.sep + now + '.zip'
else:
    target = today + os.sep + now + '_' + \
        comment.replace(' ', '_') + '.zip'
# Create the subdirectory if it isn't already there
if not os.path.exists(today):
    os.mkdir(today)  # make directory
    print('Successfully created directory', today)
# 5.We use the zip command (in Unix/Linux) to put the files in a zip archive
zip_command = "zip -qr %s %s" % (target, ' '.join(source))
tar = "tar -cvzf %s %s" % (target, ' '.join(source))
print(zip_command)
print(tar)

# Run the backup
if os.system(tar) == 0:
    print('Successful backup to ', target)
else:
    print('Backup Failed')
