package ccf.project.controller;


import ccf.project.domain.ProductTypeModel;
import ccf.project.service.ProductTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<ProductTypeModel> findByName(@PathVariable String name) {
        return ResponseEntity.of(productTypeService.getTypeByName(name));
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ProductTypeModel>> getAll() {
        return ResponseEntity.of(Optional.of(productTypeService.getAllTypes()));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductTypeModel> insertType(@RequestBody ProductTypeModel model) {
        return ResponseEntity.ok(productTypeService.insertType(model));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{name}")
    public ResponseEntity<ProductTypeModel> deleteType(@PathVariable String name) {
        ProductTypeModel result = productTypeService.deleteByName(name).stream().findFirst().orElse(null);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
