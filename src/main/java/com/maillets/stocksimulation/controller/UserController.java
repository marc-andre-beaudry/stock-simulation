package com.maillets.stocksimulation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maillets.stocksimulation.dto.UserDto;
import com.maillets.stocksimulation.identity.AuthorizationModel;
import com.maillets.stocksimulation.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorizationModel authorizationModel;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public UserDto getAccounts() {
		logger.debug("GET /");

		String userId = authorizationModel.getUserId();
		if (userId == null) {
			return null;
		}

		UserDto userDto = new UserDto();
		userDto.setFirstName("Marc-Andre");
		userDto.setLastName("Beaudry");
		return userDto;
	}
}
