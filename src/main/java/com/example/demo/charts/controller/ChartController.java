package com.example.demo.charts.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		//chart基準値
		String label1[] = {chart.getCriteriaDate(), chart.getComparsionDate()};
		
		double a = (chart.getComparsionValue() - chart.getCriteriavalue()) / (chart.getCriteriavalue() / 100);
		double point1[] = {0, a};
		double point2[] = {0, -20.93};
				
		model.addAttribute("label1", label1);
		model.addAttribute("point1", point1);
		model.addAttribute("point2", point2);
		
		return "login/homeLayout";
	}
	
	
	
	
	
}
