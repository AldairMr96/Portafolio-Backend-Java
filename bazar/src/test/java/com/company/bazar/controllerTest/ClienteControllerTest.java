package com.company.bazar.controllerTest;


import com.company.bazar.controller.ClientController;
import com.company.bazar.model.Client;
import com.company.bazar.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

public class ClienteControllerTest {

    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    private void setUp (){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createClienteControllerTest (){
        // Test data
        Client client = new Client(1L, "Aldair", "Martinez", "123456789", new ArrayList<>());

        // configure mock test Services
        doNothing().when(clientService).createCLient(client);

        //Run the method to be tested
        Client result = clientController.createClient(client);

        //verify service
        verify(clientService, times(1)).createCLient(client);

        //check data customer
        assertNotNull(result);
        assertEquals(client.getIdClient(), result.getIdClient());
        assertEquals(client.getNameClient(), result.getNameClient());
        assertEquals(client.getLastnameClient(), result.getLastnameClient());
        assertEquals(client.getDni(), result.getDni());
    }

    @Test
    public void getClientControllerEmptyTest (){
        when(clientService.getClients()).thenReturn(Collections.emptyList());
        List<Client> clients = clientController.getClients();

        assertTrue(clients.isEmpty());
    }
    @Test
    public void getClientControllerTest (){
        List<Client> clientList = List.of(
                new Client(1L, "Aldair", "Martinez", "1232345", new ArrayList<>()),
                new Client (2L, "Lina", "Bermudez", "12345", new ArrayList<>())
                );
        when(clientService.getClients()).thenReturn(clientList);
        List<Client> clients = clientController.getClients();

        assertFalse(clients.isEmpty());
        assertEquals(2, clients.size());

    }

    @Test
    public void findClientControllerTest (){
        Long idTest = 1L ;
        Client client = new Client(1L, "Aldair", "Martinez", "123456789", new ArrayList<>());

        when(clientService.findClient(idTest)).thenReturn(client);

        ResponseEntity<?> responseEntity = clientController.findClient(idTest);

        assertNotNull(responseEntity);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(client, responseEntity.getBody());
        verify(clientService, times(1)).findClient(idTest);
    }
    @Test
    public void findClientControllerNotFoundTest (){
        Long idTest = 1L ;

        when(clientService.findClient(idTest)).thenThrow( new EntityNotFoundException("Client not found"));

        ResponseEntity<?> responseEntity = clientController.findClient(idTest);


        assertNotNull(responseEntity);
        assertEquals(NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Client not found", responseEntity.getBody());
        verify(clientService, times(1)).findClient(idTest);
    }

    @Test
    public void findClientInternalErrorTest (){
        Long idTest = 1L ;

        when(clientService.findClient(idTest)).thenThrow( new RuntimeException("Unexpected server error"));

        ResponseEntity<?> responseEntity = clientController.findClient(idTest);

        assertNotNull(responseEntity);
        assertEquals(INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        verify(clientService, times(1)).findClient(idTest);

    }
    @Test
    public void deleteClientControllerTest(){
        doNothing().when(clientService).deleteClient(1L);

        ResponseEntity<?> responseEntity = clientController.deleteClient(1L);

        assertNotNull(responseEntity);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals("Delete client successfully", responseEntity.getBody());
        verify(clientService, times(1)).deleteClient(1L);
    }

    @Test
    public void deleteClientControllerNotFoundTest(){

        doThrow(new EntityNotFoundException("Client not found")).when(clientService).deleteClient(1L);

        ResponseEntity<?>responseEntity= clientController.deleteClient(1L);

        assertNotNull(responseEntity);
        assertEquals(NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Client not found", responseEntity.getBody());
        verify(clientService, times(1)).deleteClient(1L);

    }
    @Test
    public void deleteClientControllerErrorTest() {

        doThrow(new RuntimeException("Server internal Error")).when(clientService).deleteClient(1L);

        ResponseEntity<?>responseEntity = clientController.deleteClient(1L);

        assertNotNull(responseEntity);
        assertEquals(INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Server internal Error", responseEntity.getBody());
        verify(clientService, times(1)).deleteClient(1L);
    }

    @Test
    public  void editClientControllerTest (){
      Client client =  new Client(1L, "Aldair", "Martinez", "123456789", new ArrayList<>());

      doNothing().when(clientService).editClient(client);
      when(clientService.findClient(client.getIdClient())).thenReturn(client);

      Client editedClient = clientController.editClient(client);
        assertNotNull(editedClient);
        assertEquals(client.getIdClient(), editedClient.getIdClient());
        assertEquals(client.getNameClient(), editedClient.getNameClient());
        assertEquals(client.getLastnameClient(), editedClient.getLastnameClient());

        verify(clientService, times(1)).editClient(client);
        verify(clientService, times(1)).findClient(client.getIdClient());
    }

}


