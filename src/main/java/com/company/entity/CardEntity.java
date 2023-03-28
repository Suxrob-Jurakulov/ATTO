package com.company.entity;

import com.company.enums.CardStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Data
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String number;

    @Column
    private String password;

    @Column
    private double balance;

    @Column
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;
}
