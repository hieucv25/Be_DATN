package com.example.demo.controller;
import com.example.demo.auth.AuthenticationRequest;
import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.model.Author_KhachHang;
import com.example.demo.model.Author_NhanVien;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.Impl.ServiceKhachHangImpl;
import com.example.demo.service.Impl.ServiceNhanVienImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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

    @PostMapping("/login_Again")
    public ResponseEntity<AuthenticationResponse>loginAgain(@RequestBody AuthenticationRequest authenticationRequest){
        System.out.println(authenticationRequest.getEmail() + " " +authenticationRequest.getPassword());
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/check_token")
    public boolean verify_RefreshToken(@RequestBody Map<String,Object> data){
        Object role = data.get("role");
        Object refreshToken = data.get("refreshToken");
        if(role == "Admin" || role == "Staff"){
            Author_NhanVien auth = svnv.getByRefreshToken(String.valueOf(refreshToken));
            if(auth.isEmty()){
                return false;
            }
            svnv.delete_author(auth.getId());
            return true;
        }
        if(role == "Customer"){
            Author_KhachHang auth = svkh.getByRefreshToken(String.valueOf(refreshToken));
            if(auth.isEmty()){
                return false;
            }
            svkh.delete_author(auth.getId());
            return true;
        }
        return false;
    }

    @GetMapping("/getByNumberPhone/{number_phone}")
    public Object check_account_by_number_phone(@PathVariable("number_phone") String number_phone){
        if(svnv.findByNumberPhone(number_phone).isPresent()) {
            return svnv.findByNumberPhone(number_phone).get();
        }
        if(svkh.findByNumberPhone(number_phone).isPresent())
        {
            return svkh.findByNumberPhone(number_phone).get();
        }
        return null;
    }
    @GetMapping("/getByEmail/{email}")
    public Object check_account_by_email(@PathVariable("email") String email){
        if(svnv.findByEmail(email).isPresent()){
            return svnv.findByEmail(email).get();
        }
        if(svkh.findByEmail(email).isPresent()){
            return svkh.findByEmail(email).get();
        }
        return null;
    }
}
