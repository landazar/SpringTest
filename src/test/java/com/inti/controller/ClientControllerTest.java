package com.inti.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.inti.model.Client;
import com.inti.repository.IClientRepository;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	IClientRepository icr1;
	
	@MockBean
	private IClientRepository icr;
	
	@Test
	public void saveClient() throws Exception
	{
		mockMvc.perform(get("/creerClient"))
		.andExpect(status().isOk())
//		.andExpect((ResultMatcher) content().string(containsString("email")))
		.andDo(print());
	}
	
	@Test
	public void listeClient() throws Exception
	{
		mockMvc.perform(get("/listeClient"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteClient() throws Exception
	{
		mockMvc.perform(get("/deleteClient/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeClient"))
		.andDo(print());
	}
	
//	@Test
//	public void modifierClient() throws Exception
//	{
//		Client c1 = new Client("Dupont", "Nicolas", "test@test.fr");
//		
//		Client clientSaved = icr.save(c1);
//		
//		mockMvc.perform(get("/modifierClient/1").requestAttr("client", clientSaved))
//		.andExpect(status().isOk())
//		.andDo(print());
//	}
	
	//------------------------post--------------------------
	
	@Test
	public void saveClientPost() throws Exception
	{
		mockMvc.perform(post("/creerClient").sessionAttr("client", new Client("Dupont", "Nicolas", "test@test.fr")))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeClient"))
		.andDo(print());
	}
	
	@Test
	public void modifierClientPost() throws Exception
	{
		mockMvc.perform(post("/modifierClient/1").sessionAttr("client", new Client("Dupont", "Nicolas", "test@test.fr")))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeClient"))
		.andDo(print());
	}

}
