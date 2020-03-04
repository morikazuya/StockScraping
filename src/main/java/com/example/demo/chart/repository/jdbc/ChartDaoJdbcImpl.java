package com.example.demo.chart.repository.jdbc;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.chart.model.Chart;
import com.example.demo.chart.repository.ChartDao;



@Repository("ChartDaoJdbcImpl")
public class ChartDaoJdbcImpl implements ChartDao{
	
	@Autowired
	JdbcTemplate jdbc;
	

	//日付と終値取得（日経平均株価用）
	@Override
	public Chart selectCriteria(String year) throws DataAccessException {
		//日付
		Map<String, Object> map = jdbc.queryForMap("SELECT MIN(trading_date),MAX(trading_date) FROM stock_prices WHERE (DATE_FORMAT(trading_date, '%Y') = ?);", year);
		
		Chart chart = new Chart();
		chart.setCriteriaDate(map.get("MIN(trading_date)").toString());
		chart.setComparsionDate(map.get("MAX(trading_date)").toString());
		
		//終値
		Map<String, Object> map2 = jdbc.queryForMap("SELECT closing_price FROM stock_prices WHERE trading_date= ? AND stock_code='0000' ;", chart.getCriteriaDate());
		Map<String, Object> map3 = jdbc.queryForMap("SELECT closing_price FROM stock_prices WHERE trading_date= ? AND stock_code='0000' ;", chart.getComparsionDate());
		
		chart.setCriteriavalue((double)map2.get("closing_price"));
		chart.setComparsionValue((double)map3.get("closing_price"));
		
		System.out.println(chart.getCriteriavalue());
		System.out.println(chart.getComparsionValue());
		
		return chart;
	}
		
		
}
