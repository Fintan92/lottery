/**
 * 
 * @author Fintan O'Sullivan 
 * 
 * This is the service layer. It contains all the appropriate methods as specified by the Task outline.
 *
 */

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

	//creates a ticket, adds it to the ticket list and returns the ticket.
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
	//sorts the lines on the ticket into ascending order by line score.
	public List<Line> sortLines(List<Line> lineList) {
		Collections.sort(lineList,new Comparator<Line>() {
			public int compare(Line l1, Line l2) {
				return l1.getLineScore().compareTo(l2.getLineScore());
			}
		});
		return lineList;
	}
	//generates a random line of 3 integers in range 0-2.
	public List<Integer> generateLine() {
		List<Integer> tempLine = new ArrayList<Integer>();
		for (int j=0; j <3; j++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0,3);
			tempLine.add(randomNum);
		}
		return tempLine;
	}
	//generates a score of a given line. Scoring is based off specifiaciton of task outline.
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
	//returns a list of all existing tickets created.
	public List<Ticket> getTicketList() {
		return ticketList;
	}
	//returns a ticket from the ticket list based on ticket Id
	public Ticket getTicketById(int id) {
		for (Iterator<Ticket> iter = ticketList.iterator(); iter.hasNext(); ) {
			Ticket ticket = iter.next();
			if (ticket.getTicketId() == id) {
				return ticket;
			}
		}
		return null;
		
	}
	//adds a specified amount of lines to an existing ticket selected by ticket id. Also alters status
	//so same ticket cannot be altered twice.
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
	//changes the status check so it cannot altered after checking, returns total score of ticket.
	public int getStatus(int id) {
		Ticket ticket = getTicketById(id);
		if (ticket.getStatusCheck()==false) {
			ticket.setStatusCheck(true);
		}
		return ticket.getTotalScore();
		
	}
	
}
