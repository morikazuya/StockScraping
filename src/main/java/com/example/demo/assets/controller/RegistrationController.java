package com.example.demo.assets.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.example.demo.assets.model.RegistrationForm;
import com.example.demo.assets.model.Stock;
import com.example.demo.assets.service.StockService;

@Controller
public class RegistrationController {
	
	@Autowired
	private StockService stockService;
	
	
	// 登録画面の GET 用コントローラー
	@GetMapping("/registration")
	public String getRegistration(@ModelAttribute RegistrationForm form, Model model) {
		
		//画面遷移
		return "assets/registration";
	}
	
	// 登録画面の POST 用コントローラー
	@PostMapping("/registration")
	public String postRegistration(@ModelAttribute RegistrationForm form, BindingResult bindingResult, Model model) throws IOException {
		
		// 入力チェックに引っかかった場合、登録画面に戻る
		if(bindingResult.hasErrors()) {
			
			// GET リクエスト用のメソッドを呼び出して、登録画面に戻ります
			return getRegistration(form, model);
		}
		
		// form の中身をコンソールに出して確認します
		System.out.println(form);
		
		
		// insert 用変数
		Stock stock = new Stock();
		
		stock.setStockBrand(form.getStockBrand());
		stock.setStockName(form.getStockName());
		stock.setStockAmount(form.getStockAmount());
		stock.setStockNum(form.getStockNum());
		
		if(form.getStockPl() != 0 ) {
			stock.setStockPl(form.getStockPl());
			
		} else {
			Document document = Jsoup.connect("https://kabutan.jp/stock/kabuka?code="+form.getStockBrand()+"&ashi=day&page=1").get();
			Elements elements = document.select("table.stock_kabuka0 tbody");
			
			String str = null;
			for (Element element : elements) {
				str = element.text().replaceAll(",", ""); 
			}
			
			String hairetsu[] = str.split(" ");
			Stock stock2 = new Stock();
			stock2.setToday(hairetsu[0]);
			stock2.setOpeningQuotation(hairetsu[1]);
			stock2.setHigh(hairetsu[2]);
			stock2.setLow(hairetsu[3]);
			stock2.setKabuka(Integer.parseInt(hairetsu[4]));
			stock2.setDayOverDayChanges(hairetsu[5]);
			stock2.setDayOverDayChangesPercent(hairetsu[6]);
			stock2.setTurnover(hairetsu[7]);
			
			stock.setStockPl((stock2.getKabuka()-stock.getStockAmount())*stock.getStockNum());
		}
		
		
		
		// ユーザー登録処理
		boolean result = stockService.insert(stock);
		
		// ユーザー登録結果の判定
		if(result == true) {
			System.out.println("insert 成功");
		} else {
			System.out.println("insert 失敗");
		}
		
		
		// リダイレクト
		return "redirect:/stockList";
	}
	
	
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー（DB)　：ExceptionHandler");
		model.addAttribute("message", "RegistrationControllerでDataAccessExceptionが発生しました");
		//HTTPエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー（DB)　：ExceptionHandler");
		model.addAttribute("message", "RegistrationControllerでDataAccessExceptionが発生しました");
		//HTTPエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
}
