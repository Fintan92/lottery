package com.fintan.lottery.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.fintan.lottery.domain.Line;
import com.fintan.lottery.domain.Ticket;

public class LotteryService {
	private static List<Ticket> ticketList = new ArrayList<Ticket>();
	private static int id = 0;

	
	public Ticket createTicket(int numLines) {
		List<Line> lineList = new ArrayList<Line>();
		int ticketScore = 0;
		for (int i=0; i < numLines; i++) {
			List<Integer> lineSeq = generateLine();
			int lineScore = generateScore(lineSeq);
			ticketScore += lineScore;
			Line line = new Line();
			line.setLine(lineSeq);
			line.setLineScore(lineScore);
			lineList.add(line);
		}
		List<Line> sortedLineList = sortLines(lineList);
		
		Ticket ticket = new Ticket(id,numLines,sortedLineList,ticketScore);
		id += 1;
		ticketList.add(ticket);
		return ticket;
	}
	
	public List<Line> sortLines(List<Line> lineList) {
		Collections.sort(lineList,new Comparator<Line>() {
			public int compare(Line l1, Line l2) {
				return l1.getLineScore().compareTo(l2.getLineScore());
			}
		});
		return lineList;
	}

	public List<Integer> generateLine() {
		List<Integer> tempLine = new ArrayList<Integer>();
		for (int j=0; j <2; j++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0,3);
			tempLine.add(randomNum);
		}
		return tempLine;
	}
	
	public int generateScore(List<Integer> line){
		if (line.get(0)+line.get(1)+line.get(2)==2 ) {
			return 10;
		}
		else if (line.get(0)==line.get(1)&&line.get(1)==line.get(2)  && line.get(0)==line.get(2)) {
			return 5;
		}
		else if (line.get(0)!=line.get(1)&&line.get(0)!=line.get(2)) {
			return 1;
		}
		else {
			return 0;
		}
			
	
	}
	
	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public Ticket getTicketById(int id) {
		for (Iterator<Ticket> iter = ticketList.iterator(); iter.hasNext(); ) {
			Ticket ticket = iter.next();
			if (ticket.getTicketId() == id) {
				return ticket;
			}
		}
		return null;
		
	}
	public String amendTicketById(int id, int numLines) {
		/*check if false, add lines, else return error message*/
		Ticket ticket = getTicketById(id);
		if (ticket.getStatusCheck()==false) {
			List<Line> lineList = ticket.getLines();
			for (int i=0; i < numLines; i++) {
				List<Integer> lineSeq = generateLine();
				int lineScore = generateScore(lineSeq);
				ticket.setTotalScore(ticket.getTotalScore()+lineScore);
				Line line = new Line();
				line.setLine(lineSeq);
				line.setLineScore(lineScore);
				lineList.add(line);
			}
			ticket.setLines(sortLines(lineList));
			ticket.setNumLines(ticket.getNumLines()+numLines);
			ticket.setStatusCheck(true);
			return "Ticket has been ammended.";
		}else
			return "Sorry your ticket can no longer be ammended.";
		
	}
	public int getStatus(int id) {
		Ticket ticket = getTicketById(id);
		if (ticket.getStatusCheck()==false) {
			ticket.setStatusCheck(true);
		}
		return ticket.getTotalScore();
		
	}
	
}
