#上传图片

>上传图片到后台，后台把图片信息存入表中，再图片存入七牛云中。

>流程：

>1：页面携带参数（图片名称、图片类目，图片文件）

>2：后台接收参数，把图片的信息存入库中。

>3：把生成的临时图片存储到七牛云中，然后再删除临时文件夹


#生成表情包图片

>数据库中该图片的生成数+1，同时上传到七牛云。在原来图片名称的基础上面增加前缀（前缀的规则：）然后成功了之后，回显给前台浏览器，浏览器更新图片 

>1：页面携带图片的主键值+uuid的图片url值，生成表情包的一些参数

>2：后台接收参数，生成临时图片上传到七牛云，跟新数据库的记录

>3:新增的七牛云的前缀的定义：generator + 当前时间戳 + 原来的uuid


