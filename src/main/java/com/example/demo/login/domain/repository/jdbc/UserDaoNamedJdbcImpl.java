package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	
	// User テーブルの件数を取得
	@Override
	public int count() {
		// SQL文
		String sql = "SELECT COUNT(*) FROM m_user";
		// パラメーター生成
		SqlParameterSource params = new MapSqlParameterSource();
		// 全件取得してカウント
		return jdbc.queryForObject(sql, params, Integer.class);
	}
	
	
	// User テーブルにデータを1件 insert
	@Override
	public int insertOne(User user) {
		// SQL文
		String sql = "INSERT INTO m_user(user_id, "
				+ "password, "
				+ "user_name, "
				+ "birthday, "
				+ "age, "
				+ "marriage, "
				+ "role)"
				+ "VALUES(:userId, "
				+ ":password, "
				+ ":userName, "
				+ ":birthday, "
				+ ":age, "
				+ ":marriage, "
				+ ":role)";
		// パラメーター
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage())
				.addValue("role", user.getRole());
		//SQL実行
		return jdbc.update(sql, params);
	}

	
	// Userテーブルのデータを1件取得
	public User selectOne(String userId) {
		//SQL文
		String sql = "SELECT * FROM m_user WHERE user_id = :user_Id";
		// パラメーター
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);
		// SQL実行
		Map<String, Object> map = jdbc.queryForMap(sql, params);
		// 結果返却用のインスタンス生成
		User user = new User();
		// 取得データをインスタンスにセット
		user.setUserId((String)map.get("user_id"));
		user.setPassword((String)map.get("pasword"));
		user.setUserName((String)map.get("user_name"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((Integer)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));
		
		return user;
	}
	
	
	// User テーブルの全データ取得
	public List<User> selectMany() {
		//SQL文
		String sql = "SELECT * FROM m_user";
		//パラメーター
		SqlParameterSource params = new MapSqlParameterSource();
		// SQL実行
		List<Map<String, Object>> getList = jdbc.queryForList(sql, params);
		// 結果返却用の List
		List<User> userList = new ArrayList<>();
		// 取得データ分 loop
		for(Map<String, Object> map:getList) {
			// User インスタンス生成
			User user = new User();
			// User インスタンスに取得したデータをセット
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("pasword"));
			user.setUserName((String)map.get("user_name"));
			user.setBirthday((Date)map.get("birthday"));
			user.setAge((Integer)map.get("age"));
			user.setMarriage((Boolean)map.get("marriage"));
			user.setRole((String)map.get("role"));
			
			// Listに追加
			userList.add(user);
		}
		return userList;
	}
	
	
	// User テーブルを1件更新
	@Override
	public int updateOne(User user) {
		// SQL文
		String sql = "UPDATE m_user "
				+ "SET "
				+ "password = :password, "
				+ "user_name = :user_name, "
				+ "birthday = :birthday, "
				+ "age = :age, "
				+ "marriage = :marriage, "
				+ "WHERE user_id = :user_id";
		// パラメーター
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage());
		// SQL実行
		return jdbc.update(sql, params);
	}
	
	
	// User テーブルを1件削除
	@Override
	public int deleteOne(String userId) {
		// SQL文
		String sql = "DELETE FROM m_user WHERE user_id = :userId";
		// パラメーター
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);
		// SQL実行
		int rowNumber = jdbc.update(sql, params);
		
		return rowNumber;
	}
	
	
	// SQL取得結果をサーバーにCSVで保存
	@Override
	public void userCsvOut() {
		// m_userテーブルのデータを全件取得
		String sql = "SELECT * FROM m_user";
		// ResultSetExtractor生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		// クエリー実行＆CSV出力
		jdbc.query(sql, handler);
		
	}
}
