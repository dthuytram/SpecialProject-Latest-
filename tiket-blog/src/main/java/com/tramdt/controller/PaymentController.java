package com.tramdt.controller;

import com.tramdt.dto.PayInfo;
import com.tramdt.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class PaymentController {
    final
    PayService payService;

    public PaymentController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping("/createPayment")
    public String pay(@RequestBody PayInfo payInfo, HttpServletRequest request){
        try {
            return payService.payWithVNPAY(payInfo , request);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}