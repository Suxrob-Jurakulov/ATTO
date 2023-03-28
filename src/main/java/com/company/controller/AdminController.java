package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.dto.CardDTO;
import com.company.dto.TerminalDTO;
import com.company.repository.CardRepository;
import com.company.service.CardService;
import com.company.service.TerminalService;
import com.company.service.TransferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Data
@Controller
public class AdminController {
    @Autowired
    private CardService cardService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private CardRepository cardRepository;

    public void showMenu() {
        System.out.println("\n\t*** Admin Menu ***");
        System.out.println("1. Card add");
        System.out.println("2. Card list");
        System.out.println("3. Change card status");
        System.out.println("4. Card info");
        System.out.println("5. Add terminal");
        System.out.println("6. Terminal list");
        System.out.println("7. Admin card");
        System.out.println("8. Transfer list");
        System.out.println("9. Transfer list by card");
        System.out.println("10. Transfer list by terminal");
        System.out.println("0. Exit");
    }

    public void adminMenu() {
        while (true) {
            showMenu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.scannerInt.nextInt();

            switch (action) {
                case 1:
                    addCard();
                    break;
                case 2:
                    cardList();
                    break;
                case 3:
                    changeCardStatus();
                    break;
                case 4:
                    cardInfo();
                    break;
                case 5:
                    addTerminal();
                    break;
                case 6:
                    terminalList();
                    break;
                case 7:
                    adminCard();
                    break;
                case 8:
                    transferList();
                    break;
                case 9:
                    transferListByCard();
                    break;
                case 10:
                    transferListByTerminal();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Mazgi to'g'ri son tanla");
            }
        }
    }

    private void transferListByTerminal() {
        System.out.print("Enter terminal num:");
        String terNum = ComponentContainer.scannerStr.next();

        terminalService.getHistoryByTerminal(terNum);
    }

    private void transferListByCard() {
        System.out.print("Enter card num: ");
        String cardNum = ComponentContainer.scannerStr.next();

        transferService.getHistoryByCard(cardNum);
    }

    private void transferList() {
        transferService.transferList();
    }

    private void adminCard() {
        cardService.adminCard();
    }

    private void terminalList() {
        terminalService.showAllTerminal();
    }

    private void addTerminal() {
        System.out.print("Enter terminal num: ");
        String terNum = ComponentContainer.scannerStr.next();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        TerminalDTO terminal = new TerminalDTO();
        terminal.setTerNum(terNum);
        terminal.setLocation(location);
        terminalService.addTerminal(terminal);

    }

    private void cardInfo() {
        System.out.print("Enter card num: ");
        String num = ComponentContainer.scannerStr.next();

        cardService.getCardByNum(num);

    }

    public void addCard() {
        System.out.print("Enter card num: ");
        String num = ComponentContainer.scannerStr.next();

        System.out.print("Enter card balance: ");
        double balance = ComponentContainer.scannerInt.nextDouble();

        CardDTO card = new CardDTO();
        card.setNumber(num);
        card.setBalance(balance);
        cardService.addCard(card);
    }

    public void cardList() {
        cardService.cardList();
    }

    private void changeCardStatus() {
        System.out.print("Enter card num: ");
        String num = ComponentContainer.scannerStr.next();

        cardService.changeStatusCard(num);
    }
}
