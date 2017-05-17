#coding=UTF-8
import pandas as pd
import codecs
f=open('./TestFile.csv','r')
lines = f.readlines()
f.close()
print "======第一次====="
for line in lines:
    print line


print "======第二次====="
filename = './TestFile.csv'
data = pd.read_csv(filename)
print data

print "======第三次====="
def ReadFile(filePath,encoding):
    with codecs.open(filePath,"r",encoding) as f:
        return f.read()
def WriteFile(filePath,u,encoding):
    with codecs.open(filePath,"w",encoding) as f:
        f.write(u)
'''''
定义GBK_2_UTF8方法，用于转换文件存储编码
'''
def GBK_2_UTF8(src,dst):
    content = ReadFile(src,encoding='gbk')
    WriteFile(dst,content,encoding='utf_8')


'''''
qyx.csv文件使用GBK编码存储，现在将其转为UTF_8存储
'''
src = './TestFile.csv'
dst = './qyx_utf8.csv'
GBK_2_UTF8(src,dst)

filename = './qyx_utf8.csv'
data = pd.read_csv(filename)
print data