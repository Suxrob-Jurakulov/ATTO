package com.company.dto;

import com.company.enums.TerminalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalDTO {
    private Integer id;
    private String terNum;
    private String location;
    private TerminalStatus ter_status;
}
