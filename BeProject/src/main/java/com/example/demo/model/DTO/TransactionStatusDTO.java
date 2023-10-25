package com.example.demo.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class TransactionStatusDTO implements Serializable {
    private String status;
    private String message;
    private String amount;
    private String bankCode;
    private String orderInfor;
}
