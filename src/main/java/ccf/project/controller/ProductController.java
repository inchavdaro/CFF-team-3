package ccf.project.controller;


import ccf.project.domain.ProductModel;
import ccf.project.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductModel> insertProduct(@RequestBody ProductModel product) {
        return ResponseEntity.ok(productService.insertProduct(product));
    }

    @GetMapping(value = "/{model}", produces = "application/json")
    public ResponseEntity<ProductModel> getByModel(@PathVariable String model) {
        return ResponseEntity.of(productService.getProductByModel(model));
    }

    @GetMapping(value = "/type/{type}", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(productService.getProductsByType(type));
    }

    @GetMapping(value = "/brand/{brand}", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productService.getProductsByBrand(brand));
    }

    @GetMapping(value = "/{brand}/{type}", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getByBrandAndType(@PathVariable String brand, @PathVariable String type) {
        return ResponseEntity.ok(productService.getProductsByBrandAndType(brand, type));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{model}")
    public ResponseEntity<Long> deleteProducts(@PathVariable String model) {
        long result = productService.deleteProductByModel(model).size();
        if (result == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/brand/{brand}")
    public ResponseEntity<Long> deleteProductsByBrand(@PathVariable String brand) {
        long result = productService.deleteProductsByBrand(brand).size();
        if (result == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/type/{type}")
    public ResponseEntity<Long> deleteProductsByType(@PathVariable String type) {
        long result = productService.deleteProductsByType(type).size();
        if (result == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
