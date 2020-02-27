
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

/* 株価データ　*/
CREATE TABLE IF NOT EXISTS stock_prices(
    stock_code VARCHAR(10),
    company_name VARCHAR(50),
    trading_date DATE,
    opening_price DOUBLE,
    high_price DOUBLE,
    low_price DOUBLE,
    closing_price DOUBLE,
    stock_production DOUBLE,
    adjustment_closing_price DOUBLE,
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY
);
