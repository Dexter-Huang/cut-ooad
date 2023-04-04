# 工程简介
```
natapp:

https://www.bilibili.com/video/BV1w44y1379q/?spm_id_from=333.788&vd_source=55105e332d29aa64b03aec9810005d7f
```

## Task
- 添加背景图片(不同大小的屏幕 图片都能正常显示)
- 美化css样式(element-plus 可能存在内置css, 组件的一些css不能更改)
  设计以下文件
- 建议:不要用px, 多用 rem 单位
    element-plus 组件文档:
    
    https://element-plus.gitee.io/zh-CN/component/layout.html#%E5%9F%BA%E7%A1%80%E5%B8%83%E5%B1%80
 ```
 hotel-user-ui\src\login\ 里面的文件
Login.vue register.vue ForgetPassword.vue ResetPassword.vue

```

## 如何克隆代码
```
git clone https://your token@github.com/Feioyue/CS309-Project.git

cd hotel-test2-user

npm install

然后drop掉原来的数据库, 将IDEA里面的hotel.sql 新建数据库

先启动redis

再启动IDEA (resources/application.properties 修改mysql 用户名和密码)

最后 cd hotel-test2-user 

npm install
```




## 如何提交代码：

```
git add .
git commit -m "your change info"
git push
```

## 如何覆盖自己的代码然后再git pull:

```
git fetch --all

git reset --hard origin/main

git pull

大不了删了再Git clone一遍
```

## 目录简介
\hotel-test2-user为后台管理端

克隆后把整个CS309文件夹拖进IDEA即可



# 延伸阅读

