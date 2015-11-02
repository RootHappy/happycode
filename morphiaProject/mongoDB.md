# Mongo DB

*创建时间：2015-11-02*
*修改时间：2015-11-02*

## 1.简介
- 是一个面向文档的数据库，为了获得更好的扩展性
- 没有预定义的模式：文档的key和value不是固定的类型和大小，根据需要添加或删除文档变的更容易
- 扩展：纵向扩展（使用计算能力更强的机器）和横向扩展（通过分区将数据分散到更多的机器上）
- 具备支撑现在逐流web应用的关键功能：索引，复制，分片，丰富的查询语法，特别灵活的数据模型
- 可扩展出的功能：二级索引，范围查找，排序，聚合，地理空间索引等

## 2.开发篇

### 2.1 MongoDB基础知识
- 文档是Mongo DB中数据的基本单元，类似与关系型数据库中的行
- 集合可以看做是一个拥有动态模式（dynamic schema）的表
- MongoDB的一个实例可以拥有多个相互独立的数据库，每个数据库都拥有自己的集合
- 每个文档都有一个特殊的键"_id",这个键在文档所属的集合中是唯一的
- 自带了一个简单但功能强大的JavaScript Shell,可用于管理mongoDB的实例或数据操作

#### 2.1.1 文档
- 键值对的有序集，类似与json,文档的键是字符串，同时，区分大小写和类型，顺序也是有序的

```json
{
	"key" : "value",
	"key2" : "value2"
}
```
#### 2.1.2 集合
- 集合就是一组文档，类似与关系型数据库中的一张表，集合是动态模式的，一个集合里面的文档可以是各式各样的。
	- 用集合管理同种类型的文档，方便管理
	- 创建索引
- 命名：
	- 集合名不能是空字符串（“”）
	- 集合名不能包含\0字符，这个字符表示集合名的结束
	- 集合名不能以system.开头，系统集合保留的前缀。
	- 不能包含保留字符$

#### 2.1.3 数据库
- 多个文档组成集合，多个集合组成数据库，一个mongo实例可以承载多个数据库
- 数据库命名：
	- 不能是空字符串（“”）
	- 不得含有/ \ . “ * < > : | ? $ \0
	- 区分大小写，应该全部小写
	- 数据库名最多为64字节
- 数据库最终会变成文件系统里的文件，数据库名就是相应的文件名
- 保留的数据库名：
	- admin root数据库，添加到其中的用户，自动获得数据库的所有权限
	- local 本地，不可以被复制，一台服务器上的所有本地集合都可以存储在这里
	- config 分片信息存储的数据库
- 数据库名.集合名，获得集合的完全限定名，即命名空间

#### 2.1.4 MongoDB启动操作
- 启动服务器端: mongod
	- 没有参数情况下默认数据目录为 /data/db
	- 监听端口27017
	- 启动一个基本的HTTP服务器，监听端口28017，通过浏览器http://localhost:28017，可获得数据库管理信息
- 启动Shell: mongo
	- 是一个功能完备的javaScript解释器，可运行任意的javaScript程序
	- 启动时，shell会连接到MongoDB服务器的test数据库，可使用> db查看数据库

```
>db
test
>use databaseName
swithched to db databaseName
```

- Shell中4个基本操作（CRUD）

	```js
	//Create
	test>post = { "title" : "My Blog Post",
	... "content" : "Here's my blog post.",
	... "date" : new Date()}
	{
		"title" : "My Blog Post",
		"content" : "Here's my blog post.",
		"date" : ISODate("2015-11-02T08:00:33.008Z")
	}
	test>db.blog.insert(post)

	//read
	test>db.blog.findOne()
	{
		"_id" : ObjectId("5637182fd561cab9f6b2f8a3"),
		"title" : "My Blog Post",
		"content" : "Here's my blog post.",
		"date" : ISODate("2015-11-02T08:00:33.008Z")
	}
	test>db.blog.find()
	{ "_id" : ObjectId("5637182fd561cab9f6b2f8a3"), "title" : "My Blog Post", "content" : "Here's my blog post.", "date" : ISODate("2015-11-02T08:00:33.008Z") }

	//update
	test>post.comments = []
	[ ]
	test>db.blog.update({title:"My Blog Post"},post)
	test>db.blog.find()
	{ "_id" : ObjectId("5637182fd561cab9f6b2f8a3"), "title" : "My Blog Post", "content" : "Here's my blog post.", "date" : ISODate("2015-11-02T08:00:33.008Z"), "comments" : [ ] }

	//remove
	test>db.blog.remove({title: "My Blog Post"})
	```


- c
- d
- e

f
d

f
f
f
f
ff
f
f
f
f
f
ff

f
ff
f
f
f

f
f
f








## 3.管理篇

## 4。部署篇