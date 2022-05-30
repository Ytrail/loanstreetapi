package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("loans") // URL's will begin with loanstreet/demo after application path
public class LoanController {
    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping //denotes the GET loans
    public ResponseEntity<List<Loan>> readAllLoans() {
        List<Loan> loans = service.readAllLoans();
        //A shortcut for creating a ResponseEntity with either a valid body & the 200 OK status, or no body & a 404 Not Found status.
        return ResponseEntity.ok().body(loans);
    }

    @GetMapping("/{id}") //denotes GET loans/id
    public ResponseEntity<Loan> readLoan(@PathVariable("id") Integer id) {
        Optional<Loan> loan = service.readLoan(id);
        return ResponseEntity.of(loan);
    }

    @PostMapping //denotes the POST loans
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody Loan loan) { //@Valid means LoanController expects a valid request body
        Loan createdLoan = service.createLoan(loan);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdLoan.getId()).toUri();
        return ResponseEntity.created(location).body(createdLoan);
    }

    @PutMapping("/{id}") //denotes PUT loans/id
    public ResponseEntity<Loan> update(@PathVariable("id") Integer id, @Valid @RequestBody Loan loan) {
        Optional<Loan> updatedLoan = service.updateLoan(id, loan);
        return updatedLoan.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> {
            Loan createdLoan = service.createLoan(loan);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdLoan.getId()).toUri();
            return ResponseEntity.created(location).body(createdLoan);
        });
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val); 
        });
        return ResponseEntity.badRequest().body(map);
    }

}
