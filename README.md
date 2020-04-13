


## 项目介绍

`mall`项目是一套电商系统，只包括后台管理系统，基于SpringBoot+MyBatis实现 后台管理系统包含商品管理、订单管理、会员管理、促销管理、运营管理、内容管理、统计报表、财务管理、权限管理、设置等模块。

### 组织结构

``` lua

├── mall-admin -- 后台商城管理系统接口
└── mall-model -- MyBatisGenerator生成的数据库操作代码

```

### 技术选型

#### 后端技术

| 技术                 | 说明                | 官网                                                         |
| -------------------- | ------------------- | ------------------------------------------------------------ |
| SpringBoot           | 容器+MVC框架        | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| SpringSecurity       | 认证和授权框架      | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security) |
| MyBatis              | ORM框架             | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| MyBatisGenerator     | 数据层代码生成      | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html) |
| PageHelper           | MyBatis物理分页插件 | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper) |
| Swagger-UI           | 文档生产工具        | [https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) |
| Hibernator-Validator | 验证框架            | [http://hibernate.org/validator/](http://hibernate.org/validator/) |
| Redis                | 分布式缓存          | [https://redis.io/](https://redis.io/)                       |
| Druid                | 数据库连接池        | [https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
| OSS                  | 对象存储            | [https://github.com/aliyun/aliyun-oss-java-sdk](https://github.com/aliyun/aliyun-oss-java-sdk) |
| JWT                  | JWT登录支持         | [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt) |
| Lombok               | 简化对象封装工具    | [https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |

#### 前端技术

| 技术       | 说明                  | 官网                                                         |
| ---------- | --------------------- | ------------------------------------------------------------ |
| Vue        | 前端框架              | [https://vuejs.org/](https://vuejs.org/)                     |
| Vue-router | 路由框架              | [https://router.vuejs.org/](https://router.vuejs.org/)       |
| Vuex       | 全局状态管理框架      | [https://vuex.vuejs.org/](https://vuex.vuejs.org/)           |
| Element    | 前端UI框架            | [https://element.eleme.io/](https://element.eleme.io/)       |
| Axios      | 前端HTTP框架          | [https://github.com/axios/axios](https://github.com/axios/axios) |
| v-charts   | 基于Echarts的图表框架 | [https://v-charts.js.org/](https://v-charts.js.org/)         |
| Js-cookie  | cookie管理工具        | [https://github.com/js-cookie/js-cookie](https://github.com/js-cookie/js-cookie) |
| nprogress  | 进度条控件            | [https://github.com/rstacruz/nprogress](https://github.com/rstacruz/nprogress) |

## 环境搭建
后台管理系统功能演示
    http://p3.pstatp.com/large/pgc-image/0aaeb883efd449f5a08ec24e0b974f25
### 开发环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 5.7    | https://www.mysql.com/                                       |
| Redis         | 3.2    | https://redis.io/download                                    |
## 参考资料

- [Spring实战（第4版）](https://book.douban.com/subject/26767354/)
- [Spring Boot实战](https://book.douban.com/subject/26857423/)
- [Spring Cloud微服务实战](https://book.douban.com/subject/27025912/)
- [Spring Cloud与Docker微服务架构实战](https://book.douban.com/subject/27028228/)
- [Spring Data实战](https://book.douban.com/subject/25975186/)
- [MyBatis从入门到精通](https://book.douban.com/subject/27074809/)
- [深入浅出MySQL](https://book.douban.com/subject/25817684/)
- [循序渐进Linux（第2版）](https://book.douban.com/subject/26758194/)
- [Elasticsearch 权威指南](https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html)
- [Elasticsearch 技术解析与实战](https://book.douban.com/subject/26967826/)
- [MongoDB实战(第二版)](https://book.douban.com/subject/27061123/)
- [Kubernetes权威指南](https://book.douban.com/subject/26902153/)
- [Pro Git](https://git-scm.com/book/zh/v2)

