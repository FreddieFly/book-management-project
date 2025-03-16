# 图书管理系统 部署指南

## 环境要求
- JDK 17+
- Maven 3.8+
- Redis 6.2+
- IDE（推荐 IntelliJ IDEA 或 Eclipse）

## 快速开始

### 1. Redis 服务配置

### 使用 Docker 启动 Redis（推荐方式）
docker run -d --name redis-server -p 6379:6379 redis:6.2-alpine

### 或使用 Homebrew 安装
brew install redis

brew services start redis



### 2. 应用配置
### 修改配置文件（从项目根目录开始）：
./book-management-system/src/dev-configs/application-general.yml

Yaml

redis:

host: 127.0.0.1

port: 6379

password: # 若未设置密码请留空



## 3. 服务启动顺序
建议按以下顺序启动服务（使用 IntelliJ IDEA）：

服务名称	模块路径	启动类	端口
注册中心	book-management-registry	RegistryServerStarter.java	18080

API 网关	book-management-gateway	GatewayServerStarter.java	18082

认证服务	book-management-auth	AuthAppStarter.java	18083

图书服务	book-management-book	BookAppStarter.java	18081



## 4. 接口文档访问
   Swagger 聚合文档地址：http://localhost:18082/swagger-ui.html

   服务文档切换方式：
   在 Swagger 页面右上角点击 "Select a spec"
   选择需要访问的服务文档：

   auth: 认证服务接口

   book: 图书服务接口

服务监控

注册中心控制台：http://localhost:18080

H2链接

http://localhost:18083/h2-console/login.do?jsessionid=e8b9d8324a4ea4992cba0e014119ff56

http://localhost:18084/h2-console/login.do?jsessionid=e8b9d8324a4ea4992cba0e014119ff56

    username: sa

    password: password