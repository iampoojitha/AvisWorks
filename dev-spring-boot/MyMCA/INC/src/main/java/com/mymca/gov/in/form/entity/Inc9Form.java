package com.mymca.gov.in.form.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "inc9_form")
public class Inc9Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2,max = 100,message = "Company name should be between 2 and 100 characters.")
    private String companyName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]{10}$", message = "PAN must be a valid 10-character PAN number.")
    private String pan;

    @NotNull
    @Size(min = 2,max = 50, message = "The nationality should be between 2 and 50 characters.")
    private String nationality;

    @Email(message = "Email should be valid.")
    private String email;

    @NotNull
    @Size(min = 10, max = 250, message = "The address should be between 10 and 250 characters.")
    private String address;

    @NotNull
    @Size(min = 0, message = "Number of Shareholders should be at least 1")
    private Integer numShareholders;

    @NotNull
    private String legalForm;
}
