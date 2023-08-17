package com.example.demo.controller.customer;

import com.example.demo.service.Impl.ServiceKhachHangImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    ServiceKhachHangImpl svkh;

    @GetMapping("/getAll")
    private String getAll(){
        return svkh.getAll().toString();
    }
}
