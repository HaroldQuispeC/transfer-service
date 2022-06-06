package com.bootcamp.transferservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    @GetMapping("/")
    public String getSaludar(){

        return "Hola";
    }
}
