package chucknorris;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String choice = "";
        while (!"exit".equals(choice)) {
            try {
                choice = Binary.getInput("Please input operation (encode/decode/exit):").trim().toLowerCase();
                String response = switch (choice) {
                    case "encode" -> Binary.encodeInput();
                    case "decode" -> Binary.decodeInput();
                    case "exit" -> "Bye!";
                    default -> String.format("There is no '%s' operation", choice);
                };
                System.out.println(response);
            } catch (Exception e) { // encode/decode operation isn't valid for the given input
                System.out.format("Not valid: %s\n", e.getMessage());
            }
        }
    }
}