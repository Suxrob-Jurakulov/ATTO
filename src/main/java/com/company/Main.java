package com.company;

import com.company.config.Config;
import com.company.controller.MainController;
import com.company.entity.CardEntity;
import com.company.repository.CardRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context =new ClassPathXmlApplicationContext("spring-config.xml");
        ApplicationContext context =new AnnotationConfigApplicationContext(Config.class);

        MainController mainController = (MainController) context.getBean("mainController");
        mainController.start();

//        CardRepository cardRepository = (CardRepository) context.getBean("cardRepository");
//        cardRepository.saveCard(new CardEntity());
    }
}
