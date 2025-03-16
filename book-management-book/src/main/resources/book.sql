-- book.sql
CREATE TABLE IF NOT EXISTS book (
    id VARCHAR(32) PRIMARY KEY COMMENT '主键ID',
    delete_status BOOLEAN DEFAULT FALSE COMMENT '删除状态',
    create_time TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP COMMENT '更新时间',
    create_user VARCHAR(32) COMMENT '创建者',
    update_user VARCHAR(32) COMMENT '更新者',
    name VARCHAR(100) NOT NULL COMMENT '书名',
    author VARCHAR(50) NOT NULL COMMENT '作者',
    publisher VARCHAR(50) COMMENT '出版社',
    type VARCHAR(20) COMMENT '图书类型',
    language VARCHAR(20) COMMENT '语言',
    total_copies INT DEFAULT 0 COMMENT '总副本数',
    available_copies INT DEFAULT 0 COMMENT '可用副本数'
);

CREATE INDEX idx_book_name ON book(name);

-- 初始化测试数据（10条）
INSERT INTO book (id, name, author, total_copies, available_copies, delete_status, create_time, update_time, create_user, update_user) VALUES
('1', 'Java编程思想', 'Bruce Eckel', 10, 10, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('2', 'Effective Java', 'Joshua Bloch', 8, 8, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('3', 'Clean Code', 'Robert C. Martin', 15, 15, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('4', '设计模式', 'Erich Gamma', 5, 5, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('5', '算法导论', 'Thomas H. Cormen', 12, 12, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('6', 'Spring实战', 'Craig Walls', 7, 7, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('7', 'Python编程', 'Mark Lutz', 9, 9, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('8', '数据库系统概念', 'Abraham Silberschatz', 6, 6, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('9', '计算机网络', 'Andrew S. Tanenbaum', 11, 11, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('10', '操作系统', 'William Stallings', 4, 4, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system');
