package com.example.demo.login.domain.service;

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

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {

	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;
	
	// insert 用メソッド
	public boolean insert(User user) {
		// insert 実行
		int rowNumber = dao.insertOne(user);
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
	
	
	// 全件取得用メソッド
	public List<User> selectMany() {
		// 全件取得
		return dao.selectMany();
	}
	
	
	// 1件取得用メソッド
	public User selectOne(String userId) {
		// 1件取得
		return dao.selectOne(userId);
	}
	
	
	// 1件更新用メソッド
	public boolean updateOne(User user) {
		// 1件更新
		int rowNumber = dao.updateOne(user);
		// 判定用変数
		boolean result =false;
		if(rowNumber > 0) {
			// update 成功
			result = true;
		}
		return result;
	}
	
	// 1件削除メソッド
	public boolean deleteOne(String userId) {
		// 1件削除
		int rowNumber = dao.deleteOne(userId);
		// 判別用変数
		boolean result = false;
		if(rowNumber > 0) {
			// delete 成功
			result = true;
		}
		return result;
	}
	
	// ユーザー一覧をCSV出力する
	public void userCsvOut() throws DataAccessException {
		// CSV出力
		dao.userCsvOut();
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
