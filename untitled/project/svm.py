#coding=UTF-8
import pandas as pd
filename = 'E:\\projects\\dtw_file\\lixl_merge_20170330(train).csv'
data = pd.read_csv(filename,encoding='utf-8')
X_train = data.iloc[:,1:9].as_matrix()
y_train = data.iloc[:,9].as_matrix()

from sklearn.svm import LinearSVC
lsvc = LinearSVC()
lsvc.fit(X_train,y_train)

# from sklearn import svm
# lsvc = svm.SVC(kernel='precomputed')
# lsvc.fit(X_train,y_train)

# from sklearn.svm import NuSVC
# lsvc = NuSVC()
# lsvc.fit(X_train,y_train)

test_filename = 'E:\\projects\\dtw_file\\lixl_merge_20170403(predict).csv'
test_data = pd.read_csv(test_filename,encoding='utf-8')
X_test = test_data.iloc[:,1:9].as_matrix()
y_predict = lsvc.predict(X_test)

predict_data = pd.DataFrame({'abc':test_data.iloc[:,0],'bcd':y_predict})
predict_data.to_csv('E:\\projects\\dtw_file\\lixl_predict_20170403-linear.csv',index=False,header=False)
# print pd.DataFrame({'abc':test_data.iloc[:,0],'bcd':y_predict})