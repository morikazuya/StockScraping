package com.example.demo.chart.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.chart.model.Chart;

public interface ChartDao {
	
	// テーブルの日付（MIN,MAX）取得
	public Chart selectCriteria(String year) throws DataAccessException;
}
