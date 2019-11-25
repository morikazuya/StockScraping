package com.example.demo.controller;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.stock.sample.StockSample;

@Controller
public class TrialController {

    @RequestMapping("/input")
    public String input() {
        return "/input.html";
    }
    

    @RequestMapping("output")
	public String output(ModelMap modelMap, @RequestParam("x") String x, @RequestParam("b") int b, @RequestParam("c") int c, Model model) throws IOException {
		
			Document document = Jsoup.connect("https://kabutan.jp/stock/kabuka?code="+x+"&ashi=day&page=1").get();
			Elements elements = document.select("table.stock_kabuka0 tbody");
			
			
			String str = null;
			
			for (Element element : elements) {
				str = element.text().replaceAll(",", ""); 
			}
			
			String hairetsu[] = str.split(" ");
			StockSample stock = new StockSample();
			stock.setToday(hairetsu[0]);
			stock.setOpeningQuotation(hairetsu[1]);
			stock.setHigh(hairetsu[2]);
			stock.setLow(hairetsu[3]);
			stock.setKabuka(Integer.parseInt(hairetsu[4]));
			stock.setDayOverDayChanges(hairetsu[5]);
			stock.setDayOverDayChangesPercent(hairetsu[6]);
			stock.setTurnover(hairetsu[7]);
			
			
			int a = (int) stock.getKabuka();
			int total = (a - b) * c;
			modelMap.addAttribute("a", a);
	        modelMap.addAttribute("b", b);
	        modelMap.addAttribute("c", c);
	        modelMap.addAttribute("total", total);
		
		return "output";
    }
}