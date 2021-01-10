package core.basesyntax;

import core.basesyntax.controller.ConsoleHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter value and risk");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.handle();
    }
}
