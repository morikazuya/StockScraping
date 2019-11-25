package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.stock.sample.StockSample;

@Controller
public class IndexController {
		
	@RequestMapping("/index")
	public String index(Model model) throws IOException {
		
			Document document = Jsoup.connect("https://kabutan.jp/stock/kabuka?code=0000&ashi=day&page=1").get();
			Elements elements = document.select("table.stock_kabuka0 tbody");
			
			List<StockSample> list = new ArrayList<StockSample>();
			
			String str = null;
			
			for (Element element : elements) {
				str = element.text(); 
			}
			
			String hairetsu[] = str.split(" ");
			StockSample stock = new StockSample();
			stock.setToday(hairetsu[0]);
			stock.setOpeningQuotation(hairetsu[1]);
			stock.setHigh(hairetsu[2]);
			stock.setLow(hairetsu[3]);
			stock.setClosingQuotation(hairetsu[4]);
			stock.setDayOverDayChanges(hairetsu[5]);
			stock.setDayOverDayChangesPercent(hairetsu[6]);
			stock.setTurnover(hairetsu[7]);
			
			list.add(stock);
			
		model.addAttribute("today", stock.getToday());
		model.addAttribute("openingQuotation", stock.getOpeningQuotation());
		model.addAttribute("high", stock.getHigh());
		model.addAttribute("low", stock.getLow());
		model.addAttribute("closingQuotation", stock.getClosingQuotation());
		model.addAttribute("dayOverDayChanges", stock.getDayOverDayChanges());
		model.addAttribute("dayOverDayChangesPercent", stock.getDayOverDayChangesPercent());
		model.addAttribute("turnover", stock.getTurnover());

		return "/index.html";
	}
}
