package com.example.demo.chart.model;

public class Chart {
	
	private String stockCode;//銘柄番号
	private String companyName;//社名
	private String tradingDate;//取引日
	private double openingPrice;//始値
	private double highPrice;//高値
	private double lowPrice;//安値
	private double closingPrice;//終値
	private double stockProduction;//出来高
	private double adjustmentClosingPrice;//調整後終値
	private int id;//識別番号
	
	
	private String year;//検索年
	private String criteriaDate;//基準日
	private String 	comparsionDate;//比較日
	private double criteriavalue;//基準値
	private double comparsionValue;//比較値
	
	
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTradingDate() {
		return tradingDate;
	}
	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate;
	}
	public double getOpeningPrice() {
		return openingPrice;
	}
	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}
	public double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
	public double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public double getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}
	public double getStockProduction() {
		return stockProduction;
	}
	public void setStockProduction(double stockProduction) {
		this.stockProduction = stockProduction;
	}
	public double getAdjustmentClosingPrice() {
		return adjustmentClosingPrice;
	}
	public void setAdjustmentClosingPrice(double adjustmentClosingPrice) {
		this.adjustmentClosingPrice = adjustmentClosingPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCriteriaDate() {
		return criteriaDate;
	}
	public void setCriteriaDate(String criteriaDate) {
		this.criteriaDate = criteriaDate;
	}
	public String getComparsionDate() {
		return comparsionDate;
	}
	public void setComparsionDate(String comparsionDate) {
		this.comparsionDate = comparsionDate;
	}
	public double getCriteriavalue() {
		return criteriavalue;
	}
	public void setCriteriavalue(double criteriavalue) {
		this.criteriavalue = criteriavalue;
	}
	public double getComparsionValue() {
		return comparsionValue;
	}
	public void setComparsionValue(double comparsionValue) {
		this.comparsionValue = comparsionValue;
	}
}
