package org.promo.data.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promo")
public class promoController {

    @GetMapping("/eligible")
    public ResponseEntity<String> eligibleApi(@RequestParam("msisdn") String msisdn, @RequestParam("promoId") Integer promoId){

        return null;
    }
}
