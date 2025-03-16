
-- book_borrow.sql
CREATE TABLE IF NOT EXISTS book_borrow (
    id VARCHAR(32) PRIMARY KEY COMMENT '主键ID',
    delete_status BOOLEAN DEFAULT FALSE COMMENT '删除状态',
    create_time TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP COMMENT '更新时间',
    create_user VARCHAR(32) COMMENT '创建者',
    update_user VARCHAR(32) COMMENT '更新者',
    book_id VARCHAR(32) NOT NULL COMMENT '图书ID',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    borrow_date DATE NOT NULL COMMENT '借阅日期',
    return_date DATE COMMENT '归还日期',
    status VARCHAR(20) DEFAULT 'BORROWED' COMMENT '状态'
);

CREATE INDEX idx_borrow_book_user ON book_borrow(book_id, user_id);

-- 初始化测试数据（10条）
INSERT INTO book_borrow (id, book_id, user_id, borrow_date, status, delete_status, create_time, update_time, create_user, update_user) VALUES
('B001', '1', '1', '2023-01-01', 'BORROWED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B002', '2', '2', '2023-02-15', 'RETURNED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B003', '3', '3', '2023-03-10', 'BORROWED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B004', '4', '4', '2023-04-20', 'OVERDUE', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B005', '5', '5', '2023-05-05', 'BORROWED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B006', '6', '6', '2023-06-12', 'RETURNED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B007', '7', '7', '2023-07-18', 'BORROWED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B008', '8', '8', '2023-08-22', 'OVERDUE', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B009', '9', '9', '2023-09-30', 'BORROWED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system'),
('B010', '10', '10', '2023-10-11', 'RETURNED', FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'system', 'system');