# 🎓 学生学籍信息管理系统

> 基于 MVC 分层架构的 Java Web 项目，综合运用 JDBC、反射、自定义注解、Socket 网络编程等核心技术，支持四种数据持久化方式自由切换。

[![Java](https://img.shields.io/badge/Java-1.8-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![Tomcat](https://img.shields.io/badge/Tomcat-8.5+-red.svg)](https://tomcat.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-5.7+-green.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

🌐 **[在线展示页面](https://w020316.github.io/Student-Enrollment-Information-Management-System/)** | 🎮 **[交互演示](demo.html)**

---

## 📋 项目概述

本系统是 Java高级与算法编程课程的综合实验项目，实现学生学籍信息的增、删、改、查、分页、登录权限控制功能，并支持以下四种数据持久化方式自由切换：

| 模式 | 说明 | 配置值 |
|------|------|--------|
| 💾 内存存储 | 静态 List 集合，适合快速测试 | `memory` |
| 🗄 数据库存储 | JDBC 硬编码 SQL，操作 MySQL | `db` |
| 🔍 反射动态SQL | 通过反射+注解动态生成 SQL | `dbReflect` |
| 🌐 远程网络调用 | Socket + JSON 跨进程通信 | `remote` |

## 🏗 技术架构

```
┌─────────────────────────────────────────────────────────┐
│                    View 视图层 (JSP)                      │
│   loginForm.jsp · StudentList.jsp · GenericList.jsp      │
├─────────────────────────────────────────────────────────┤
│                 Controller 控制层 (Servlet)               │
│   LoginServlet · StudentListServlet · GenericListServlet  │
├─────────────────────────────────────────────────────────┤
│                   Service 业务层                          │
│        StudentServiceImpl · TeacherServiceImpl            │
├─────────────────────────────────────────────────────────┤
│                    Factory 工厂层                         │
│      DaoFactory (单例+工厂) · ServiceFactory (单例)        │
├──────────┬──────────┬──────────────┬─────────────────────┤
│MemoryDao │  DBDao   │DBReflectDao  │     RemoteDao        │
│ 内存存储  │数据库存储 │ 反射动态SQL   │  Socket+JSON远程调用  │
└──────────┴──────────┴──────────────┴─────────────────────┘
```

## 🔧 核心技术

- **MVC 架构** — JSP + Servlet + Service + DAO 四层分离
- **JDBC 数据库编程** — PreparedStatement 防注入，分页查询
- **Java 反射机制** — DBReflectUtil 动态生成增删改查 SQL
- **自定义注解** — @Entity / @ID / @Column 注解驱动
- **Socket 网络编程** — ServerSocket + 多线程 + JSON 序列化
- **设计模式** — 单例模式（ServiceFactory）+ 工厂模式（DaoFactory）
- **Filter 过滤器** — LoginFilter 登录拦截与权限控制

## 📁 项目结构

```
StudentManage/
├── pom.xml                                    # Maven 配置
├── sql/init.sql                               # 数据库初始化脚本
├── docs/index.html                            # GitHub Pages 展示页
├── demo.html                                  # 交互演示页面
├── src/main/java/com/stu/
│   ├── annotation/                            # 自定义注解
│   │   ├── Entity.java                        #   @Entity 标注实体类
│   │   ├── ID.java                            #   @ID 标注主键
│   │   └── Column.java                        #   @Column 标注字段
│   ├── entity/                                # 实体类
│   │   ├── Student.java                       #   学生实体
│   │   ├── Teacher.java                       #   教师实体
│   │   ├── EntityInfo.java                    #   实体注解信息
│   │   └── ColumnInfo.java                    #   字段注解信息
│   ├── dao/                                   # 数据访问层
│   │   ├── StudentDao.java / TeacherDao.java  #   DAO 接口
│   │   ├── *MemoryDao.java                    #   内存版实现
│   │   ├── *DBDao.java                        #   数据库版实现
│   │   ├── *DBReflectDao.java                 #   反射版实现
│   │   └── *RemoteDao.java                    #   远程版实现
│   ├── service/                               # 业务逻辑层
│   ├── factory/                               # 工厂类（单例模式）
│   ├── servlet/                               # 控制器层
│   ├── filter/                                # 过滤器
│   ├── util/                                  # 工具类
│   └── server/                                # 远程服务端
└── src/main/webapp/                           # 视图层
    ├── css/style.css                          # 样式
    ├── loginForm.jsp                          # 登录页
    ├── student/                               # 学生模块 JSP
    ├── teacher/                               # 教师模块 JSP
    └── generic/GenericList.jsp                # 通用注解驱动列表
```

## 🚀 快速开始

### 环境要求

- JDK 1.8
- Maven 3.6+
- Tomcat 8.5+
- MySQL 5.7+（使用 db/dbReflect/remote 模式时需要）

### 安装步骤

**1. 克隆项目**

```bash
git clone https://github.com/w020316/Student-Enrollment-Information-Management-System.git
cd Student-Enrollment-Information-Management-System
```

**2. 初始化数据库**（使用数据库模式时）

```bash
mysql -u root -p < sql/init.sql
```

**3. 配置 DAO 模式**

修改 `src/main/webapp/WEB-INF/web.xml` 中的 `daoType` 参数：

```xml
<context-param>
    <param-name>daoType</param-name>
    <param-value>memory</param-value>  <!-- memory / db / dbReflect / remote -->
</context-param>
```

**4. 编译打包**

```bash
mvn clean package -DskipTests
```

**5. 部署运行**

将 `target/StudentManage.war` 放入 Tomcat 的 `webapps` 目录，启动 Tomcat 后访问：

```
http://localhost:8080/StudentManage
```

**登录账号：** 用户名 `xcy`，密码 `ddd`

### 远程模式额外步骤

使用 remote 模式前，需先启动服务端：

```bash
# 运行服务端主类
java com.stu.server.Server
# 服务端将在 8888 端口监听
```

然后将 `daoType` 改为 `remote` 并重启 Tomcat。

## 📊 数据库设计

### student 表（学生表）

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | INT | PK, AUTO_INCREMENT | 编号 |
| name | VARCHAR(10) | NOT NULL | 姓名 |
| age | INT | | 年龄 |

### teacher 表（教师表）

| 字段 | 类型 | 约束 | 说明 |
|------|------|------|------|
| id | INT | PK, AUTO_INCREMENT | 编号 |
| name | VARCHAR(10) | NOT NULL | 姓名 |
| age | INT | | 年龄 |
| salary | DOUBLE | | 薪资 |

## 📜 License

MIT License
