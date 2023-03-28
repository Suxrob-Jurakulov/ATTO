package com.company.container;

import com.company.dto.CardDTO;
import com.company.dto.TerminalDTO;

import java.util.Scanner;

public class ComponentContainer {
    public static Scanner scannerInt = new Scanner(System.in);
    public static Scanner scannerStr = new Scanner(System.in);
    public static CardDTO currentCard = null;
    public static TerminalDTO currentTerminal = null;
}
