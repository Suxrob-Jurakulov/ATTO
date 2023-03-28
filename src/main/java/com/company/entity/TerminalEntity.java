package com.company.entity;

import com.company.enums.TerminalStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "terminal")
@Data
public class TerminalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String terNum;

    @Column
    private String location;

    @Column
    @Enumerated(EnumType.STRING)
    private TerminalStatus ter_status;
}
