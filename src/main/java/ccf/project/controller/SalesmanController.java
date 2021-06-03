package ccf.project.controller;

import ccf.project.domain.SalesmanModel;
import ccf.project.service.SalesmanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.websocket.server.PathParam;

@RestController
@Validated
@RequestMapping("salesman")
public class SalesmanController {

    private final SalesmanService salesmanService;

    public SalesmanController(SalesmanService salesmanService) {
        this.salesmanService = salesmanService;
    }

    @PostMapping
    public ResponseEntity<SalesmanModel> createSalesman(@RequestBody SalesmanModel salesmanModel) {
        if (salesmanModel == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(salesmanService.createSalesman(salesmanModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesmanModel> getSalesman(@PathVariable int id) {
        return ResponseEntity.of(salesmanService.getById(id));
    }

    @GetMapping("/for-user")
    public ResponseEntity<SalesmanModel> getSalesmanByUserId(@RequestParam int userId) {
        return ResponseEntity.of(salesmanService.getById(userId));
    }

    @GetMapping
    public ResponseEntity<Page<SalesmanModel>> getSalesman(@RequestParam @Min(0) Integer page,
                                                           @RequestParam @Min(1) Integer pageSize) {
        if (page != null && pageSize != null) {
            return ResponseEntity.ok(salesmanService.getAll(page, pageSize));
        }
        return ResponseEntity.ok(salesmanService.getAll());
    }

}
