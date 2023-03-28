package com.company.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transfer")
@Data
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer card_id;
    @Column
    private Integer ter_id;
    @Column
    private Timestamp time;
    @Column
    private double amount;
}
