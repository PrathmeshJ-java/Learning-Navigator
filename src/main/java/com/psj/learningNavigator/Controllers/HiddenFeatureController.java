package com.psj.learningNavigator.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hidden-feature")
public class HiddenFeatureController {

    @GetMapping("/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        String fact = fetchNumberFact(number);
        return ResponseEntity.ok(fact);
    }

    private String fetchNumberFact(int number) {
        return "The number " + number + " is an interesting number!";
    }
}
