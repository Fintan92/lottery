package com.fintan.lottery.resources;

import com.fintan.lottery.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/" , produces = MediaType.APPLICATION_JSON_VALUE)
public class LotteryController {
	@Autowired
	private LotteryService lotteryService;
	
	@RequestMapping(value="/ticket", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public  createTicket()
}
