package com.company.service;

import com.company.container.ComponentContainer;
import com.company.controller.AdminController;
import com.company.dto.CardDTO;
import com.company.dto.TerminalDTO;
import com.company.entity.CardEntity;
import com.company.repository.CardRepository;
import com.company.repository.TerminalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MainService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AdminController adminController;
    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private TransferService transferService;

    public void log(String num, String password) {
        CardEntity entity = cardRepository.log(num, password);
        if (entity == null){
            System.out.println("Card num or password wrong! ");
            return;
        }

        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(entity.getId());
        cardDTO.setNumber(entity.getNumber());
        cardDTO.setBalance(entity.getBalance());
        cardDTO.setCardStatus(entity.getCardStatus());

        ComponentContainer.currentCard = cardDTO;
        adminController.adminMenu();
    }

    public void transfer(String cardNum, String terNum) {
        ComponentContainer.currentCard = new CardDTO();
        ComponentContainer.currentTerminal = new TerminalDTO();

        if (!cardRepository.isExistCard(cardNum)){
            System.out.println("Kalla bunday raqamli karta yo'q!");
            return;
        }

        if (cardRepository.isBlockedCard(cardNum)){
            System.out.println("Kalla. Kartang bloklangan!");
            return;
        }

        if (!terminalRepository.isExistTerminal(terNum)){
            System.out.println("Kalla bunday raqamli terminal yo'q");
            return;
        }

        double balance = cardRepository.checkBalance(cardNum);

        if (balance < 1400){
            System.out.println("Kalla puling tugabdi");
            return;
        }else {

            cardRepository.transfer(cardNum);
            cardRepository.profitByTransfer();
            transferService.history(ComponentContainer.currentCard.getId(), ComponentContainer.currentTerminal.getId());

            System.out.println("Success");
        }
        ComponentContainer.currentCard = null;
        ComponentContainer.currentTerminal = null;
    }
}
