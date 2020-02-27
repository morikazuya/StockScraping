package com.example.demo.assets.model;


import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationForm {
	
	@NotBlank
	private String stockBrand;//銘柄番号
	
	private String stockName;//銘柄
	
	@NotBlank
	private int stockAmount;//購入額
	
	@NotBlank
	private int stockNum;//購入数
	
	private int stockPl;//損益
	
	//private boolean stockHold;//保持状況
}
