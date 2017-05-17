#coding=utf-8
import pandas as pd
filename = 'E:\\projects\\dtw_file\\lixl_merge_20170105.csv'
data = pd.read_csv(filename,encoding='utf-8')
x = data.iloc[:,1:9].as_matrix()
y = data.iloc[:,9].as_matrix()
print x
#numpy.savetxt('new.csv',my_matrix,delimiter=',')