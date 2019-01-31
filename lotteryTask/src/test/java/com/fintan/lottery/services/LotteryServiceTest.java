package com.fintan.lottery.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintan.lottery.domain.Line;
import com.fintan.lottery.domain.Ticket;

public class LotteryServiceTest {
	
	@Autowired
	private LotteryService lotteryService;
	
	

	@Test
	public void testCreateTicket() {
		Ticket ticket = new Ticket(0,0,null,10);
		
		Ticket lotteryTicket = lotteryService.createTicket(2);
		lotteryTicket.setLines(null);
		lotteryTicket.setTotalScore(10);
		
		assertThat(lotteryTicket).isEqualTo(ticket);
		
	}
	
	@Test
	public void testSortLines() {
		
		List<Integer> arr1 = new ArrayList<Integer>();
		arr1.add(1);
		arr1.add(1);
		arr1.add(1);
		
		List<Integer> arr2 = new ArrayList<Integer>();
		arr2.add(0);
		arr2.add(1);
		arr2.add(1);
		
		List<Line> lineList = new ArrayList<Line>();
		Line line1 = new Line();
		Line line2 = new Line();
		
		line1.setLine(arr1);
		line1.setLineScore(5);
		
		line2.setLine(arr2);
		line2.setLineScore(10);
		
		lineList.add(line1);
		lineList.add(line2);
		
		List<Line> sortList = new ArrayList<Line>();
		sortList.add(line2);
		sortList.add(line1);
		
		assertThat(lotteryService.sortLines(sortList)).isEqualTo(lineList);
		
	}
	@Test
	public void testGenerateLine() {
		/*check length and each value in range 0-2*/
		List<Integer> line = lotteryService.generateLine();
		for (int i=0; i<line.size(); i++) {
			assertTrue(line.get(i)>= 0 && line.get(i) <= 2);
		}
		assertThat(line.size()).isEqualTo(2);
	}
	
	@Test
	public void testGenerateScore() {
		List<Integer> line = new ArrayList<Integer>();
		line.add(1);
		line.add(1);
		line.add(1);
		assertThat(lotteryService.generateScore(line)).isEqualTo(5);
	}
	
	
	@Test
	public void testGetTicketById() {
		Ticket ticket = lotteryService.createTicket(8);
		ticket.setTicketId(10);
		assertThat(lotteryService.getTicketById(10).getNumLines()).isEqualTo(8);
		
	}
	
	@Test
	public void testAmmendTicketById() {
		/*check length of numLines status*/
		lotteryService.createTicket(2);
		lotteryService.amendTicketById(0, 3);
		assertThat(lotteryService.getTicketById(0).getNumLines()).isEqualTo(5);
		
		assertThat(lotteryService.getTicketById(0).getStatusCheck()).isEqualTo(true);
		
	}
	
	@Test
	public void testGetStatus() {
		Ticket ticket = lotteryService.createTicket(2);
		ticket.setTicketId(12);
		assertThat(lotteryService.getStatus(12)).isEqualTo(false);
	}

}
