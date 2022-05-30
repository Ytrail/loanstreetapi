package com.example.demo;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //Tags this class as a service provider (indicates it's holding the business logic) for the service layer
@EnableMapRepositories //Tags this class as using repositories for Spring to automatically implement  
public class LoanService {
    private final CrudRepository<Loan, Integer> repository;

    public LoanService(CrudRepository<Loan, Integer> repository) {
        this.repository = repository;
    }

    public Loan createLoan(Loan loan) {
        return repository.save(loan);
    }

    public Optional<Loan> readLoan(Integer id) {
        return repository.findById(id);
    }

    public List<Loan> readAllLoans() {
        Iterable<Loan> loans = repository.findAll(); //gets all the loans from the database & store them in a iterable
        List<Loan> loansList = new ArrayList<>();
        loans.forEach(loansList::add); //iterate to add all the loans from the iterable to a list to be returned
        return loansList;
    }

    public Optional<Loan> updateLoan(Integer id, Loan newLoan) {
        //Optional is for a method return type that needs to represent "no result," b/c using null is likely to cause errors.
        // A variable whose type is Optional should never itself be null; it should always point to an Optional instance.
        // Only update a loan if it can be found first.
        return repository.findById(id).map(oldLoan -> {
            Loan updated = oldLoan.updateWith(newLoan);
            return repository.save(updated);
        });
    }
}
