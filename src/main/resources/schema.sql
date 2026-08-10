-- ============================================
-- PersonalHub 数据库建表脚本
-- 数据库：CREATE DATABASE personalhub
-- 执行：mysql -u root -p personalhub < schema.sql
-- ============================================

CREATE DATABASE IF NOT EXISTS personalhub
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE personalhub;

-- -------------------------------------------
-- 个人信息（单条记录）
-- -------------------------------------------
CREATE TABLE IF NOT EXISTS person (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL        COMMENT '姓名',
    title       VARCHAR(100)    NOT NULL        COMMENT '头衔',
    description TEXT                            COMMENT '关于我详细描述',
    about       VARCHAR(500)                    COMMENT 'Hero 区简短介绍',
    label       VARCHAR(500)                    COMMENT '标签，逗号分隔',
    email       VARCHAR(200)                    COMMENT '邮箱',
    github      VARCHAR(500)                    COMMENT 'GitHub 地址',
    avatar      VARCHAR(500)                    COMMENT '头像图片路径',
    create_time DATETIME        DEFAULT NOW()   COMMENT '创建时间'
) ENGINE=InnoDB COMMENT='个人信息';

-- -------------------------------------------
-- 教育经历
-- -------------------------------------------
CREATE TABLE IF NOT EXISTS education (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    school      VARCHAR(200)    NOT NULL        COMMENT '学校名称',
    major       VARCHAR(200)    NOT NULL        COMMENT '专业',
    degree      VARCHAR(100)    NOT NULL        COMMENT '学历（本科/硕士/博士）',
    start_time  DATE            NOT NULL        COMMENT '入学时间',
    end_time    DATE                            COMMENT '毕业时间（NULL 表示在读）',
    description TEXT                            COMMENT '在校描述',
    INDEX idx_start_time (start_time)
) ENGINE=InnoDB COMMENT='教育经历';

-- -------------------------------------------
-- 工作/实习经历
-- -------------------------------------------
CREATE TABLE IF NOT EXISTS experience (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    company     VARCHAR(200)    NOT NULL        COMMENT '公司名称',
    department  VARCHAR(200)                    COMMENT '部门',
    position    VARCHAR(200)    NOT NULL        COMMENT '职位',
    start_time  DATE            NOT NULL        COMMENT '入职时间',
    end_time    DATE                            COMMENT '离职时间（NULL 表示至今）',
    description TEXT                            COMMENT '工作描述',
    INDEX idx_start_time (start_time)
) ENGINE=InnoDB COMMENT='工作经历';

-- -------------------------------------------
-- 技能
-- -------------------------------------------
CREATE TABLE IF NOT EXISTS skill (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL        COMMENT '技能名称',
    level       INT             DEFAULT 3       COMMENT '等级 1-5',
    category    VARCHAR(100)                    COMMENT '分类（Backend/Frontend/AI 等）',
    description VARCHAR(500)                    COMMENT '技能描述'
) ENGINE=InnoDB COMMENT='技能';

-- -------------------------------------------
-- 项目
-- -------------------------------------------
CREATE TABLE IF NOT EXISTS project (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(200)    NOT NULL        COMMENT '项目名称',
    description TEXT            NOT NULL        COMMENT '项目描述',
    tech_stack  VARCHAR(500)                    COMMENT '技术栈',
    github_url  VARCHAR(500)                    COMMENT 'GitHub 地址',
    image       VARCHAR(500)                    COMMENT '项目图片路径',
    category    VARCHAR(100)                    COMMENT '分类',
    status      VARCHAR(100)                    COMMENT '状态（Developing/Done）',
    create_time DATETIME        DEFAULT NOW()   COMMENT '创建时间',
    update_time DATETIME        DEFAULT NOW()   COMMENT '更新时间',
    INDEX idx_update_time (update_time)
) ENGINE=InnoDB COMMENT='项目';

-- -------------------------------------------
-- 博客文章
-- -------------------------------------------
CREATE TABLE IF NOT EXISTS article (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(200)    NOT NULL        COMMENT '文章标题',
    summary     VARCHAR(500)                    COMMENT '文章摘要',
    content     MEDIUMTEXT      NOT NULL        COMMENT '文章正文（Markdown）',
    category    VARCHAR(100)                    COMMENT '分类',
    cover       VARCHAR(500)                    COMMENT '封面图片路径',
    create_time DATETIME        DEFAULT NOW()   COMMENT '创建时间',
    update_time DATETIME        DEFAULT NOW()   COMMENT '更新时间',
    INDEX idx_update_time (update_time)
) ENGINE=InnoDB COMMENT='博客文章';

-- -------------------------------------------
-- 管理员用户
-- -------------------------------------------
CREATE TABLE IF NOT EXISTS admin_user (
    id          BIGINT          AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(100)    NOT NULL UNIQUE COMMENT '用户名',
    password    VARCHAR(200)    NOT NULL        COMMENT 'BCrypt 密码哈希',
    create_time DATETIME        DEFAULT NOW()   COMMENT '创建时间'
) ENGINE=InnoDB COMMENT='管理员用户';
