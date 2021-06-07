package ccf.project.controller;

import ccf.project.domain.ClientModel;
import ccf.project.domain.SaleModel;
import ccf.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {


    ClientService clientService;
    int currentPage;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        this.currentPage = 0;
    }


    @GetMapping(value = "/{bulstat}", produces = "application/json")
    public ResponseEntity<ClientModel> findByBulstat(@PathVariable String bulstat){
        return ResponseEntity.of(clientService.findByBulstat(bulstat));
    }

    @GetMapping(produces = "application/json")
    public Page<ClientModel> getPageOfClients(@RequestParam int pageNumber, @RequestParam int clientsPerPage){
        return clientService.getPageOfClients(pageNumber, clientsPerPage);
    }
//part of salesController
//    @GetMapping(value = "/{bulstat}/sales", produces = "application/json")
//    public Page<SaleModel> getNextPageOfSales(@PathVariable String bulstat, @RequestBody int pageNumber, @RequestBody int salesPerPage){
//        return clientService.getPageOfSales(bulstat, pageNumber, salesPerPage);
//    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientModel> saveClient(@RequestBody ClientModel client){
        return ResponseEntity.ok(clientService.save(client));
    }

    @PutMapping("/{bulstat}")
    public ResponseEntity<Long> deleteClient(@PathVariable String bulstat){
        Long result = clientService.deleteByBulstat(bulstat);
        if(result == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
