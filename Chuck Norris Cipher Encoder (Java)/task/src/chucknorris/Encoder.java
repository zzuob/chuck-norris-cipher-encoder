package chucknorris;

import java.util.Arrays;
import java.util.Objects;

public class Encoder {

    private static String encodeDigit(String digit, int count) {
        // convert a repeated binary digit into it's encoded representation
        // 1111 -> "1", 4 -> "0 0000"
        String[] seriesValue = new String[]{"00", "0"};
        StringBuilder translated = new StringBuilder();
        int index = Integer.parseInt(digit); // 0 -> 00, 1 -> 0
        translated.append(seriesValue[index]).append(" ");
        for (int i = 0; i < count; i++) {
            translated.append("0"); // add count number of zeros
        }
        return translated.toString();
    }

    public static String encodeBinary(String binaryCharacters) {
        // encode a binary string
        String[] binaryArray = binaryCharacters.split("");
        StringBuilder result = new StringBuilder();
        String prev = binaryArray[0];
        int count = 1;
        for (int i = 1; i < binaryArray.length; i++) {
            if (Objects.equals(binaryArray[i], prev)) {
                count++; // count number of repeated digits
            } else {
                result.append(encodeDigit(prev ,count)).append(" ");
                // change to new digit value and restart counting
                prev = binaryArray[i];
                count = 1;
            }
        }
        // loop only updates result when digit changes - repeat for last digit
        result.append(encodeDigit(prev, count));
        return result.toString();
    }

    public static String decodeBinary(String encodedBinary) {
        // convert encoded binary to a binary string
        if ("".equals(encodedBinary)) { return ""; }
        String[] encodedArray = encodedBinary.split("\\s");
        StringBuilder binary = new StringBuilder();
        String digit = null;
        for (int i = 0; i < encodedArray.length; i++) {
            if (i % 2 == 0) { // first part -> either "00" or "0"
                digit = encodedArray[i];
            } else {
                String binaryDigit = switch(digit) {
                    case "00" -> "0";
                    case "0" -> "1";
                    default -> throw new IllegalArgumentException("encoded statement is not formatted correctly");
                };
                int repeat = encodedArray[i].length(); // series length = length of second part
                binary.append(binaryDigit.repeat(repeat));
            }
        }
        return binary.toString();
    }
}
