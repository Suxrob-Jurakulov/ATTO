package com.company.service;

import com.company.container.ComponentContainer;
import com.company.entity.CardEntity;
import com.company.enums.CardStatus;
import com.company.dto.CardDTO;
import com.company.repository.CardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public void addCard(CardDTO card) {
        if (!validation(card)) {
            System.out.println("Mazgi. Card num kamida 4 xonali bolishi kerak");
            return;
        }

        if (cardRepository.isExistCard(card.getNumber())) {
            System.out.println("Already exist");
        } else {
            CardEntity entity = new CardEntity();
            entity.setId(card.getId());
            entity.setNumber(card.getNumber());
            entity.setBalance(card.getBalance());
            entity.setCardStatus(card.getCardStatus());

            cardRepository.saveCard(entity);
            System.out.println("Success");
        }

    }

    public boolean validation(CardDTO card) {
        return card.getNumber().length() >= 4;
    }

    public void cardList() {
        List<CardEntity> cardList = cardRepository.selectAllCard();
        int index = 1;
        for (CardEntity card : cardList) {
            System.out.println(index + ". " + card);
            index++;
        }
    }

    public void changeStatusCard(String num) {
        CardEntity card = cardRepository.getCardByNum(num);
        if (card == null) {
            System.out.println("Kalla no card");
            return;
        }
        if (card.getCardStatus().equals(CardStatus.ACTIVE)) {
            card.setCardStatus(CardStatus.BLOCK);
        } else {
            card.setCardStatus(CardStatus.ACTIVE);
        }
        cardRepository.changeStatus(card.getId(), card.getCardStatus());
        System.out.println("Success");
    }

    public void getCardByNum(String num) {
        CardEntity card = cardRepository.selectCard(num);
        if (card == null){
            System.out.println("Kalla no card! ");
            return;
        }

        System.out.println(card);
    }

    public void adminCard() {
        CardEntity card = cardRepository.showAdminCard();
        System.out.println(card);
    }

    public void fillBalance(String cardNum, double amount) {
        ComponentContainer.currentCard = new CardDTO();
        if (!cardRepository.isExistCard(cardNum)){
            System.out.println("Bunday karta yo'q");
            return;
        }

        cardRepository.fillBalance(cardNum, amount);
        ComponentContainer.currentCard = null;
        System.out.println("Success");
    }
}
