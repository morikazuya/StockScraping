package com.example.demo.assets.service;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.assets.model.Stock;
import com.example.demo.assets.repository.StockDao;

@Transactional
@Service
public class StockService {

	@Autowired
	@Qualifier("StockDaoJdbcImpl")
	StockDao dao;
	
	// insert 用メソッド
	public boolean insert(Stock stock) {
		// insert 実行
		int rowNumber = dao.insertOne(stock);
		// 判別用変数
		boolean result = false;
		if(rowNumber > 0) {
			// insert 成功
			result = true;
		}
		return result;
	}
	
	
	// count 用メソッド
	public int count() {
		return dao.count();
	}
	
	// sum 用メソッド
		public int sum() {
			return dao.sum();
		}
	
	// 全件取得用メソッド
	public List<Stock> selectMany() {
		// 全件取得
		return dao.selectMany();
	}
	
	
	// 1件取得用メソッド
	public Stock selectOne(String stockBrand) {
		// 1件取得
		return dao.selectOne(stockBrand);
	}
	
	
	// 1件更新用メソッド
	public boolean updateOne(Stock stock) {
		// 1件更新
		int rowNumber = dao.updateOne(stock);
		// 判定用変数
		boolean result =false;
		if(rowNumber > 0) {
			// update 成功
			result = true;
		}
		return result;
	}
	
	// 1件削除メソッド
	public boolean deleteOne(String stockBrand) {
		// 1件削除
		int rowNumber = dao.deleteOne(stockBrand);
		// 判別用変数
		boolean result = false;
		if(rowNumber > 0) {
			// delete 成功
			result = true;
		}
		return result;
	}
	
	// ユーザー一覧をCSV出力する
	public void stockCsvOut() throws DataAccessException {
		// CSV出力
		dao.stockCsvOut();
	}
	// サーバーに保存されているファイルを取得して、byte 配列に変換
	public byte[] getFile(String fileName) throws IOException {
		// システムファイル(デフォルト）の取得
		FileSystem fs = FileSystems.getDefault();
		// ファイル取得
		Path p = fs.getPath(fileName);
		// ファイルを byte 配列に変換
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
}
