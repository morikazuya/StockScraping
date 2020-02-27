package com.example.demo.assets.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.assets.model.Stock;
import com.example.demo.assets.repository.StockDao;

@Repository("StockDaoJdbcImpl")
public class StockDaoJdbcImpl implements StockDao {

	
	@Autowired
	JdbcTemplate jdbc;
	
	
	//テーブルの件数を取得
	@Override
	public int count() throws DataAccessException {
		// 全件取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT( * ) FROM m_stock", Integer.class);
		
		return count;
	}
	//損益額合計を取得
	@Override
	public int sum() throws DataAccessException {
		// 全件取得してカウント
		int sum = jdbc.queryForObject("SELECT COALESCE(SUM(stock_Pl), 0) FROM m_stock", Integer.class);
			
		return sum;
	}
	// テーブルにデータ1件 insert
	@Override
	public int insertOne(Stock stock) throws DataAccessException {
		
		// 1件登録
		String sql = "INSERT INTO m_stock("
				+ "stock_brand, "
				+ "stock_name, "
				+ "stock_amount, "
				+ "stock_num, "
				+ "stock_pl)"
				+ "VALUES(?,?,?,?,?)";
		int rowNumber = jdbc.update(sql,
				stock.getStockBrand(),
				stock.getStockName(),
				stock.getStockAmount(),
				stock.getStockNum(),
				stock.getStockPl());
		return rowNumber;
	}
			
	//  テーブルのデータを1件取得
	@Override
	public Stock selectOne(String stockBrand) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_stock "
				+ "WHERE stock_brand = ?"
				, stockBrand);
		// 結果返却用の変数
		Stock stock = new Stock();
		// 取得したデータを結果返却用の変数にセット
		stock.setStockBrand((String)map.get("stock_brand"));
		stock.setStockName((String)map.get("stock_name"));
		stock.setStockAmount((Integer)map.get("stock_amount"));
		stock.setStockNum((Integer)map.get("stock_num"));
		stock.setStockPl((Integer)map.get("stock_pl"));
		
		return stock;
	}
			
	//  テーブルの全データを取得
	@Override
	public List<Stock> selectMany() throws DataAccessException {
		// M_stock テーブルのデータを全件取得
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_stock");
		// 結果返却用の変数
		List<Stock> stockList = new ArrayList<>();
		// 取得したデータを結果返却用の List に格納
		for(Map<String, Object> map:getList) {
			// User インスタンスの生成
			Stock stock = new Stock();
			// User インスタンスに取得したデータをセット
			stock.setStockBrand((String)map.get("stock_brand"));
			stock.setStockName((String)map.get("stock_name"));
			stock.setStockAmount((Integer)map.get("stock_amount"));
			stock.setStockNum((Integer)map.get("stock_num"));
			stock.setStockPl((Integer)map.get("stock_pl"));
			// 結果返却用の List に追加
			stockList.add(stock);
		}
		return stockList;
	}
			
	//  テーブルを1件更新
	@Override
	public int updateOne(Stock stock) throws DataAccessException {
		// 1件更新
		String sql = "UPDATE m_stock "
				+ "SET "
				+ "stock_name = ?, "
				+ "stock_amount = ?, "
				+ "stock_num = ?, "
				+ "stock_pl = ? "
				+ "WHERE stock_brand =?";
		int rowNumber =jdbc.update(sql,
				stock.getStockName(),
				stock.getStockAmount(),
				stock.getStockNum(),
				stock.getStockPl(),
				stock.getStockBrand());
		
		return rowNumber;
	}
			
	//  テーブルを1件削除
	@Override
	public int deleteOne(String stockBrand) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE FROM m_stock WHERE stock_brand = ?", stockBrand);
		return rowNumber;
	}
			
	// SQL 取得結果をサーバーに CSV で保存
	@Override
	public void stockCsvOut() throws DataAccessException {
		// M_stock テーブルのデータを全件取得する SQL
		String sql = "SELECT * FROM m_stock";
		// ResultSetxtractor の生成
		StockRowCallbackHandler handler = new StockRowCallbackHandler();
		
		// SQL 実行＆ CSV 出力
		jdbc.query(sql, handler);
	}
}
