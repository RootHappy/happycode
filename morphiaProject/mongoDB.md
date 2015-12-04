# Mongo DB

*创建时间：2015-11-02*
*修改时间：2015-11-30*

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

### 2.1.5 数据类型

- 基本数据类型（json）
	- null : 用于表示空值或者不存在的值
	- 布尔型 : true和false
	- 数值 : 默认使用64位浮点型数值，对于整型值，可使用NumberInt（4字节带符号）类或NumberLong类（8字节带符号）
	- 字符串 ： UTF-8字符串
	- 数组 : 数据列表或数据集 ["a","b","c"]
	- 内嵌文档 : {"x" : { "foo" : "bar" } }
- 扩展数据类型(mongo 新增bson)
	- 日期 ： 存储自新纪元经过的毫秒数，不存储时区
	- 正则表达式 ： {"x" : /foobar/i}语法与javaScrip一致
	- 对象id : 是一个12字节的ID,是文档唯一标志
	- 二进制数据 ： 可用来保存非UTF-8的字符串
	- 代码 ： 查询和文档中可以包含任意的javaScript代码
**_id和ObjectId理解 ： 每个文档都有一个"_id"键，默认类型为ObjectId对象，在集合中唯一。ObjectId是"_id"默认类型，不同的机器都能用用全局唯一的同种方法方便地生成。**
**ObjectId使用了12字节的存储空间，是一个由24个十六进制数字组成的字符串
	- 1-4：标准纪元开始的时间戳，单位为秒，提供秒级别的唯一性
	- 5-7：主机唯一标志，通常是主机名的散列值，提供机器唯一
	- 8-9：进程表示符（PId），提供进程唯一
	- 10-12 : 计数器，提供同一个进程的同一秒产生的Id唯一，疫苗中最多允许有256^3^个不同的ObjectId
**

### 2.1.6 使用Mongo Shell
- mongo用法：mongo [options] [db address] [file names(ending in .js)]
	- db address
		- foo	foo database on local machine
		- 192.168.160.78/foo	foo database on 192.168.160.78 machine
		- 192.168.160.78:27017/foo foo database on 192.168.160.78 machine on port 27017
	- options
		- --nodb : don't connect to mongod on startup
		- --norc : will not run the ".mongorc.js" file on start up
		- -h [--help] : show this usage information
		- --port arg : port to connect to
		- --host arg : server to connect to
		- --version : show version information
		- -u [--username] arg : username for authentication
		- -p [--password] arg : password for authentication
		- --shell : run the shell after executing files
		- ................
	- file names : a list of files to run. files have to end in .js and will exit after unless --shell is specified

- help ： 查看mongo帮助命令
- 使用Shell执行脚本
	- mongo -u username -p password host:port/database scritp1.js scritp2.js
	- mongo -u username -p password host:port/database后进入，load("script1.js");
- 在脚本中可以访问db变量，以及其他全局变量；然而，shell辅助函数不可以在文件中使用。可以使用脚本将变量注入到shell中
- 定制Shell提示，参见.mongorc.js中prompt变量
- 设置shell编辑器，EDITOR变量，在.mongorc.js中设置：EDITOR="编辑路径"
- 集合名称包含保留字或者无效的JavaScript属性名称，db.getCollection("CollectionName");db[collectionName];

### 2.2 创建、更新和删除文档

### 2.2.1 插入文档

- 插入一个文档：
	```js
	db.foo.insert({"key":"value"});
	```
- 插入多个文档：
	```js
	db.foo.insert([{"key1":"value1"},{"key2":"Value2"},{"key3":"value3"}]);
	```
- 如果在执行批量插入的过程中有一个文档插入失败，那么在这个文档之前的所有文档都会成功插入到集合中，而这个文档以后的所有文档全部插入失败。

### 2.2.2 删除文档

- 删除文档 db.remove({"Key":"value"});		//{}代表删除所有

### 2.2.3 更新文档

- 文档属性的增删改
	- 增、改：obj.fieldName = value;
	- 删	： delete obj.fieldName;
- 如果两个更新同时发生，先到达服务器的先执行，接着执行另外一个。
- 文档替换 : 修改查到的文档后更新
	```js
	//1.原文档
	{
		"_id" : ObjectId("565c1ca0910e50b18f586124"),
		"name" : "joe",
		"friends" : 32,
		"enemies" : 2
	}
	//2.修改文档
	>var joe = db.foo.findOne({"name":"joe"});
	>joe.relationships = {"friends":joe.friends,"enemies":joe.enemies};
	>joe.username = joe.name;
	>delete joe.friends;
	>delete joe.enemies
	>delete joe.name;
	>db.foo.update({"name":"joe"},joe);

	//3.更新后的文档
	{
		"_id" : ObjectId("565c1ca0910e50b18f586124"),
		"relationships" : {
			"friends" : 32,
			"enemies" : 2
		},
		"username" : "joe"
	}
	```

- 使用修改器 : 通常文档只会更新一部分，使用原子性的更新修改器对指定字段进行更新（修改键、增加键、删除键、操作数组、操作内嵌文档）

	- $set修改器：指定一个字段，如果这个字段不存在，则创建它；存在则更新。
	```js
	//1.原文档
	{
		"_id" : ObjectId("565cf8ed22512d7f44f5008c"),
		"name" : "joe",
		"age" : 30,
		"sex" : "male",
		"location" : "wisconsin"
	}

	//2.修改操作
	db.foo.update({"age":30},{"$set":{"favorite book":"war and peace"}});
	db.foo.update({"_id":ObjectId("565cf8ed22512d7f44f5008c")},{"$set":{"a":"b"}});
	//3.结果
	{
		"_id" : ObjectId("565cf8ed22512d7f44f5008c"),
		"name" : "joe",
		"age" : 30,
		"sex" : "male",
		"location" : "wisconsin",
		"favorite book" : "war and peace",
		"a" : "b"
	}
	//$set可以修改键的值，和添加新的键

	//$set修改内嵌文档
	{
		"_id" : ObjectId("565cfe285f3731e41a31ccea"),
		"title" : "A Blog Post",
		"content" : "asdf",
		"author" : {
			"name" : "Joe",
			"email" : "joe@example.com"
		}
	}
	db.foo.update({"_id" : ObjectId("565cfe285f3731e41a31ccea")},{"$set":{"author.name":"joe schmoe"}});
	{
		"_id" : ObjectId("565cfe285f3731e41a31ccea"),
		"title" : "A Blog Post",
		"content" : "asdf",
		"author" : {
			"name" : "Joe",
			"email" : "joe@example.com"
		}
	}
	```
	- $unset修改器：删除一个键
	```js
	//操作
	db.foo.findOne({"_id":ObjectId("565cf8ed22512d7f44f5008c")});
	//结果
	{
		"_id" : ObjectId("565cf8ed22512d7f44f5008c"),
		"name" : "joe",
		"age" : 30,
		"sex" : "male",
		"location" : "wisconsin",
		"favorite book" : "war and peace"
	}
	```
	- $inc修改器：增加已有的键的值，如果该键不存在就创建一个。
	```js
	//插入操作数据
	db.foo.insert({"game":"pinball","user":"joe"})
	//原始数据
	{
		"_id" : ObjectId("565cffce336955650eaa6b12"),
		"game" : "pinball",
		"user" : "joe"
	}
	//使用$inc
	db.foo.findOne({"_id" : ObjectId("565cffce336955650eaa6b12")});
	//结果
	{
		"_id" : ObjectId("565cffce336955650eaa6b12"),
		"game" : "pinball",
		"user" : "joe",
		"score" : 50
	}
	//操作2
	db.foo.update({"_id" : ObjectId("565cffce336955650eaa6b12")},{"$inc":{"score":10000}});
	//结果2
	{
		"_id" : ObjectId("565cffce336955650eaa6b12"),
		"game" : "pinball",
		"user" : "joe",
		"score" : 10050
	}
	//$inc只能用于整数，长整数或者双精度浮点型
	```
	- 数组修改器
		- $push : 向已有的数组末尾加入一个元素，要是没有就创建一个新的数组。
		```js
		//原始数据
		{
			"_id" : ObjectId("565cfe285f3731e41a31ccea"),
			"title" : "A Blog Post",
			"content" : "asdf",
			"author" : {
				"name" : "joe schmoe",
				"email" : "joe@example.com"
			}
		}
		//使用$push
		db.foo.update({"_id" : ObjectId("565cfe285f3731e41a31ccea")},{"$push":{"comments":{"name":"joe","email":"joe@example.com","content":"nice Post."}}});
		//结果
		{
			"_id" : ObjectId("565cfe285f3731e41a31ccea"),
			"title" : "A Blog Post",
			"content" : "asdf",
			"author" : {
				"name" : "joe schmoe",
				"email" : "joe@example.com"
			},
			"comments" : [
				{
					"name" : "joe",
					"email" : "joe@example.com",
					"content" : "nice Post."
				}
			]
		}
		//第二次使用，数组已经存在
		db.foo.update({"_id" : ObjectId("565cfe285f3731e41a31ccea")},{"$push":{"comments":{"name":"bob","email":"bob@example.com","content":"good Post."}}});
		//结果2
		{
			"_id" : ObjectId("565cfe285f3731e41a31ccea"),
			"title" : "A Blog Post",
			"content" : "asdf",
			"author" : {
				"name" : "joe schmoe",
				"email" : "joe@example.com"
			},
			"comments" : [
				{
					"name" : "joe",
					"email" : "joe@example.com",
					"content" : "nice Post."
				},
				{
					"name" : "bob",
					"email" : "bob@example.com",
					"content" : "good Post."
				}
			]
		}
		```
		$push与其他修改器一起使用
		```js
		//$each
		db.foo.update({"_id" : ObjectId("565cfe285f3731e41a31ccea")},{"$push":{"prices":{"$each":[234,240,254]}}});
		//结果
		{
			"_id" : ObjectId("565cfe285f3731e41a31ccea"),
			"title" : "A Blog Post",
			"content" : "asdf",
			"author" : {
				"name" : "joe schmoe",
				"email" : "joe@example.com"
			},
			"comments" : [
				{
					"name" : "joe",
					"email" : "joe@example.com",
					"content" : "nice Post."
				},
				{
					"name" : "bob",
					"email" : "bob@example.com",
					"content" : "good Post."
				}
			],
			"prices" : [
				234,
				240,
				254
			]
		}

		//$slice -n :限制数组的最大长度为n，必须为负值，只包含最后添加到数组中的n个元素
		//$sort：根据字段对数组进行排序，
		//注意：不能只将$slice或者$sort与push配合使用，且必须使用$each
		db.foo.update(
			{"_id" : ObjectId("565cfe285f3731e41a31ccea")},
			{"$push":{
				"top10":{
					"$each":[
					{"name":"Nightmare on Elm street","rating":6.6},{"name":"Saw","rating":4.3}],
					"$slice":-10,
					"$sort":{"rating":-1}
						}
				}
			});
		```
		- $ne :避免添加重复的值到数组
		- $addToTest ： 避免添加重复的值到数组，可以和$each一起使用，同时添加多个值
		```js
		db.foo.update({"prices":{"$ne":146}},{"$push":{"prices":146}});
		//添加一个不重复素到数组末尾，重复则不添加
		db.foo.update({"_id" : ObjectId("565cfe285f3731e41a31ccea")},{"$addToSet":{"prices":178}});
		//不重复的添加多个元素
		db.foo.update({"_id" : ObjectId("565cfe285f3731e41a31ccea")},{"$addToSet":{"prices":{"$each":[123,145,240,178,345]}}});
		```
		- 删除元素
			- {"$pop":{"key":1}}:从数组末尾删除
			- {"$pop":{"key":-1}}：从数组头部删除
			- {"$pull"{"key":"value"}}:删除数组中指定条件的值
	- 基于位置的数组修改器：数组下标或者$

- upsert操作:一种特殊的更新，如果没有找到符合更新条件的文档，就会以这个更新条件和更新文档为基础创建一个新文档；如果找到了指定的文档，则正常更新。db.update({},{},true);第三个参数，指定更新为upsert
- $setOnInsert: 在创建文档时，设置指定值，后期不可变
- Save Shell帮助函数：save是一个shell函数，如果文档不存在，自动创建文档，如果文档存在就更新该文档

- 更新多个文档：默认情况下，更新只能对匹配条件的第一个文档执行操作。要是有多个文档，只有第一个会被更新，如果要更新匹配的多个文档，可以将update的第四个参数设置为true(后期版本可能变化为默认更新多个文档)
- getLastError命令：返回最后一次操作的相关信息db.runCommand({gerLastError:1});
- 返回被更新的文档

```js
db.runCommand({
	"findAndModify":"collectionName",
	"query":{"key":"value"},
	"sort":{"key":-1},
	"update":{...},	//与remove必须存在一个,也只能存在一个
	"remove":true/false,
	"new":true/false,//返回更新前的文档，还是更新后的文档，默认更新前的文档
}).value;
```

#### 2.2.4 写入安全机制

- 应答式写入
- 非应答式写入
注：MongoClient是默认使用应答式写入的，其他的客户端如：Mongo/Connection...使用非应答式写入

### 2.3 查询

- 使用find()或者findOne()和查询文档对数据库执行查询
- 使用$条件查询 实现范围查询、数据集包含查询、不等式查询等
- 查询返回游标，游标只有在需要时才将需要的文档批量返回
- 对游标执行的元操作等。

#### 2.3.1 find

- 查询就是返回一个集合中文档的子集
- 空的查询文档（{}）会匹配集合全部内容，不知道查询文档，默认为空文档
- 指定需要返回的文档的键值，指定find的第二个参数来指定想要的键（{"key":1,"Key2":0 ...}）,1代表返回该键，0表示不返回该键
- 限制：传递给数据库的查询文档的值必须是常量，不能引用文档中其他键的值

#### 2.3.2 查询条件

- 范围、OR子句和取反
- $ne(!=)、$lt（<）、$lte(<=)、$gt(>)、$gte(>=),可组合使用{"age":{"$gte":18,"$lte":30}}(18 <= age <= 30)
- OR查询
	- $in:用来查询一个键的多个值（一次查询key的多个value）:{"key":{"$in":[value1,value2,value3...]}}
	- $nin:返回与数组中所有条件都不匹配的文档
	- $or：在多个键中查询任意的给定值（一次查询多个key对应的value）
- $not:元条件句，即可以用在任何其他条件之上。对其他条件取反：{"key":{"$not":{"$mod":[5,1]}}}
- $mod：取模运算符，{"key":{"$mod":[5,1]}},将查询的值除以第一个给定值，若余数等于第二个值则匹配成功
- 条件语句是内层文档的键，而修改器则是外层文档的键，可以对一个键应用多个条件

- $and:{"$and":[{A},{B},{C}]}---->A && B && c
- $or：{"$or":[{A},{B},{C}]}------>A || B || c
- $not:{"$not":{C}}--------------->~C

#### 2.3.3 特殊类型的查询

- 匹配null值：{"key":null}-->匹配key为null，和没有key的文档
- 匹配null值并且该键存在：{"key":{"$in":[null],"$exists":true}}
- 正则表达式：能够灵活有效的匹配字符串{"key":/joe/i}---->忽略大小写查找key的值为joe，MongoDB使用Perl兼容的正则表达式（PCRE）库，来匹配正则表达式
- 查询数组：查询数组与查询标量值是一样的。
	- $all : 通过多个元素来匹配数组就需要使用$all->{"key":{"$all":[a,b,c,d]}}
	- key.index ：查询数组特定未知的元素->{"key.2":"value"}(数组下标从0开始)
	- $size : 查询特定长度的数组。{"key":{"$size":n}}
	- $slice : 返回数组元素的一个子集
		- {"KeyArray":{"$slice":n}}前n条
		- {"KeyArray":{"$slice":-n}}后n条
		- {"KeyArray":{"$slice":[index,length]}},从下标index开始的length个元素
	- 返回一个匹配的数组元素：find({"keyArray.keyField":"value"},{"keyArray.$":1});
	- 数组与范围查找的相互作用：数组中的一个元素与查询条件匹配，也会返回该文档，对数组进行范围查询不是确定。
		- $elemMatch:同时使用查询条件中的两个语句与一个数组元素进行比较，但是不会匹配非数组元素{"key":{"$elemMatch":{"$gt":10,"$lt":20}}}
		- min()和max():如果当前查询的字段上创建果索引，可以使用min和max将查询条件遍历的索引范围限制在查询条件之内db.foo.find("age":{"$gt":10,"$lt":20}).min({"age":10}).max({"age":20})

- 查询内嵌文档
	- 查询整个文档：内嵌文档的域的顺序会影响查询结果
	- 只针对内嵌文档的键值进行查询

#### 2.4 游标
- 数据库使用游标返回find的执行结果，可以使用游标对限制结果数目，略过部分结果，按任意键排序等操作
- 创建一个游标：对集合进行查询find（）,并将结果分配给一个局部变量

```js
	for(var i = 0; i < 100; i++) {
		db.foo.insert({"x":i});
	}
	var cursor = db.foo.find();
	while(cursor.hasNext()) {
		obj = cursor.next();
	}
	//游标还实现了javaScript迭代器接口
	cursor.forEach(function(x){
		print(x.x);
	});
```
- 调用find()，Shell并不立即查询数据库，而是等待真正开始要求获取结果时才发送查询，可以在执行之前可以给查询附加额外的选项：sort(),limit(),skip(),几乎游标对象的每个方法都返回游标本身。
- limit():限制返回结果的数目
- skip()：忽略一定数目的结果
- sort()：对结果排序

```js
	//返回三个结果,指定的是上限
	db.foo.find().limit(3);
	//略果前三个文档，文档数目少于3个则不会返回任何结果
	db.foo.fidn().skip(3);
	//对结果排序:1代表升序，-1代表降序，当指定了多个键，按照被指定的顺序逐个排序
	db.foo.find().sort({"key1":1,"key2":-1});
	//实现分页
	db.foo.find().limit(50).sort({"key":1});	//第一页
	db.foo.find().limit(50).skipe(50).sort({"key":1});//下一页，但是略过过多的文档会导致性能问题
	//不使用skip完成分页
	var page1 = db.foo.find().limit(50).sort({}"key":1});//第一页
	var lastest = null;
	while(page1.hasNext()) {
		lastest = page1.next();
	}
	var page2 = db.foo.find({"key":{"$gt":lastest.key}}).sort({"key":1}).limit(50);
```
- 避免是由skip略过大量的结果
- 获取一直的结果：文档变大后会移动到文档末尾，游标会再次读取
- 游标的生命周期：客户端游标和客户端游标表示的数据库游标
- 数据库命令：运行db.listCommands()查看所有数据库命令