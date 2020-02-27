package com.example.demo.assets.controller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

import com.example.demo.assets.model.RegistrationForm;
import com.example.demo.assets.model.Stock;
import com.example.demo.assets.service.StockService;

@Controller
public class StockController {

	@Autowired
	StockService stockService;
	
	
	// 一覧画面の GET 用メソッド
	@GetMapping("/stockList")
	public String getStockList(Model model) {
		// コンテンツ部分に一覧表示のための文字列を登録
		model.addAttribute("contents", "assets/stockList::stockList_contents");
		// 一覧生成
		List<Stock> stockList = stockService.selectMany();
		// Model にリストを登録
		model.addAttribute("stockList", stockList);
		// データ件数を取得
		int count = stockService.count();
		model.addAttribute("stockListCount", count);
		// 損益合計を取得
		int sum = stockService.sum();
		model.addAttribute("stockPlCount", sum);
		
		return "login/homeLayout";
	}
	
	// ユーザー詳細画面の GET 用メソッド
	@GetMapping("/stockDetail/{stockBrand:.+}")
	public String getStockDetail(@ModelAttribute RegistrationForm form, Model model, @PathVariable("stockBrand") String stockBrand) {
		// ID 確認（デバック）
		System.out.println("stockBrand = " + stockBrand);
		// コンテンツ部分に詳細を表示用文字列登録
		model.addAttribute("contents", "assets/stockDetail :: stockDetail_contents");
		
		// ユーザーIDチェック
		if(stockBrand != null && stockBrand.length() > 0) {
			// ユーザー情報を取得
			Stock stock = stockService.selectOne(stockBrand);
			// User クラスをフォームクラスに変換
			form.setStockBrand(stock.getStockBrand());
			form.setStockName(stock.getStockName());
			form.setStockAmount(stock.getStockAmount());
			form.setStockNum(stock.getStockNum());
			//form.setStockPl(stock.getStockPl());
			//form.setStockHold(stock.isStockHold());
			// Model に登録
			model.addAttribute("registrationForm", form);
		}
		return "login/homeLayout";
	}
	
	// ユーザー更新用処理
	@PostMapping(value= "/stockDetail", params = "update")
	public String postStockDetailUpdate(@ModelAttribute RegistrationForm form, Model model) throws IOException {
		System.out.println("更新ボタンの処理");
		// User インスタンス生成
		Stock stock = new Stock();
		// フォームクラスを User クラスに変換
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
		try {
			// 更新実行
			boolean result = stockService.updateOne(stock);
			if(result == true) {
				model.addAttribute("result", "更新成功");
			} else {
				model.addAttribute("result", "更新失敗");
			}
		} catch(DataAccessException e) {
			model.addAttribute("result", "更新失敗（トランザクションテスト)");
		}
		// ユーザー一覧画面を表示
		return getStockList(model);
	}
	
	// ユーザー削除用処理
	@PostMapping(value="/stockDetail", params="delete")
	public String postStockDetailDelete(@ModelAttribute RegistrationForm form, Model model) {
		System.out.println("削除ボタンの処理");
		// 削除実行
		boolean result = stockService.deleteOne(form.getStockBrand());
		if(result == true) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result", "削除失敗");
		}
		// ユーザー一覧画面を表示
		return getStockList(model);
	}
	
	// ユーザー一覧の CSV 出力用メソッド
	@GetMapping("/stockList/csv")
	public ResponseEntity<byte[]> getStockListCsv(Model model) {
		// ユーザーを全件取得して、CSVをサーバーに保存
		stockService.stockCsvOut();
		byte[] bytes = null;
		try {
			// サーバーに保存されている sample.csv ファイルを byte で取得
			bytes = stockService.getFile("sample_stock.csv");
		} catch(IOException e) {
			e.printStackTrace();
		}
		// HTTP ヘッダーの設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv;charset=UTF-8");
		header.setContentDispositionFormData("filename", "sample_stock.csv");
		// sample.csv を戻す
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}
}
