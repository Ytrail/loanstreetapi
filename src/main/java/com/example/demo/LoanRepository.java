package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Spring automatically implements Repositories for you with basic CRUD operations Create, Read, Update, & Delete
@Repository //This annotation is optional & is not needed for the above to be true
public interface LoanRepository extends CrudRepository<Loan, Integer> {
    
}
