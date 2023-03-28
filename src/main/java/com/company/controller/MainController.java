package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.service.CardService;
import com.company.service.MainService;
import com.company.service.TransferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Data
@Controller
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private CardService cardService;

    public void showMenu(){
        System.out.println("\n\t*** ATTO ***");
        System.out.println("1. Login");
        System.out.println("2. Transfer");
        System.out.println("3. Pul tashlash");
        System.out.println("0. Exit");
    }

    public void start(){
        while (true) {
            showMenu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.scannerInt.nextInt();

            switch (action) {
                case 1:
                   login();
                    break;
                case 2:
                    transfer();
                    break;
                case 3:
                    pulTashlash();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Mazgi to'g'ri son tanla");
            }
            System.out.println();
        }
    }

    private void pulTashlash() {
        System.out.print("Enter card num: ");
        String cardNum = ComponentContainer.scannerStr.next();
        System.out.print("Enter amount: ");
        double amount = ComponentContainer.scannerInt.nextDouble();

        cardService.fillBalance(cardNum, amount);
    }

    private void transfer() {
        System.out.print("Enter card num: ");
        String cardNum = ComponentContainer.scannerStr.next();

        System.out.print("Enter terminal num: ");
        String terNum = ComponentContainer.scannerStr.next();

        mainService.transfer(cardNum, terNum);
    }

    public void login(){
        System.out.print("Enter card num: ");
        String num = ComponentContainer.scannerStr.next();

        System.out.print("Enter password: ");
        String password = ComponentContainer.scannerStr.next();

        mainService.log(num, password);
    }
}
