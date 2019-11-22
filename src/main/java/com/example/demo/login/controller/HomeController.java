package com.example.demo.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	// 結婚ステータスのラジオボタン用変数
	private Map<String, String> radioMarriage;
	// ラジオボタン初期化メソッド
	private Map<String, String> initRadioMarriage() {
		
		Map<String, String> radio = new LinkedHashMap<>();
		// 既婚、未婚を Map に格納
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		
		return radio;
	}
	
	// ユーザー一覧画面の GET 用メソッド
	@GetMapping("/home")
	public String getHome(Model model) {
		// コンテンツ部分にユーザー一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/home::home_contents");
		return "login/homeLayout";
	}
	
	// ユーザー一覧画面の GET 用メソッド
	@GetMapping("/userList")
	public String getUserList(Model model) {
		// コンテンツ部分にユーザー一覧表示のための文字列を登録
		model.addAttribute("contents", "login/userList::userList_contents");
		// ユーザー一覧生成
		List<User> userList = userService.selectMany();
		// Model にユーザーリストを登録
		model.addAttribute("userList", userList);
		// データ件数を取得
		int count = userService.count();
		model.addAttribute("userListCount", count);
		
		return "login/homeLayout";
	}
	
	// ユーザー詳細画面の GET 用メソッド
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
		// ユーザー ID 確認（デバック）
		System.out.println("userId = " + userId);
		// コンテンツ部分にユーザー詳細を表示用文字列登録
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
		// 結婚ステータス用ラジオボタン初期化
		radioMarriage = initRadioMarriage();
		// ラジオボタン用の Map を Model に登録
		model.addAttribute("radioMarriage", radioMarriage);
		
		// ユーザーIDチェック
		if(userId != null && userId.length() > 0) {
			// ユーザー情報を取得
			User user = userService.selectOne(userId);
			// User クラスをフォームクラスに変換
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());
			// Model に登録
			model.addAttribute("signupForm", form);
		}
		return "login/homeLayout";
	}
	
	// ユーザー更新用処理
	@PostMapping(value= "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");
		// User インスタンス生成
		User user = new User();
		// フォームクラスを User クラスに変換
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		try {
			// 更新実行
			boolean result = userService.updateOne(user);
			if(result == true) {
				model.addAttribute("result", "更新成功");
			} else {
				model.addAttribute("resulr", "更新失敗");
			}
		} catch(DataAccessException e) {
			model.addAttribute("result", "更新失敗（トランザクションテスト");
		}
		// ユーザー一覧画面を表示
		return getUserList(model);
	}
	
	// ユーザー削除用処理
	@PostMapping(value="/userDetail", params="delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除ボタンの処理");
		// 削除実行
		boolean result = userService.deleteOne(form.getUserId());
		if(result == true) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result", "削除失敗");
		}
		// ユーザー一覧画面を表示
		return getUserList(model);
	}
	
	// ログアウト用メソッド
	@PostMapping("/logout")
	public String postLogout() {
		// ログイン画面にリダイレクト
		return "redirect:/login";
	}
	
	// ユーザー一覧の CSV 出力用メソッド
	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model model) {
		// ユーザーを全件取得して、CSVをサーバーに保存
		userService.userCsvOut();
		byte[] bytes = null;
		try {
			// サーバーに保存されている sample.csv ファイルを byte で取得
			bytes = userService.getFile("sample.csv");
		} catch(IOException e) {
			e.printStackTrace();
		}
		// HTTP ヘッダーの設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv;charset=UTF-8");
		header.setContentDispositionFormData("filename", "sample.csv");
		// sample.csv を戻す
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}
	
	//アドミン権限専用画面のGET用メソッド
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		//コンテンツ部分にユーザー詳細表示用の文字列登録
		model.addAttribute("contents", "login/admin::admin_contents");
		//レイアウト用テンプレート
		return "login/homeLayout";
	}
}
