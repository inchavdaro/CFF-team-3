package ccf.project.controller;

import ccf.project.domain.ClientModel;
import ccf.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/{bulstat}", produces = "application/json")
    public ResponseEntity<ClientModel> findByBulstat(@PathVariable String bulstat){
        return ResponseEntity.of(clientService.findByBulstat(bulstat));
    }

    //clientService.getNextPage() ? kakto i za sales ?
    @GetMapping(produces = "application/json")
    public ResponseEntity<Collection<ClientModel>> findAll(){
        return null;
    }

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
