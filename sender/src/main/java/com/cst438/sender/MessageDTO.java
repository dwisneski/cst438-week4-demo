package com.cst438.sender;

import java.util.Date;

public class MessageDTO {
	public Date date_time;
	public int  msg_no;
	public String text;
	
	
	@Override
	public String toString() {
		return "[date_time=" + date_time + ", msg_no=" + msg_no + ", text=" + text + "]";
	}
	
}
