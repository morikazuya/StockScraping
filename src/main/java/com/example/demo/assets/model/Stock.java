package com.example.demo.assets.model;

public class Stock {

	private String stockBrand;//銘柄番号
	private String stockName;//銘柄
	private int stockAmount;//購入額
	private int stockNum;//購入数
	private int stockPl;//損益
	private boolean stockHold;//保持状況
	
	private String today;//本日
	private String openingQuotation;//始値
	private String high;//高値
	private String low;//安値
	private String closingQuotation;//終値
	private String dayOverDayChanges;//前日比
	private String dayOverDayChangesPercent;//前日比％
	private String turnover;//売買高(株)
	
	private int kabuka;
	
	public String getStockBrand() {
		return stockBrand;
	}
	public void setStockBrand(String stockBrand) {
		this.stockBrand = stockBrand;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}
	public int getStockNum() {
		return stockNum;
	}
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
	public int getStockPl() {
		return stockPl;
	}
	public void setStockPl(int stockPl) {
		this.stockPl = stockPl;
	}
	public boolean isStockHold() {
		return stockHold;
	}
	public void setStockHold(boolean stockHold) {
		this.stockHold = stockHold;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getOpeningQuotation() {
		return openingQuotation;
	}
	public void setOpeningQuotation(String openingQuotation) {
		this.openingQuotation = openingQuotation;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getClosingQuotation() {
		return closingQuotation;
	}
	public void setClosingQuotation(String closingQuotation) {
		this.closingQuotation = closingQuotation;
	}
	public String getDayOverDayChanges() {
		return dayOverDayChanges;
	}
	public void setDayOverDayChanges(String dayOverDayChanges) {
		this.dayOverDayChanges = dayOverDayChanges;
	}
	public String getDayOverDayChangesPercent() {
		return dayOverDayChangesPercent;
	}
	public void setDayOverDayChangesPercent(String dayOverDayChangesPercent) {
		this.dayOverDayChangesPercent = dayOverDayChangesPercent;
	}
	public String getTurnover() {
		return turnover;
	}
	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}
	public int getKabuka() {
		return kabuka;
	}
	public void setKabuka(int kabuka) {
		this.kabuka = kabuka;
	}
	
}
