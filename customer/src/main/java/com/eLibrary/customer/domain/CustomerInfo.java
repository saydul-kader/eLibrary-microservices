package com.eLibrary.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_info")
public class CustomerInfo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-binary")
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "address")
    private String address;

    @Column(name = "is_verified")
    private boolean isVerified;

    public CustomerInfo(String fullName, String userName, String email, String contactNo, String address, boolean isVerified) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.isVerified = isVerified;
    }
}

