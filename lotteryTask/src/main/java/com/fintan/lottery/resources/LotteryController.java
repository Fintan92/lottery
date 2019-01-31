/**
 * 
 * @author Fintan O'Sullivan 
 * 
 * This is the controller in the resource layer. It contains all the relevant endpoints specified 
 * in the outline document. Each returns a json object.
 *
 */

package com.fintan.lottery.resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintan.lottery.domain.Ticket;
import com.fintan.lottery.services.LotteryService;


@RestController
@RequestMapping(value="/" , produces = MediaType.APPLICATION_JSON_VALUE)

public class LotteryController {
	
	public LotteryService lotteryService = new LotteryService();;
	private static ObjectMapper objectMapper = new ObjectMapper();
	

	//creates an instance of a ticket. As a default I have chosen 2 lines as standard for every ticket.
	@RequestMapping(value="/ticket", produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public ResponseEntity<String> createTicket(@RequestParam(required=false) String version) throws JsonProcessingException{
		Ticket newTicket = lotteryService.createTicket(2);
		String json = objectMapper.writeValueAsString(newTicket);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	//this returns a list of json objects representing all the tickets.
	@RequestMapping(value="/ticket", produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public ResponseEntity<List<String>> getTickets(@RequestParam(required=false) String version) throws JsonProcessingException{
		List<String> jsonTickets = new ArrayList<String>();
		List<Ticket> allTickets = lotteryService.getTicketList();
		for (Iterator<Ticket> iter = allTickets.iterator(); iter.hasNext(); ) {
		Ticket newTicket = iter.next();
		String json = objectMapper.writeValueAsString(newTicket);
		jsonTickets.add(json);
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonTickets);
	}
	//this returns a ticket, specified by the tickets' id.
	@RequestMapping(value="/ticket/{id}", produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public ResponseEntity<String> getIndividualTicket(@PathVariable String id) throws JsonProcessingException{
		Ticket newTicket = lotteryService.getTicketById(Integer.valueOf(id));
		String json = objectMapper.writeValueAsString(newTicket);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
	//this PUT request ammends an existing ticket specified by its' id. I chose 3 as a default number of lines to add.
	@RequestMapping(value="/ticket/{id}", produces=MediaType.APPLICATION_JSON_VALUE,method= RequestMethod.PUT)
	public ResponseEntity<String> ammendTicket(@PathVariable String id) throws JsonProcessingException{
		String output = lotteryService.amendTicketById(Integer.valueOf(id), 3);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(output);
	}
	//returns the total score of a ticket.
	@RequestMapping(value="/status/{id}", produces=MediaType.APPLICATION_JSON_VALUE,method= RequestMethod.PUT)
	public ResponseEntity<String> statusTicket(@PathVariable String id) throws JsonProcessingException{
		Integer ticketScore = lotteryService.getStatus(Integer.valueOf(id));
		String json = objectMapper.writeValueAsString(ticketScore);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}
