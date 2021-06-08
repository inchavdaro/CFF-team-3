package ccf.project.controller;

import ccf.project.domain.BrandModel;
import ccf.project.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brands")
public class BrandController
{
    @Autowired
    BrandService brandService;

    @GetMapping(value ="/{name}", produces = "application/json")
    public ResponseEntity<BrandModel> findByName(@PathVariable String name){
        return ResponseEntity.of(brandService.getByName(name));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BrandModel> insertBrand(@RequestBody BrandModel model){
        return ResponseEntity.ok(brandService.insertBrand(model));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Long> deleteBrand(@PathVariable String name){
        Long result = brandService.deleteByName(name);
        if(result == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}
