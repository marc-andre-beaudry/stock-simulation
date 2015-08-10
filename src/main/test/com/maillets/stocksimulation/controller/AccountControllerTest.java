package com.maillets.stocksimulation.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.maillets.stocksimulation.TestApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
public class AccountControllerTest extends BaseRestControllerTest {

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}
//
//	@Test
//	public void get_all_accounts_on_empty_repo_then() throws Exception {
//		mockMvc.perform(get("/players")).andExpect(status().isOk());
//	}
//
//	@Test
//	public void get_all_on_empty_repo_then_is_empty() throws Exception {
//		mockMvc.perform(get("/players")).andExpect(status().isOk());
//	}
//
//	@Test
//	public void get_id_on_empty_repo_then_is_not_found() throws Exception {
//		mockMvc.perform(get("/players/1")).andExpect(status().isNotFound());
//	}
//
//	@Test
//	public void get_id_with_invalid_id_format_then_bad_request() throws Exception {
//		mockMvc.perform(get("/players/one")).andExpect(status().isBadRequest());
//	}

//	@Test
//	public void get_all_on_non_empty_repo_then_is_ok() throws Exception {
//		Player player = TestUtils.generatePlayer("Carey", "Price");
//		playerRepository.saveAndFlush(player);
//		mockMvc.perform(get("/players")).andExpect(status().isOk());
//	}
//
//	@Test
//	public void add_player_then_get_all_then_size_is_one() throws Exception {
//		Player player = TestUtils.generatePlayer("Carey", "Price");
//		playerRepository.saveAndFlush(player);
//		mockMvc.perform(get("/players")).andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
//	}
//
//	@Test
//	public void add_two_player_then_get_all_then_size_is_two() throws Exception {
//		Player player1 = TestUtils.generatePlayer("Carey", "Price");
//		Player player2 = TestUtils.generatePlayer("Max", "Pacioretty");
//		playerRepository.saveAndFlush(player1);
//		playerRepository.saveAndFlush(player2);
//		mockMvc.perform(get("/players")).andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)));
//	}
}
