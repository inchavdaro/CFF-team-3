package ccf.project.controller;

import ccf.project.domain.SaleModel;
import ccf.project.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Controller
@RequestMapping("/sales")
public class SaleController {
    SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<Page<SaleModel>> getPage(@RequestParam Integer clientId, @RequestParam @Min(0) Integer pageNumber, @RequestParam @Min(1) Integer salesPerPage) {
        return ResponseEntity.ok(saleService.getByClient(clientId, PageRequest.of(pageNumber, salesPerPage)));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<SaleModel> insertSale(SaleModel sale) {
        return ResponseEntity.ok(saleService.insert(sale));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable int id) {
        Long result = saleService.deleteById(id);
        if (result == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
