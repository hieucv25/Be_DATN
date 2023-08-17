package com.example.demo.controller;
import com.example.demo.auth.AuthenticationRequest;
import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.Impl.ServiceKhachHangImpl;
import com.example.demo.service.Impl.ServiceNhanVienImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    @Autowired
    ServiceNhanVienImpl svnv;
    @Autowired
    ServiceKhachHangImpl svkh;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>login(@RequestBody AuthenticationRequest authenticationRequest){
        System.out.println(authenticationRequest.getEmail() + " " +authenticationRequest.getPassword());
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/getByNumberPhone/{number_phone}")
    public Object login(@PathVariable("number_phone") String number_phone){
        if(svnv.findByNumberPhone(number_phone) != null) {
            return svnv.findByNumberPhone(number_phone);
        } else {
            return svkh.findByNumberPhone(number_phone);
        }
    }

    @GetMapping("/home")
    public String login(){
        return "Home";
    }
}
