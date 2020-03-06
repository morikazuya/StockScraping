package com.example.demo.charts.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.chart.model.Chart;
import com.example.demo.chart.service.ChartService;



@Controller
public class ChartController {
	
	@Autowired
	ChartService chartService;
		
	@GetMapping("/chart")
	public String getChart(Model model, Chart chart) throws IOException {	
		// コンテンツ部分に一覧表示のための文字列を登録
		model.addAttribute("contents", "chart/chart::chart_contents");
		
		String year = "2019";
		chart = chartService.selectCriteria(year);
		model.addAttribute("chart", chart);
		
		//chart(日経平均)
		String label1[] = {chart.getCriteriaDate(), chart.getComparsionDate()};
		
		double nikkei_chart = (chart.getComparsionValue() - chart.getCriteriavalue()) / (chart.getCriteriavalue() / 100);
		double point1[] = {0, nikkei_chart};
				
		model.addAttribute("label1", label1);
		model.addAttribute("point1", point1);
		
		return "login/homeLayout";
	}
	
	@PostMapping("/chart")
	public String postChart(Model model, Chart chart, @RequestParam(value = "a", defaultValue = "0")double a, @RequestParam(value = "b", defaultValue = "0")double b, @RequestParam(value = "year", defaultValue = "2019")String year) throws IOException {
		
		model.addAttribute("contents", "chart/chart::chart_contents");
		
		chart = chartService.selectCriteria(year);
		model.addAttribute("chart", chart);
		
		//chart(日経平均)
		String label1[] = {chart.getCriteriaDate(), chart.getComparsionDate()};
				
		double nikkei_chart = (chart.getComparsionValue() - chart.getCriteriavalue()) / (chart.getCriteriavalue() / 100);
		double point1[] = {0, nikkei_chart};
					
		model.addAttribute("label1", label1);
		model.addAttribute("point1", point1);
		
		//chart（ポートフォリオ）
		double portfolio_chart = (b - a) / (a / 100);
		double point2[] = {0, portfolio_chart};
		
		model.addAttribute("point2", point2);
	
		return "login/homeLayout";
	}

}
