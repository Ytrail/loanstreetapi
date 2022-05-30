package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import lombok.Data; //Auto creates getters/setters/param contructor/toSTring/ and equals method
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor; //Auto creatures the all args contstructor, since VSCode EXT doesn't with @Data...
import javax.validation.constraints.*;

@Entity //Allows Hibernate to create a table for this JPA (Java Persistence API) entity model
@NoArgsConstructor
@AllArgsConstructor
public @Data class Loan { //The DTO (data transfer object)
    @Id //tags this member as the id for this entity
    @GeneratedValue(strategy=GenerationType.AUTO) //generate the ID automatically
    private Integer id;

    @NotNull(message = "Loan interest rate is required.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Loan interest rate must be positive.") //inclusive means 0.00 can be included as valid
    @Digits(integer=19, fraction=2, message= "Loan interest rate must be at most 19 digits long with at most 2 decimal places.")
    private BigDecimal interestRate; //BigDecimal Defaults to DECIMAL(19,2)

    @NotNull(message = "Loan paid amount is required.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Loan paid amount must be positive.")
    @Digits(integer=19, fraction=2, message= "Loan paid amount must be at most 19 digits long with at most 2 decimal places.")
    private BigDecimal paidAmt;

    @NotNull(message = "Loan month length is required.")
    @Min(value=0, message= "Loan month length must be positive.")
    @Max(value=Integer.MAX_VALUE, message= "Loan month length is greater than maximum allowable months.")
    private Integer monthsLength;

    @NotNull(message = "Loan monthly payment is required.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Loan monthly payment must be positive.")
    @Digits(integer=19, fraction=2, message= "Loan monthly payment must be at most 19 digits long with at most 2 decimal places.")
    private BigDecimal monthPayAmt;

    //a simple static factory to update an loans’s members.
    public Loan updateWith(Loan loan) {
        return new Loan(
            getId(), //preserving its id. It favors immutability, making the code safer
            loan.getInterestRate(),
            loan.getPaidAmt(),
            loan.getMonthsLength(),
            loan.getMonthPayAmt());
    }
}
