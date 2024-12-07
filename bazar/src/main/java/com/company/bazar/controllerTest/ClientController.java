package com.company.bazar.controllerTest;import com.company.bazar.model.Client;import com.company.bazar.service.IClientService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.List;@RestController@RequestMapping("/bazar/client")public class ClientController {    @Autowired    private IClientService clientService;    @PostMapping("/create")    @ResponseStatus(HttpStatus.CREATED)    public Client createClient (@RequestBody Client client){        clientService.createCLient(client);        return client;    }    @GetMapping("/get")    @ResponseStatus(HttpStatus.OK)    public List<Client> getClients (){        List<Client>clientList = clientService.getClients();        return clientList;    }    @PutMapping("/edit")    @ResponseStatus(HttpStatus.OK)    public Client editClient (@RequestBody Client client ){        clientService.editClient(client);        return clientService.findClient(client.getIdClient());    }    @DeleteMapping("/delete")   public  ResponseEntity<?> deleteClient(@RequestParam Long idClient) {        try {            clientService.deleteClient(idClient);            return ResponseEntity.ok("Delete client successfully");        }catch (RuntimeException ex) {            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());        }catch (Exception ex){            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server internal Error");        }    }    @GetMapping("/find/client")    public ResponseEntity<?> findClient (@RequestParam long idClient){        try{        Client client = clientService.findClient(idClient);        return ResponseEntity.ok(client);    } catch (RuntimeException ex) {        // Captura la excepción y devuelve una respuesta con estado 404        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());    } catch (Exception ex) {        // Captura cualquier otra excepción no controlada        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server internal Error");    }    }}