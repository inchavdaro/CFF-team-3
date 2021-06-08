package ccf.project.controller;


import ccf.project.domain.ProductModel;
import ccf.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;



    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductModel> insertProduct(@RequestBody ProductModel product){
        return ResponseEntity.ok(productService.insertProduct(product));
    }

    @GetMapping(value ="/{model}", produces = "application/json")
    public ResponseEntity<ProductModel> getByModel(@PathVariable String model){
        return ResponseEntity.of(productService.getProductByModel(model));
    }

    @GetMapping(value ="/type/{type}", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getByType(@PathVariable String type){
        return ResponseEntity.ok(productService.getProductsByType(type));
    }

    @GetMapping(value ="/brand/{brand}", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getByBrand(@PathVariable String brand){
        return ResponseEntity.ok(productService.getProductsByBrand(brand));
    }

    @GetMapping(value ="/{brand}/{type}", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getByBrandAndType(@PathVariable String brand,@PathVariable String type){
        return ResponseEntity.ok(productService.getProductsByBrandAndType(brand,type));
    }

    @PutMapping("/{model}")
    public ResponseEntity<Long> deleteProducts(@PathVariable String model){
        Long result = productService.deleteProductByModel(model).stream().count();
        if(result == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @PutMapping("/brand/{brand}")
    public ResponseEntity<Long> deleteProductsByBrand(@PathVariable String brand){
        Long result = productService.deleteProductsByBrand(brand).stream().count();
        if(result == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/type/{type}")
    public ResponseEntity<Long> deleteProductsByType(@PathVariable String type){
        Long result = productService.deleteProductsByType(type).stream().count();
        if(result == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
