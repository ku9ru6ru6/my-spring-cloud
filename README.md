# my-spring-cloud
所有的除GET方法外的错误提示
``` json
{
  "timestamp": 1494574205810,
  "status": 500,
  "error": "Internal Server Error",
  "message": "Hello 服务出错",
  "path": "/feign-server/api/user/hello"
}
```
-- --
## 导入依赖
```html
    <!-- 导入本地依赖 -->
    <link href="/main.css" rel="stylesheet">
    <script src="/app.js"></script>
    <!-- 导入外部依赖 -->
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/3.2.0/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
```
## 页面样式
### 登录页面: 一个登录框里有账号,密码两个输入框,下面有一个登录按钮和一个注册按钮
### 主页: 一个分页插件，可以根据房间名前缀匹配，还有一个创建房间的按钮
### 聊天页面：一个消息框，一个发送框。消息气泡带有用户名和消息发送日期
``` json
{
    "name": "消息发送者",
    "createTime": "消息创建日期",
    "message": "消息内容"
}
```
-- --
## 用户相关API
### 请求的跟地址 localhost:9003/feign-server/api/user
-- --
### 创建用户 URL: 无 | Method: POST  
请求参数 User对象:
``` java
public class User {
    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;
}
```
返回值 用户Id 类型 Long  
-- --
### 登录接口 URL: /login | Method: POST
请求参数 User对象:
``` java
public class User {
    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;
}
```
返回值 User对象:
``` java
public class User {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;
}
```
-- --
## 房间相关API
### 请求的跟地址 localhost:9003/feign-server/api/room
### 创建房间前的验证重名 URL:/checkName | Method:POST
请求参数 String
``` java
    /**
     * 房间名
     */
    private String name;
```
返回一个布尔值 true 没重名 false 重名
-- --
### 创建房间 URL: 无 | Method: POST
请求参数：Room对象
``` java
public class Room {

    /**
     * 房间创建者的id
     */
    private Long userId;

    /**
     * 房间名
     */
    private String name;

    /**
     *  简介 (可以为Null)
     */
    private String intro;
    
    /**
     * 房间最大人数, 最大不超过20
     */
    private Integer maxNumber;
}
```
-- --
### 分页获取房间列表 URL: /paging | Method: Get
请求参数：对个参数
``` java
@RequestParam(value = "name", required = false) String name,
@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer limit,
@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer offset
```
返回值：Paging对象
``` java
public class Paging<T> {

    private Long total;
    private List<Room> data;
}

class Room {
    /**
     * 主键
     */
    private Long id;

    /**
     * 房间创建者的id
     */
    private Long userId;

    /**
     * 房间名
     */
    private String name;

    /**
     *  简介
     */
    private String intro;

    /**
     * 房间最大人数, 最大不超过20
     */
    private Integer maxNumber;

    /**
     * 创建时间
     */
    private Date createTime;
}
```
-- --
# 数据库名: chat
``` sql
# Dump of table message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `room_id` bigint(20) unsigned NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table room
# ------------------------------------------------------------

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(20) NOT NULL DEFAULT '',
  `intro` varchar(200) DEFAULT '''''',
  `max_number` int(11) NOT NULL DEFAULT '10',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;

INSERT INTO `room` (`id`, `user_id`, `name`, `intro`, `max_number`, `create_time`)
VALUES
	(1,1,'??','房间简介',10,'2017-05-11 00:00:00');

/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL DEFAULT '',
  `password` char(32) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name_password` (`name`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `name`, `password`, `create_time`)
VALUES
	(3,'admin','e10adc3949ba59abbe56e057f20f883e','2017-05-14 13:58:00');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
```


