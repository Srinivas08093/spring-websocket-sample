package com.example.websocket;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * 
 * @author Srinivas Nangana.
 *
 */
@Controller
public class GreetingController {
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private SimpMessagingTemplate broker;

	@MessageMapping("/sender")
	@SendTo("/subscribe/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		return new Greeting("Hello, " + message.getName() + "!");
	}

	@RequestMapping(value = "/caller")
	@ResponseBody
	public String testController() {
		String time = LocalTime.now().format(TIME_FORMAT);
		broker.convertAndSend("/subscribe/greetings", new Greeting("New Record Added: " + time));

		return "Hi";
	}

}
