import java.util.*;
import java.io.*;

public class VigenereCipher {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean encrypt;
    System.out.print("Deseja encriptar[1] ou decriptar[2]? ");
    encrypt = br.readLine().equals("1");
    System.out.print("Informe a chave: ");
    String key = br.readLine().toUpperCase().replaceAll("[^A-Za-z]", "");
    System.out.print("Informe o texto: ");
    String text = br.readLine();
    StringBuilder result = new StringBuilder();
    int keyIndex = 0;
    for (int i = 0; i < text.length(); i++) {
      char currentChar = text.charAt(i);
      if (Character.isLetter(currentChar)) {
        boolean isLowerCase = Character.isLowerCase(currentChar);
        char base = isLowerCase ? 'a' : 'A';
        char keyChar = key.charAt(keyIndex % key.length());
        int textNum = currentChar - base;
        int keyNum = Character.toUpperCase(keyChar) - 'A';
        int newCharNum;
        if (encrypt) {
          newCharNum = (textNum + keyNum) % 26;
        } else {
          newCharNum = (textNum - keyNum + 26) % 26;
        }
        char resultChar = (char) (base + newCharNum);
        result.append(resultChar);
        keyIndex++;
      } else {
        result.append(currentChar);
      }
    }
    System.out.println("Resultado: " + result.toString());
  }
}
