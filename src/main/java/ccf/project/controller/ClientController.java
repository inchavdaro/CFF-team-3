package ccf.project.controller;

import ccf.project.domain.ClientModel;
import ccf.project.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Controller
@RequestMapping("/clients")
public class ClientController {


    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "/{bulstat}", produces = "application/json")
    public ResponseEntity<ClientModel> getByBulstat(@PathVariable String bulstat) {
        return ResponseEntity.of(clientService.getByBulstat(bulstat));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ClientModel>> getPageOfClients(@RequestParam @Min(0) Integer pageNumber, @RequestParam @Min(1) Integer clientsPerPage) {
        if (pageNumber != null && clientsPerPage != null) {
            return ResponseEntity.ok(clientService.getPageOfClients(pageNumber, clientsPerPage));
        }
        return ResponseEntity.ok(clientService.getAll());
    }
//part of salesController
//    @GetMapping(value = "/{bulstat}/sales", produces = "application/json")
//    public Page<SaleModel> getNextPageOfSales(@PathVariable String bulstat, @RequestBody int pageNumber, @RequestBody int salesPerPage){
//        return clientService.getPageOfSales(bulstat, pageNumber, salesPerPage);
//    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientModel> insertClient(@RequestBody ClientModel client) {
        return ResponseEntity.ok(clientService.insert(client));
    }

    @PutMapping("/{bulstat}")
    public ResponseEntity<Long> deleteClient(@PathVariable String bulstat) {
        Long result = clientService.deleteByBulstat(bulstat);
        if (result == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
