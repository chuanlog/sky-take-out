# 苍穹外卖Java项目`qq
## 技术栈
* Spring Boot
* MyBatis
* MySQL
## 使用须知
在克隆到本地后，需要修改自己增加配置文件,格式如下，包括数据库连接信息，oss信息等
```yaml
sky:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    host: 
    port: 
    database: 
    username: 
    password: 

  alioss:
    endpoint: 
    access-key-id: 
    access-key-secret: 
    bucket-name: 
```