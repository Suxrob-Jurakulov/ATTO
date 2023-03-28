package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.CardDTO;
import com.company.dto.TransferDTO;
import com.company.entity.TransferEntity;
import com.company.repository.CardRepository;
import com.company.repository.TransferRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private CardRepository cardRepository;

    public void history(Integer cardID, Integer terId) {
        transferRepository.writeHistory(cardID, terId);
    }

    public void transferList() {
        List<TransferEntity> transferList = transferRepository.transferList();
        int index = 1;
        for (TransferEntity transfer: transferList){
            System.out.println(index + ". " + transfer);
            index++;
        }
    }

    public void getHistoryByCard(String cardNum) {
        ComponentContainer.currentCard = new CardDTO();
        if (!cardRepository.isExistCard(cardNum)){
            System.out.println("Kalla bunday karta yo'q");
            return;
        }

        List<TransferEntity> list = transferRepository.transferList(ComponentContainer.currentCard.getId());
        int index = 1;
        for (TransferEntity transfer: list){
            System.out.println(index + ". " + transfer);
            index++;
        }
    }
}
