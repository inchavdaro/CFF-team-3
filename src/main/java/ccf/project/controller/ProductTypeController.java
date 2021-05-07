package ccf.project.controller;


import ccf.project.service.impl.DefaultProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductTypeController {

    @Autowired
    DefaultProductTypeService defaultProductTypeService;


}
