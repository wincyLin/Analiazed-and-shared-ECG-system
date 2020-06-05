#!E:\Python\python.exe
# -*- coding: UTF-8 -*-

# 添加GI处理模块
import cgi, cgitb
# 读取mysql数据
import pymysql
from pylab import *
from PIL import Image
from PCV.localdescriptors import harris
from PCV.tools.imtools import imresize

# 创建FieldStorage的实例化
form = cgi.FieldStorage()
# 获取html页面传递过来的数据值
name = form.getvalue('name')
phone = form.getvalue('phone')
sex = form.getvalue('sex')
age = form.getvalue('age')
beizhu = form.getvalue('beizhu')
pic = form.getvalue('pic')

# 打印输出
print("Content-type:text/html")
print(" ")
print("<html>")
print("<head>")
print("<meta charset=\"utf-8\">")
print("<title>POST</title>")
print("</head>")
print("<body>")
print("<h2>name:%s,phone:%s,age:%s,sex:%s,beizhu:%s</h2>" % (name, phone, age, sex, beizhu))
print("</body>")
print("</html>")



# 读入图像
im = array(pic.convert('L'))
# Image.open('pic.jpg')
# 检测harris角点
harrisim = harris.compute_harris_response(im)

# Harris响应函数m
harrisim1 = 255 - harrisim

figure()
gray()

#画出Harris响应图
subplot(121)
suptitle("Harris corners")
imshow(harrisim1)
print (harrisim1.shape)
axis('off')
axis('equal')

threshold = [0.01, 0.05, 0.1]
for i, thres in enumerate(threshold):
    filtered_coords = harris.get_harris_points(harrisim, 6, thres)

subplot(1, 2, 2)
imshow(im)
print (im.shape)
plot([p[1] for p in filtered_coords], [p[0] for p in filtered_coords], '+c')
axis('off')
show()

#
# # Figure 2-2下面的图
# im1 = array(Image.open("../pic/.png").convert("L"))
# im2 = array(Image.open("../pic/.png").convert("L"))
#
# # resize to make matching faster
# im1 = imresize(im1, (im1.shape[1]/2, im1.shape[0]/2))
# im2 = imresize(im2, (im2.shape[1]/2, im2.shape[0]/2))
#
# wid = 5
# harrisim = harris.compute_harris_response(im1, 5)
# filtered_coords1 = harris.get_harris_points(harrisim, wid+1)
# d1 = harris.get_descriptors(im1, filtered_coords1, wid)
#
# harrisim = harris.compute_harris_response(im2, 5)
# filtered_coords2 = harris.get_harris_points(harrisim, wid+1)
# d2 = harris.get_descriptors(im2, filtered_coords2, wid)
#
# print ('starting matching')
# matches = harris.match_twosided(d1, d2)
#
# figure()
# suptitle("Harris matching")
# gray()
# harris.plot_matches(im1, im2, filtered_coords1, filtered_coords2, matches)
# show()
