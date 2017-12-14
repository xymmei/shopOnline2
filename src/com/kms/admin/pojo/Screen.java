package com.kms.admin.pojo;

import java.time.YearMonth;

public class Screen {
	private int single;
	private YearMonth startMonth;
	private YearMonth endMonth;
	public int getSingle() {
		return single;
	}
	public void setSingle(int single) {
		this.single = single;
	}
	public YearMonth getStartMonth() {
		
		return startMonth;
	}
	public void setStartMonth(YearMonth startMonth) {
		this.startMonth = startMonth;
	}
	public YearMonth getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(YearMonth endMonth) {
		this.endMonth = endMonth;
	}
	
}
