package com.example.demo.assets.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.assets.model.Stock;



public interface StockDao {

		// Stock テーブルの件数を取得
		public int count() throws DataAccessException;
		
		// Stock テーブルの損益合計を取得
		public int sum() throws DataAccessException;
		
		// Stock テーブルにデータ1件 insert
		public int insertOne(Stock stock) throws DataAccessException;
		
		// Stock テーブルのデータを1件取得
		public Stock selectOne(String stockBrand) throws DataAccessException;
		
		// Stock テーブルの全データを取得
		public List<Stock> selectMany() throws DataAccessException;
		
		// Stock テーブルを1件更新
		public int updateOne(Stock stock) throws DataAccessException;
		
		// Stock テーブルを1件削除
		public int deleteOne(String stockBrand) throws DataAccessException;
		
		// SQL 取得結果をサーバーに CSV で保存する
		public void stockCsvOut() throws DataAccessException;
}
