package com.vendor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    private String id;
    private String name;
    private String address;
    private String phoneNumber;

}
