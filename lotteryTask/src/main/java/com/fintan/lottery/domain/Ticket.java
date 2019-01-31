/**
 * 
 * @author Fintan O'Sullivan 
 * 
 * This is the ticket object that is being requested.
 *
 */

package com.fintan.lottery.domain;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
	
	private int ticketId;
	private int numLines;
	private List<Line> lines = new ArrayList<Line>();
	private boolean statusCheck = false;
	private int totalScore;
	
	public Ticket(int ticketId, int numLines, List<Line> lines,int totalScore) {
		this.ticketId = ticketId;
		this.numLines = numLines;
		this.lines = lines;
		this.totalScore = totalScore;
	}
	


	public List<Line> getLines() {
		return lines;
	}
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getNumLines() {
		return numLines;
	}
	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}
	public boolean getStatusCheck() {
		return statusCheck;
	}
	public void setStatusCheck(boolean statusCheck) {
		this.statusCheck = statusCheck;
	}


	public int getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	
}
