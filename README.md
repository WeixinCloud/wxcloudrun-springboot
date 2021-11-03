# wxcloudrun-springboot
微信云托管 springboot框架构建HTTP服务端示例

简介：Spring Boot(https://spring.io/projects/spring-boot) 是 Pivotal 团队在 Spring 的基础上提供的一套全新的开源框架，其目的是为了简化 Spring 应用的搭建和开发过程。了解在微信云托管上如何用springboot框架创建简单的http服务。通过示例创建一张user表，并对其进行增删改查的操作，对应POST/DELETE/PUT/GET四种请求的实现。

版本： JDK11(修改版本，需要同步修改[Dockerfile](https://github.com/WeixinCloud/wxcloudrun-springboot/blob/main/Dockerfile)中的基础镜像)

详细介绍：
1. 一键部署时将默认开通微信云托管中的MySQL，并自动将数据库基本信息传入了环境变量中，可直接使用。（数据库信息获取及配置详情见:[application.yml](https://github.com/WeixinCloud/wxcloudrun-springboot/blob/main/src/main/resources/application.yml)）
2. container.config.json仅用于在微信云托管中创建流水线时配套使用。
  * 如果不使用流水线，而是用本项目的代码在微信云托管控制台手动「新建版本」，则container.config.json配置文件不生效。最终版本部署效果以「新建版本」窗口中手动填写的值为准。
  * 'dataBaseName'和‘executeSQLs’ 两个字段只有在服务第一次部署时生效，后续流水线触发的版本更新不会执行（避免重复初始化数据库）。


示例API列表：

1 根据ID查询用户

* URL路径：
  ```/user/:id```
  
* 请求示例：
```
curl -X GET  http://{ip}:{port}/user/34
```

* 响应示例：
```
{
    "code": 0,
    "data": {
        "id": 34,
        "name": "1231231232131",
        "age": 10,
        "email": "m1779387qqwewqeqwe3123@163.com",
        "phone": "1779aqweqwea3873123@163.com",
        "description": "111",
        "create_time": "2021-11-03T12:25:13Z",
        "update_time": "2021-11-03T12:25:13Z"
    }
}
```


2 新增用户

* URL路径：
  ```/user```
  
* 请求示例：
```
curl http://{ip}:{port}/user \
  -X POST \
  -H 'Content-Type: application/json' \
  -d '{  
      "name":"1231231232131",
      "age":10,
      "email":"m1779387qqwewqeqwe3123@163.com",
      "phone":"1779aqweqwea3873123@163.com",
      "description":"111"
  }'
```

* 响应示例：
```
{
    "code": 0,
    "errorMsg": ""
}
```

3 根据ID修改用户

* URL路径：
  ```/user```
  
* 请求示例：
```
curl http://{ip}:{port}/user \
  -X PUT \
  -H 'Content-Type: application/json' \
  -d '{  
      "id":1,
      "name":"1231231232131",
      "age":10,
      "email":"m1779387qqwewqeqwe3123@163.com",
      "phone":"1779aqweqwea3873123@163.com",
      "description":"111"
  }'
```

* 响应示例：
```
{
    "code": 0,
    "errorMsg": ""
}
```

4 根据ID删除用户

* URL路径：
  ```/user/:id```
  
* 请求示例：
```
curl http://{ip}:{port}/user/1 \
  -X DELETE \
  -H 'Content-Type: application/json' \
  -d '{   }'
```

* 响应示例：
```
{
    "code": 0,
    "errorMsg": ""
}
```