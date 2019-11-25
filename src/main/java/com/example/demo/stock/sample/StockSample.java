package com.example.demo.stock.sample;

public class StockSample {

	private String today;//本日
	private String openingQuotation;//始値
	private String high;//高値
	private String low;//安値
	private String closingQuotation;//終値
	private String dayOverDayChanges;//前日比
	private String dayOverDayChangesPercent;//前日比％
	private String turnover;//売買高(株)
	
	private int kabuka;
	
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
	public double getKabuka() {
		return kabuka;
	}
	public void setKabuka(int kabuka) {
		this.kabuka = kabuka;
	}
	
}
