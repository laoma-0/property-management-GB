# 智慧物业管理系统（property-management-GB）项目介绍
<img width="2560" height="1528" alt="QQ20250813-213324" src="https://github.com/user-attachments/assets/44930203-01a2-4b35-a547-89471892c804" />
<img width="2560" height="1528" alt="QQ20250813-213350" src="https://github.com/user-attachments/assets/dbad8738-e65b-46d1-a443-faad9d6ce21b" />
<img width="2560" height="1528" alt="QQ20250813-213358" src="https://github.com/user-attachments/assets/d089fb52-da2e-4c61-8a29-2ff7a704ed3c" />
<img width="2560" height="1528" alt="QQ20250813-213405" src="https://github.com/user-attachments/assets/4b41a183-8c74-4c41-b178-c78d457eed33" />
<img width="2560" height="1528" alt="QQ20250813-213420" src="https://github.com/user-attachments/assets/1f9e65ae-ba73-4381-bd1c-e91fa0c607f4" />
<img width="2560" height="1528" alt="QQ20250813-233028" src="https://github.com/user-attachments/assets/87e454be-18d2-4eea-b4c7-cfa09cf97f45" />


## 项目概述
该项目是一个基于前后端分离架构的**智慧物业管理系统**，旨在通过数字化手段提升物业管理效率，实现小区楼栋、房间、业主信息、报修服务、公告发布等核心业务的线上管理。项目名称从代码及配置中可明确为“智慧物业管理系统”，适合中小型小区物业日常运营使用。


## 技术栈架构

### 1. 后端技术栈
- **核心框架**：Spring Boot 3.2.5（用于快速开发RESTful API，简化配置与部署）。
- **数据访问**：MyBatis 3.0.3（持久层框架，通过XML映射文件实现数据库操作）。
- **数据库**：MySQL 8.0.32（关系型数据库，存储小区、用户、楼栋等核心数据）。
- **API文档**：SpringDoc OpenAPI（通过`SwaggerConfig`配置，自动生成接口文档，便于前后端联调）。
- **异常处理**：通过`GlobalExceptionHandler`统一捕获和处理异常，返回标准化错误信息。


### 2. 前端技术栈
- **核心框架**：Vue 3（采用`<script setup>`语法糖简化组件逻辑）。
- **构建工具**：Vite（替代Webpack，提供更快的开发热更新和打包速度）。
- **UI组件库**：Element Plus（提供表格、表单、对话框等现成组件，统一界面风格）。
- **状态管理**：Pinia（管理全局状态，如用户登录信息、系统配置等）。
- **路由管理**：Vue Router（实现页面跳转与权限控制）。
- **网络请求**：Axios（封装统一请求工具，与后端API交互，如`api/repair.js`、`api/property.js`等）。


## 核心功能模块

### 1. 基础信息管理
- **楼栋管理**：通过`BuildingController`提供楼栋的CRUD接口（查询、新增、修改、删除），对应数据库`building`表，支持按ID查询或获取所有楼栋信息。
- **房间管理**：关联楼栋信息，存储房号、面积、业主ID等数据（`room`表），支持按楼栋筛选房间。
- **小区信息管理**：维护小区名称、地址、物业联系电话等基础信息（`community`表）。


### 2. 用户与权限管理
- **用户管理**：支持系统用户（业主、物业人员等）的信息维护，前端通过`api/sysUser.js`、`api/user.js`提供用户更新接口，后端对应`sys_user`表。
- **角色区分**：通过权限控制不同角色（如业主、物业人员）的可访问功能（如业主无法访问管理后台，物业可发布公告）。


### 3. 公告管理
- **公告发布与查询**：物业人员可发布公告（包含标题、内容、发布时间、是否置顶等），存储于`announcement`表；前端通过`api/announcement.js`获取公告详情，支持按ID查询。
- **示例数据**：初始化SQL中包含“停水通知”“消防演习”等示例公告，展示实际业务场景。


### 4. 报修管理
- **报修订单处理**：业主可提交报修请求（包含房间ID、问题描述等），前端通过`api/repair.js`的`addRepairOrder`接口提交，后端对应`repair`相关表存储数据。
- **状态跟踪**：支持报修状态（待处理、处理中、已完成）的管理与展示。


### 5. 系统概览
- 前端首页通过`api/home.js`的`getSystemOverview`接口获取系统核心数据统计（如用户总数、楼栋总数等），以可视化卡片展示。


## 项目结构

```
property-management-GB/
├── .gitattributes/.gitignore       # Git配置文件
├── README.md                       # 项目说明文档
├── mvnw/mvnw.cmd/pom.xml           # Maven配置（后端依赖与构建）
├── package.json/package-lock.json  # 前端依赖配置
├── sql/                            # 数据库脚本
│   ├── init_smart_property_management.sql  # 初始化表结构与示例数据
│   └── smart_property_management.sql       # 核心数据库脚本
├── src/main/                       # 后端源代码
│   ├── java/org/example/
│   │   ├── controller/             # 控制器（如BuildingController）
│   │   ├── entity/                 # 实体类（如Announcement、Building）
│   │   ├── common/                 # 通用工具（Result结果封装、异常处理）
│   │   ├── config/                 # 配置类（如SwaggerConfig）
│   │   └── PropertyManagementGbApplication.java  # 启动类
│   └── resources/
│       └── application.properties  # 后端配置（数据库连接、端口等）
└── frontend/                       # 前端源代码
    ├── src/
    │   ├── api/                    # 接口请求（如repair.js、home.js）
    │   ├── main.js                 # 前端入口（配置Vue、Pinia、路由等）
    │   └── ...（组件、路由、状态管理等）
    └── README.md                   # 前端说明（Vue 3 + Vite配置）
```


## 运行说明
1. **数据库准备**：执行`sql/init_smart_property_management.sql`初始化数据库表结构与示例数据。
2. **后端配置**：修改`src/main/resources/application.properties`中的数据库连接信息（用户名、密码等）。
3. **启动后端**：运行`PropertyManagementGbApplication.java`启动Spring Boot服务。
4. **启动前端**：进入`frontend`目录，执行`npm install`安装依赖后，通过`npm run dev`启动Vite开发服务器。
5. **接口文档**：后端启动后，访问Swagger UI（默认路径通常为`http://localhost:8080/swagger-ui.html`）查看API详情。


该项目通过前后端分离架构实现了物业管理的核心功能，技术选型成熟，代码结构清晰，便于维护和扩展，适合作为中小型小区的数字化管理解决方案。
