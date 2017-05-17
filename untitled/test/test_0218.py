import numpy as np
import pandas as pd
data = {'color':['blue','green','yellow','red','white'],'object':['ball','pen','pencil','paper','mug'],'price':[1.2,1.0,0.6,0.9,1.7]}
frame = pd.DataFrame(data)
#print frame['object'][0:2]
s1 = pd.Series([3,2,5,1],['white','yellow','green','blue'])
#print s1
# print frame[['price','color']]
# print np.ones(3)
data2 = np.array([[1,2,3,4],[5,6,7,8]])
# print data2
frame2 = pd.DataFrame(data2,columns=[2011,2012,2013,2014])
print frame2