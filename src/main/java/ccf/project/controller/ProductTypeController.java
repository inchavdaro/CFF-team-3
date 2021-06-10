package ccf.project.controller;



import ccf.project.domain.ProductTypeModel;
import ccf.project.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;


    @GetMapping(value ="/{name}", produces = "application/json")
    public ResponseEntity<ProductTypeModel> findByName(@PathVariable String name){
        return ResponseEntity.of(productTypeService.getTypeByName(name));
    }

    @GetMapping(value ="/all", produces = "application/json")
    public ResponseEntity<List<ProductTypeModel>> getAll(){
        return ResponseEntity.of(Optional.of(productTypeService.getAllTypes()));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductTypeModel> insertType(@RequestBody ProductTypeModel model){
        return ResponseEntity.ok(productTypeService.insertType(model));
    }

    @PutMapping("/{name}")
    public ResponseEntity<ProductTypeModel> deleteType(@PathVariable String name){
        ProductTypeModel result = productTypeService.deleteByName(name).stream().findFirst().orElse(null);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
