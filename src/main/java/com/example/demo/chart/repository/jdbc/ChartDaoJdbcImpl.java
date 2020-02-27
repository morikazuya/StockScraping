package com.example.demo.chart.repository.jdbc;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.chart.model.Chart;
import com.example.demo.chart.repository.ChartDao;




public class ChartDaoJdbcImpl implements ChartDao{
	
	@Autowired
	JdbcTemplate jdbc;
	
	
	
	//引数をもとにテーブルの日付（MIN,MAX）取得
	@Override
	public Chart selectDate(String year) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT MIN(trading_date),MAX(trading_date) FROM stock_prices WHERE (DATE_FORMAT(trading_date, '%Y') = 'year = ?');", year);
		
		Chart chart = new Chart();
		chart.setCriteriaDate((String)map.get("MIN(trading_date)"));
		chart.setComparsionDate((String)map.get("MAX(trading_date)"));
		
		return chart;
	}
		
	
	//日経平均株価（比較基準用）
	//引数をもとに終値検索
		
		
}
