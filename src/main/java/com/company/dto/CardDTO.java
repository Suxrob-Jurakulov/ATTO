package com.company.dto;

import com.company.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Integer id;
    private String number;
    private String password;
    private double balance;
    private CardStatus cardStatus;
}
