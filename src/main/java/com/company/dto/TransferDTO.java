package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    private CardDTO card;
    private TerminalDTO terminal;

    private Integer id;
    private Integer card_id;
    private Integer ter_id;
    private Timestamp time;
    private double amount;

}
