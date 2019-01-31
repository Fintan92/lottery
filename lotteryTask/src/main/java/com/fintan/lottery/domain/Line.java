/**
 * 
 * @author Fintan O'Sullivan 
 * 
 * This Line object is created for the lines in Tickets.
 *
 */


package com.fintan.lottery.domain;

import java.util.List;

public class Line {
	private List<Integer> line;
	private Integer lineScore;
	
	
	public Integer getLineScore() {
		return lineScore;
	}
	public void setLineScore(int lineScore) {
		this.lineScore = lineScore;
	}
	public List<Integer> getLine() {
		return line;
	}
	public void setLine(List<Integer> line) {
		this.line = line;
	}
}
