package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

		// User テーブルの件数を取得
		public int count() throws DataAccessException;
		
		// User テーブルにデータ1件 insert
		public int insertOne(User user) throws DataAccessException;
		
		// User テーブルのデータを1件取得
		public User selectOne(String userId) throws DataAccessException;
		
		// User テーブルの全データを取得
		public List<User> selectMany() throws DataAccessException;
		
		// User テーブルを1件更新
		public int updateOne(User user) throws DataAccessException;
		
		// User テーブルを1件削除
		public int deleteOne(String userId) throws DataAccessException;
		
		// SQL 取得結果をサーバーに CSV で保存する
		public void userCsvOut() throws DataAccessException;
}
