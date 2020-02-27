package com.example.demo.charts.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ChartController {
		
	@RequestMapping("/chart")
	public String chartHome(Model model) throws IOException {	
		// コンテンツ部分に一覧表示のための文字列を登録
		model.addAttribute("contents", "chart/chartHome::chartHome_contents");

		return "login/homeLayout";
	}
}
