package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// User テーブルの件数を取得
	@Override
	public int count() throws DataAccessException {
		// 全権取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT( * ) FROM m_user", Integer.class);
		
		return count;
	}
	// User テーブルにデータ1件 insert
	@Override
	public int insertOne(User user) throws DataAccessException {
		
		//パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		// 1件登録
		String sql = "INSERT INTO m_user("
				+ "user_id, "
				+ "password, "
				+ "user_name, "
				+ "birthday, "
				+ "age, "
				+ "marriage, "
				+ "role)"
				+ "VALUES(?,?,?,?,?,?,?)";
		int rowNumber = jdbc.update(sql,
				user.getUserId(),
				password,
				user.getUserName(),
				user.getBirthday(),
				user.getAge(),
				user.isMarriage(),
				user.getRole());
		return rowNumber;
	}
			
	// User テーブルのデータを1件取得
	@Override
	public User selectOne(String userId) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user "
				+ "WHERE user_id = ?"
				, userId);
		// 結果返却用の変数
		User user = new User();
		// 取得したデータを結果返却用の変数にセット
		user.setUserId((String)map.get("user_id"));
		user.setPassword((String)map.get("password"));
		user.setUserName((String)map.get("user_name"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((Integer)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));
		
		return user;
	}
			
	// User テーブルの全データを取得
	@Override
	public List<User> selectMany() throws DataAccessException {
		// M_USER テーブルのデータを全件取得
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
		// 結果返却用の変数
		List<User> userList = new ArrayList<>();
		// 取得したデータを結果返却用の List に格納
		for(Map<String, Object> map:getList) {
			// User インスタンスの生成
			User user = new User();
			// User インスタンスに取得したデータをセット
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("password"));
			user.setUserName((String)map.get("user_name"));
			user.setBirthday((Date)map.get("birthday"));
			user.setAge((Integer)map.get("age"));
			user.setMarriage((Boolean)map.get("marriage"));
			user.setRole((String)map.get("role"));
			// 結果返却用の List に追加
			userList.add(user);
		}
		return userList;
	}
			
	// User テーブルを1件更新
	@Override
	public int updateOne(User user) throws DataAccessException {
		//パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		// 1件更新
		String sql = "UPDATE m_user "
				+ "SET "
				+ "password = ?, "
				+ "user_name = ?, "
				+ "birthday = ?, "
				+ "age = ?, "
				+ "marriage = ? "
				+ "WHERE user_id =?";
		int rowNumber =jdbc.update(sql,
				password,
				user.getUserName(),
				user.getBirthday(),
				user.getAge(),
				user.isMarriage(),
				user.getUserId());
		
		// トランザクション確認のため、わざと例外をthrowする
		//if(rowNumber > 0) {
		//	throw new DataAccessException("トランザクションテスト") {};
		//}
		
		return rowNumber;
	}
			
	// User テーブルを1件削除
	@Override
	public int deleteOne(String userId) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);
		return rowNumber;
	}
			
	// SQL 取得結果をサーバーに CSV で保存
	@Override
	public void userCsvOut() throws DataAccessException {
		// M_USER テーブルのデータを全件取得する SQL
		String sql = "SELECT * FROM m_user";
		// ResultSetxtractor の生成
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		
		// SQL 実行＆ CSV 出力
		jdbc.query(sql, handler);
	}
}
