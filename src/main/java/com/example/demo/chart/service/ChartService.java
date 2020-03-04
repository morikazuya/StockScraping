package com.example.demo.chart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.chart.model.Chart;
import com.example.demo.chart.repository.ChartDao;

@Transactional
@Service
public class ChartService {

	@Autowired
	@Qualifier("ChartDaoJdbcImpl")
	ChartDao dao;
	
	//基準取得
	public Chart selectCriteria(String year) {
		
		return dao.selectCriteria(year);
	}
}
