package com.example.demo.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class PaymentDTO implements Serializable {

    private String status;
    private String message;
    private String URL;
}
