// Create a program to collect and store all the cards to assist the users in finding all the cards in a given symbol using Collection interface.

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollection {
    private Collection<Card> cards = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addCard() {
        System.out.print("Enter card symbol (e.g., Hearts, Diamonds): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter card value (e.g., Ace, 2, King): ");
        String value = scanner.nextLine();
        cards.add(new Card(symbol, value));
        System.out.println("Card added.");
    }

    public void findCardsBySymbol() {
        System.out.print("Enter symbol to search for: ");
        String symbol = scanner.nextLine();
        boolean found = false;
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for the symbol: " + symbol);
        }
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards to display.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCard();
                case 2 -> findCardsBySymbol();
                case 3 -> displayAllCards();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        new CardCollection().menu();
    }
}
