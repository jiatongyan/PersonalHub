# PersonalHub 产品文档

## 一、产品定位

**PersonalHub** 是一个「个人数字中枢」，定位为集数字简历、博客写作、项目展示于一体的个人品牌平台。既是面向外界的展示窗口，也是个人知识资产的长期沉淀空间。
**项目链接** https://www.yanjiatong.com/

### 核心目标

| 维度 | 描述 |
|------|------|
| 数字简历 | 教育经历、工作经历、技能的结构化展示 |
| 博客系统 | Markdown 写作，技术 + 人文思考沉淀 |
| 项目作品集 | 展示个人项目，链接 GitHub |
| 知识资产库 | 长期记录技术成长与思考 |

### 对标产品

个人主页 + 博客系统 + 作品展示（可类比 GitHub Pages + 个人博客的结合体）

---

## 二、目标用户

### 访客（外部）

- 招聘方 / HR —— 快速了解你是谁、做过什么、能力如何
- 技术同行 —— 通过博客和项目了解你的技术深度
- 合作者 —— 评估专业背景与项目经验

### 本人（内部）

- 记录技术成长、项目经历、学习笔记
- 沉淀阅读思考、电影分析、随笔文章
- 作为长期维护的个人知识资产库

---

## 三、信息架构

```
首页 Home（单页滚动）
├── Hero 区          —— 头像、姓名、头衔、一句话介绍
├── 关于我           —— 详细描述 + 个人标签
├── 经历             —— 教育经历 + 工作经历 + 技能
├── 项目             —— 项目作品卡片
├── 博客             —— 文章卡片
└── 联系方式         —— GitHub、邮箱

后台 Admin（/admin）
├── 登录 / 注销
├── Dashboard        —— 管理导航
├── 个人信息管理      —— 编辑首页展示内容
├── 教育经历管理      —— 列表 + 新增/编辑/删除
├── 工作经历管理      —— 列表 + 新增/编辑/删除
├── 技能管理          —— 列表 + 新增/编辑/删除
├── 项目管理          —— 列表 + 新增/编辑/删除
└── 博客管理          —— 列表 + 新增/编辑/删除（Markdown）
```

---

## 四、页面设计

### 4.1 首页（index.html）

单页滚动设计，6 个区段：

| 区段 | 数据来源 | 展示内容 |
|------|---------|---------|
| Hero | `person` | 头像、姓名、头衔、一句话介绍，两个按钮（查看简历 / 阅读博客） |
| 关于我 | `person` | 详细描述文字 + 标签（逗号分隔渲染） |
| 经历 | `education` + `experience` + `skill` | 时间线展示教育/工作经历，卡片展示技能星级 |
| 项目 | `project` | 卡片列表，含图片、名称、描述、技术栈、状态，点击跳转 GitHub |
| 博客 | `article` | 卡片列表，含封面、标题、摘要、分类，点击进入详情页 |
| 联系方式 | `person` | GitHub 链接、邮箱 |

### 4.2 博客详情页（article.html）

通过 `/article/{id}` 访问，从 Markdown 渲染为 HTML 展示正文。

### 4.3 后台管理（admin/）

| 页面 | 功能 |
|------|------|
| `admin/login` | 登录页，已登录自动跳转 Dashboard |
| `admin/dashboard` | 侧边栏导航 + 卡片入口 |
| `admin/person` | 个人信息编辑（单条记录，直接编辑） |
| `admin/education` | 教育经历列表 → 新增/编辑/删除 |
| `admin/experience` | 工作经历列表 → 新增/编辑/删除 |
| `admin/skill` | 技能列表 → 新增/编辑/删除 |
| `admin/project` | 项目列表 → 新增/编辑/删除 |
| `admin/article` | 博客列表 → 新增/编辑/删除，Markdown 编辑器（EasyMDE） |

---

## 五、数据库设计

### 数据表一览

| 表名 | 实体 | 说明 |
|------|------|------|
| `person` | Person | 个人信息（单条记录） |
| `education` | Education | 教育经历 |
| `experience` | Experience | 工作/实习经历 |
| `skill` | Skill | 技能 |
| `project` | Project | 项目作品 |
| `article` | Article | 博客文章 |
| `admin_user` | AdminUser | 管理员账号 |

### 表结构

#### person

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | 主键 |
| name | VARCHAR(100) | 姓名 |
| title | VARCHAR(100) | 头衔 |
| description | TEXT | 关于我详细描述 |
| about | VARCHAR(500) | Hero 区简短介绍 |
| label | VARCHAR(500) | 标签（逗号分隔） |
| email | VARCHAR(200) | 邮箱 |
| github | VARCHAR(500) | GitHub 地址 |
| avatar | VARCHAR(500) | 头像图片路径 |
| create_time | DATETIME | 创建时间 |

#### education

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | 主键 |
| school | VARCHAR(200) | 学校名称 |
| major | VARCHAR(200) | 专业 |
| degree | VARCHAR(100) | 学历 |
| start_time | DATE | 入学时间 |
| end_time | DATE | 毕业时间（NULL = 在读） |
| description | TEXT | 在校描述 |

#### experience

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | 主键 |
| company | VARCHAR(200) | 公司名称 |
| department | VARCHAR(200) | 部门 |
| position | VARCHAR(200) | 职位 |
| start_time | DATE | 入职时间 |
| end_time | DATE | 离职时间（NULL = 至今） |
| description | TEXT | 工作描述 |

#### skill

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | 主键 |
| name | VARCHAR(100) | 技能名称 |
| level | INT | 等级（1-5，首页 ⭐ 展示） |
| category | VARCHAR(100) | 分类 |
| description | VARCHAR(500) | 技能描述 |

#### project

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | 主键 |
| name | VARCHAR(200) | 项目名称 |
| description | TEXT | 项目描述 |
| tech_stack | VARCHAR(500) | 技术栈 |
| github_url | VARCHAR(500) | GitHub 地址 |
| image | VARCHAR(500) | 项目图片路径 |
| category | VARCHAR(100) | 分类 |
| status | VARCHAR(100) | 状态（Developing / Done） |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

#### article

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | 主键 |
| title | VARCHAR(200) | 文章标题 |
| summary | VARCHAR(500) | 文章摘要 |
| content | MEDIUMTEXT | 文章正文（Markdown） |
| category | VARCHAR(100) | 分类 |
| cover | VARCHAR(500) | 封面图片路径 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

#### admin_user

| 字段 | 类型 | 说明 |
|------|------|------|
| id | BIGINT PK | 主键 |
| username | VARCHAR(100) UNIQUE | 用户名 |
| password | VARCHAR(200) | BCrypt 密码哈希 |
| create_time | DATETIME | 创建时间 |

---

## 六、技术架构

```
浏览器
  │
  ▼
Thymeleaf 模板（前台 + 后台）
  │
  ▼
Controller（MVC）
  │
  ▼
Service（业务逻辑）
  │
  ▼
Mapper（MyBatis XML / 注解）
  │
  ▼
MySQL 8
```

### 技术栈

| 层级 | 技术 | 版本 |
|------|------|------|
| 语言 | Java | 17 |
| 框架 | Spring Boot | 3.5.3 |
| ORM | MyBatis | 3.0.4 |
| 数据库 | MySQL | 8 |
| 模板引擎 | Thymeleaf | 3.1 |
| Markdown | commonmark-java | 0.24.0 |
| 密码加密 | spring-security-crypto (BCrypt) | 6.x |
| 简化 | Lombok | 1.18 |

### 包结构

```
com.dylan.personalhub
├── controller/          # 前台控制器
│   ├── HomeController
│   └── ArticleController
├── controller/admin/    # 后台控制器
│   ├── AdminController         # 登录/注销/Dashboard
│   ├── AdminPersonController   # 个人信息
│   ├── AdminArticleController  # 文章
│   ├── AdminProjectController  # 项目
│   ├── AdminExperienceController # 工作经历
│   ├── AdminSkillController    # 技能
│   ├── AdminEducationController # 教育经历
│   └── FileUploadController    # 图片上传
├── service/             # 业务层
├── mapper/              # MyBatis 数据访问
├── entity/              # 实体类
└── config/              # 配置
    ├── WebConfig              # 静态资源 + 拦截器注册
    ├── LoginInterceptor       # 登录 + CSRF 拦截
    └── GlobalExceptionHandler # 全局异常处理
```

---

## 七、安全体系

### 7.1 认证

- 基于 Session 的手动认证（非 Spring Security）
- `LoginInterceptor` 拦截 `/admin/**` 所有请求，未登录重定向到登录页
- 登录成功时销毁旧 Session、创建新 Session（防会话固定攻击）
- BCrypt 哈希存储和验证密码

### 7.2 CSRF 防护

- 登录成功后生成随机 CSRF Token 存入 Session
- 所有后台 POST 表单携带 `_csrf` 隐藏域
- 拦截器校验 POST/PUT/DELETE 请求的 CSRF Token
- 文件上传接口（JS fetch 调用）豁免 CSRF 校验但保留登录检查

### 7.3 Session 安全

- Cookie 配置 `http-only=true`（防 XSS 窃取）
- Cookie 配置 `same-site=lax`（防跨站请求）
- Session 超时 10 分钟（可在 `application.properties` 中调整）

### 7.4 输入校验

- 实体类使用 Jakarta Validation 注解（`@NotBlank`、`@Size`、`@Email`、`@Min`、`@Max`）
- Controller 层 `@Valid` 触发校验，失败返回编辑页并显示错误提示
- 文件名过滤路径遍历字符（`\`、`/` 替换为 `_`）

### 7.5 配置安全

- 数据库用户名/密码使用环境变量占位符（`${DB_USERNAME}`、`${DB_PASSWORD}`）
- 上传路径通过 `app.upload.path` 配置，不依赖 `user.dir`

---

## 八、部署配置

### 8.1 环境变量

| 变量 | 说明 | 示例 |
|------|------|------|
| `DB_USERNAME` | 数据库用户名 | `personalhub_user` |
| `DB_PASSWORD` | 数据库密码 | `your_password` |
| `APP_UPLOAD_PATH` | 上传文件存储路径 | `/data/personalhub/uploads/images` |

### 8.2 application.properties 关键配置

```properties
# 数据库
spring.datasource.url=jdbc:mysql://localhost:3306/personalhub?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:1234}

# 上传路径（必填，绝对路径）
app.upload.path=D:/javaDevelop/project/personalhub/uploads/images

# Session 安全
server.servlet.session.timeout=30m
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.same-site=lax

# 文件大小
spring.servlet.multipart.max-file-size=10MB
```

### 8.3 数据库初始化

```bash
mysql -u root -p < src/main/resources/schema.sql
```

### 8.4 构建部署

```bash
# 打包
./mvnw clean package -DskipTests

# 启动
java -jar target/personalhub-0.0.1-SNAPSHOT.jar
```

### 8.5 初始密码

数据库 `admin_user` 表中密码字段需存入 BCrypt 哈希值。可通过 `AdminService.encodePassword("明文密码")` 生成。

---

## 九、开发路线与完成状态

| 阶段 | 内容 | 状态 |
|------|------|------|
| Phase 1 | 基础主页（Spring Boot + MySQL + Thymeleaf） | ✅ 完成 |
| Phase 2 | 教育经历 + 工作经历 + 技能 | ✅ 完成 |
| Phase 3 | 项目展示 | ✅ 完成 |
| Phase 4 | 博客系统（Markdown） | ✅ 完成 |
| Phase 5 | 后台管理（CRUD + 登录 + 图片上传） | ✅ 完成 |
| Phase 6 | 安全加固（BCrypt + CSRF + Session + 校验） | ✅ 完成 |
| Phase 7 | 部署就绪（环境变量 + 建表脚本 + 异常处理） | ✅ 完成 |

### 未来方向

- [ ] 知识地图（可视化展示学习内容）
- [ ] 文章分类筛选与搜索
- [ ] 分页
- [ ] 移动端优化（汉堡菜单）
- [ ] 单元测试
- [ ] CI/CD 自动部署
- [ ] Vue3 前后端分离（可选）
- [ ] RSS 订阅

---

## 十、文件结构

```
personalhub
├── docs/
│   └── PRODUCT.md              # 本文档
├── src/main/java/com/dylan/personalhub/
│   ├── PersonalhubApplication.java
│   ├── controller/
│   │   ├── HomeController.java
│   │   ├── ArticleController.java
│   │   └── admin/
│   │       ├── AdminController.java
│   │       ├── AdminArticleController.java
│   │       ├── AdminEducationController.java
│   │       ├── AdminExperienceController.java
│   │       ├── AdminPersonController.java
│   │       ├── AdminProjectController.java
│   │       ├── AdminSkillController.java
│   │       └── FileUploadController.java
│   ├── service/
│   │   ├── AdminService.java
│   │   ├── ArticleService.java
│   │   ├── EducationService.java
│   │   ├── ExperienceService.java
│   │   ├── MarkdownService.java
│   │   ├── PersonService.java
│   │   ├── ProjectService.java
│   │   └── SkillService.java
│   ├── mapper/
│   │   ├── AdminUserMapper.java
│   │   ├── ArticleMapper.java
│   │   ├── EducationMapper.java
│   │   ├── ExperienceMapper.java
│   │   ├── PersonMapper.java
│   │   ├── ProjectMapper.java
│   │   └── SkillMapper.java
│   ├── entity/
│   │   ├── AdminUser.java
│   │   ├── Article.java
│   │   ├── Education.java
│   │   ├── Experience.java
│   │   ├── Person.java
│   │   ├── Project.java
│   │   └── Skill.java
│   └── config/
│       ├── GlobalExceptionHandler.java
│       ├── LoginInterceptor.java
│       └── WebConfig.java
├── src/main/resources/
│   ├── application.properties
│   ├── schema.sql
│   ├── mapper/
│   │   └── *.xml
│   ├── templates/
│   │   ├── index.html
│   │   ├── article.html
│   │   ├── error/
│   │   │   ├── 500.html
│   │   │   └── admin-500.html
│   │   └── admin/
│   │       ├── login.html
│   │       ├── dashboard.html
│   │       ├── person/edit.html
│   │       ├── article/list.html, edit.html
│   │       ├── project/list.html, edit.html
│   │       ├── experience/list.html, edit.html
│   │       ├── skill/list.html, edit.html
│   │       └── education/list.html, edit.html
│   └── static/
│       ├── css/style.css
│       └── css/admin.css
└── pom.xml
```
