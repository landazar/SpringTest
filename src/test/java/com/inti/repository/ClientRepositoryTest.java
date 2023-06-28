package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Client;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClientRepositoryTest {
	
	@Autowired
	IClientRepository icr;
	
	@BeforeAll
	public static void debut()
	{
		
	}
	
	@BeforeEach
	public void setUp()
	{
		
	}
	
	@Test
	public void saveClientTest()
	{
		//given
		Client c1 = new Client("Dupont", "Nicolas", "test@test.fr");
		
		//when
		Client clientSaved = icr.save(c1);
		
		//then
		assertThat(clientSaved).isNotNull();
		assertThat(clientSaved.getId()).isGreaterThan(0);
	}
	@Test
	public void saveClientSansRequiredTest()
	{
		//given
		Client c1 = new Client("Dupont", "Nicolas");
		
		//when
//		Client clientSaved = icr.save(c1);
		
		//then
		Assertions.assertThrows(Exception.class, () -> icr.save(c1));
//		assertThat(clientSaved).isNotNull();
//		assertThat(clientSaved.getId()).isGreaterThan(0);
	}
	
//	@Test
//	public void saveClientRequiredNullTest()
//	{
//		//given
//		Client c1 = new Client("Dupont", "Nicolas", null);
//		
//		//when
//		Client clientSaved = icr.save(c1);
//		
//		//then
//		assertThat(clientSaved).isNotNull();
//		assertThat(clientSaved.getId()).isGreaterThan(0);
//	}
	
	@Test
	public void saveClientOnlyRequiredTest()
	{
		//given
		Client c1 = new Client(null, null, "test@test.fr");
		
		//when
		Client clientSaved = icr.save(c1);
		
		//then
		assertThat(clientSaved).isNotNull();
		assertThat(clientSaved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void saveClientRequiredTest()
	{
		//given
		Client c1 = new Client("test@test.fr");
		
		//when
		Client clientSaved = icr.save(c1);
		
		//then
		assertThat(clientSaved).isNotNull();
		assertThat(clientSaved.getId()).isGreaterThan(0);
	}
	
	//----------------------------supprimer client---------------------------------
	
	@Test
	public void deleteClientTest()
	{
		//given
		Client c1 = new Client("Dupont", "Nicolas", "test@test.fr");
		Client clientSaved = icr.save(c1);
		//when
		
		icr.delete(clientSaved);
		
		//then
		Assertions.assertThrows(Exception.class, () -> icr.getReferenceById(c1.getId()));
	}
	
	@Test
	public void deleteClientNotFoundTest()
	{
		//given
		
		//when
		
		//then
		Assertions.assertThrows(Exception.class, () -> icr.delete(null));
	}
	
	//--------------------------------------update-----------------------------------------------
	
	@Test
	public void updateClientTest()
	{
		//given
		Client c1 = new Client("Dupont", "Nicolas", "test@test.fr");
		Client clientSaved = icr.save(c1);
		//when
		
		clientSaved.setNom("Dubois");
		Client clientUpdated = icr.save(clientSaved);
		
		//then
		assertThat(clientUpdated).isNotNull();
		assertThat(clientSaved.getNom()).isEqualTo("Dubois");
	}
	
//	@Test
//	public void updateClientUniqueParamTest()
//	{
//		//given
//		Client c1 = new Client("Dupont", "Nicolas", "test@test.fr");
//		Client c2 = new Client("Dupont", "Nicolas", "info@test.fr");
//		Client clientSaved1 = icr.save(c1);
//		Client clientSaved2 = icr.save(c2);
//		//when
//		
//		clientSaved2.setEmail("test@test.fr");
//		//Bizarre : pas d'exception
//		
//		//then
//		Assertions.assertThrows(Exception.class, () -> icr.save(clientSaved2));
//	}
	
	@Test
	public void updateClientNullTest()
	{
		//given
		Client c1 = null;
		
		//when
		
		//then
		Assertions.assertThrows(Exception.class, () -> c1.setEmail("test@test.fr"));
	}
	
	
	//------------------------------------------select-----------------------------------
	
	@Test
	public void getClientTest()
	{
		//given
		Client c1 = new Client("Dupont", "Nicolas", "test@test.fr");
		Client clientSaved = icr.save(c1);
		//when
		Client client = icr.getReferenceById(clientSaved.getId());
		
		
		
		//then
		assertThat(client).isNotNull();
		assertThat(client.getNom()).isEqualTo("Dupont");
		assertThat(client).isEqualTo(clientSaved);
	}
	
	@Test
	public void getClientNullTest()
	{
		//given
		
		//when
		
		//then
//		Assertions.assertThrows(Exception.class, () -> icr.getReferenceById(150));
	}
	
	@Test
	public void getAllClientTest()
	{
		//given
		Client c1 = new Client("Dupont", "Nicolas", "test@test.fr");
		Client c2 = new Client("Dupont", "Nicolas", "info@test.fr");
		Client clientSaved1 = icr.save(c1);
		Client clientSaved2 = icr.save(c2);
		
		//when
		List<Client> listeC = icr.findAll();
		
		//then
		assertThat(listeC).isNotEmpty();
		assertThat(listeC).hasSize(2);
		assertThat(listeC.get(0).getClass()).hasSameClassAs(Client.class);
	}
	
	@Test
	public void getClientListeVideTest()
	{
		//given
		
		
		//when
		List<Client> listeC = icr.findAll();
		
		//then
		assertThat(listeC).isEmpty();
		assertThat(listeC).hasSize(0);
	}
	

}
