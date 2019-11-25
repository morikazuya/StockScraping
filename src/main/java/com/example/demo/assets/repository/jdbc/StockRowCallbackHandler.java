package com.example.demo.assets.repository.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

public class StockRowCallbackHandler implements RowCallbackHandler {

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		
		try {
			
			// ファイルの書き込み準備
			File file = new File("sample_stock.csv");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			// 件数取得分 loop
			do {
				
				// ResultSet から値を取得して String にセット
				String str = rs.getString("stock_brand") + ","
						+ rs.getString("stock_name") + ","
						+ rs.getString("stock_amount") + ","
						+ rs.getDate("stock_num") + ","
						+ rs.getInt("stock_pl") + ","
						+ rs.getBoolean("stock_hold");
				
				// ファイルに書き込み＆改行
				bw.write(str);
				bw.newLine();
				
			} while(rs.next());
			
			// 強制的に書き込み＆ファイルクローズ
			bw.flush();
			bw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}
}
