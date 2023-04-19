package chucknorris;

import java.util.Objects;
import java.util.Scanner;

public class Binary {

    public static String getInput(String message) {
        System.out.println(message);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static String convertCharacter(String symbol) {
        // convert a character to its ASCII value in binary form
        if (symbol == null) { return ""; }
        else if (symbol.length() != 1) { return ""; }
        int valueInASCII = symbol.charAt(0); // string -> char -> int value of char
        String binaryString = Integer.toBinaryString(valueInASCII);
        String result;
        if (binaryString.length() != 7) {
            StringBuilder missing = new StringBuilder();
            for (int i = 0; i < 7 - binaryString.length(); i++) {
                missing.append("0"); // add any missing zeroes
            }
            result = missing + binaryString;
        } else { result = binaryString; }
        return result;
    }

    public static String encodeInput() {
        String text = getInput("Input string:");
        String[] symbols = text.split("");
        StringBuilder binaries = new StringBuilder();
        for (String symbol: symbols) {
            if (!Objects.equals(symbol, "")) {
                String binaryString = convertCharacter(symbol);
                binaries.append(binaryString);
            }
        }
        return String.format("Encoded string:\n%s", Encoder.encodeBinary(binaries.toString()));
    }

    public static String convertBinary(String sevenBits) {
        // convert a 7 bit ASCII value into it's corresponding character
        if (sevenBits == null) { return ""; }
        else if (sevenBits.length() != 7) { throw new IllegalArgumentException("character must be 7 bits wide"); }
        int valueInASCII = Integer.parseInt(sevenBits, 2);
        char character = (char) valueInASCII;
        return String.valueOf(character);
    }

    public static String decodeInput() {
        String sequence = getInput("Input encoded string:");
        String binary = Encoder.decodeBinary(sequence);
        if (binary.length() % 7 != 0) {
            throw new IllegalArgumentException("incomplete character(s), must be 7 bits wide");
        }
        StringBuilder result = new StringBuilder();
        int c = 0;
        while (c < binary.length()) { // process each 7 bits of binary into it's corresponding character
            String sevenBits = binary.substring(c, c + 7);
            String character = convertBinary(sevenBits);
            result.append(character); // add to result
            c += 7; // next character
        }
        return String.format("Decoded string:\n%s", result);
    }

}
