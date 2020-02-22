/* 従業員テーブル */
CREATE TABLE IF NOT EXISTS employee(
	employee_id INT PRIMARY KEY,
	employee_name VARCHAR(50),
	age INT
);


/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user(
	user_id VARCHAR(50) PRIMARY KEY,
	password VARCHAR(100),
	user_name VARCHAR(50),
	birthday DATE,
	age INT,
	marriage BOOLEAN,
	role VARCHAR(50)
);

/* 株マスタ */
CREATE TABLE IF NOT EXISTS m_stock(
	stock_brand VARCHAR(10) PRIMARY KEY,
	stock_name VARCHAR(50),
	stock_amount INT,
	stock_num INT,
	stock_pl INT,
	stock_hold BOOLEAN
);
