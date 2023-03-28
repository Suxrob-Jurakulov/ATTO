package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.TerminalDTO;
import com.company.dto.TransferDTO;
import com.company.entity.TerminalEntity;
import com.company.entity.TransferEntity;
import com.company.mapper.TransferListMapper;
import com.company.repository.TerminalRepository;
import com.company.repository.TransferRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private TransferRepository transferRepository;

    public void addTerminal(TerminalDTO terminal) {
        if (!validation(terminal)) {
            System.out.println("Mazgi terminal qiymatlarini tog'ri kirit");
            return;
        }

        if (terminalRepository.isExistTerminal(terminal.getTerNum())) {
            System.out.println("Already exist");
        } else {
            TerminalEntity entity = new TerminalEntity();
            entity.setId(terminal.getId());
            entity.setTerNum(terminal.getTerNum());
            entity.setLocation(terminal.getLocation());
            entity.setTer_status(terminal.getTer_status());

            terminalRepository.saveTerminal(entity);
            System.out.println("Success");
        }

    }

    public boolean validation(TerminalDTO terminal) {
        if (terminal.getTerNum() == null || terminal.getTerNum().length() < 4) {
            return false;
        }
        if (terminal.getLocation() == null || terminal.getLocation().length() < 4) {
            return false;
        }
        return true;
    }

    public void showAllTerminal() {
        List<TerminalEntity> terminalList = terminalRepository.selectAllTerminal();
        int index = 1;
        for (TerminalEntity terminal : terminalList) {
            System.out.println(index + ". " + terminal);
            index++;
        }
    }

    public void getHistoryByTerminal(String terNum) {
        ComponentContainer.currentTerminal = new TerminalDTO();
        if (!terminalRepository.isExistTerminal(terNum)){
            System.out.println("Kalla bunday terminal yo'q");
            return;
        }
        List<TransferListMapper> list = transferRepository.transferListByTerminal(ComponentContainer.currentTerminal.getId());
        int index = 1;
        for (TransferListMapper transfer: list){
            System.out.println(index + ". " + transfer);
            index++;
        }

    }
}
