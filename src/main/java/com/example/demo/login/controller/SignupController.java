package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;

	// ラジオボタンの実装
	private Map<String, String> radioMarriage;
	
	// ラジオボタン初期化メソッド
	private Map<String, String> initRadioMarriage() {
		
		Map<String, String> radio = new LinkedHashMap<>();
		
		//既婚、未婚をMAPに格納
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		
		return radio;
	}
	
	// ユーザー登録画面の GET 用コントローラー
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
		
		// ラジオボタン初期化メソッドの呼び出し
		radioMarriage = initRadioMarriage();
		
		// ラジオボタン用の Map を Model に登録
		model.addAttribute("radioMarriage", radioMarriage);
		
		// signup.html に画面遷移
		return "login/signup";
	}
	
	// ユーザー登録画面の POST 用コントローラー
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {
		
		// 入力チェックに引っかかった場合、ユーザー登録画面に戻る
		if(bindingResult.hasErrors()) {
			
			// GET リクエスト用のメソッドを呼び出して、ユーザー登録画面に戻ります
			return getSignUp(form, model);
		}
		
		// form の中身をコンソールに出して確認します
		System.out.println(form);
		
		
		// insert 用変数
		User user = new User();
		
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		user.setRole("ROLE_GENERAL");
		
		// ユーザー登録処理
		boolean result = userService.insert(user);
		
		// ユーザー登録結果の判定
		if(result == true) {
			System.out.println("insert 成功");
		} else {
			System.out.println("insert 失敗");
		}
		
		
		// login.html にリダイレクト
		return "redirect:/login";
	}
	
	
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー（DB)　：ExceptionHandler");
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");
		//HTTPエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー（DB)　：ExceptionHandler");
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");
		//HTTPエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
}
