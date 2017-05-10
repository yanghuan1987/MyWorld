
import os
import time

# 备份源路径
source = r'E:\mysql-5.6.35-winx64\data\pms'

# 备份目标路径
target_dir = r'D:\数据zip备份\PMS'

# WINRAR路径
winrar = r'C:\Program Files\WinRAR\WinRAR.exe'
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