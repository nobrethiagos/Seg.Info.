import java.util.*;
import java.io.*;

public class CeaserCipher {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int key;
    boolean mask;
    System.out.print("Deseja encriptar[1] ou decriptar[2] a mensagem: ");
    if(Integer.parseInt(br.readLine()) == 1) {
      mask = true;
    } else {
      mask = false;
    }
    do {
      System.out.print("Informe o valor da chave (0 <= chave <= 26): ");
      key = Integer.parseInt(br.readLine());
    } while(key > 26 || key < 0);
    System.out.print("Informe o texto: ");
    StringBuilder text = new StringBuilder();
    String preText = br.readLine();
    text.append(preText);
    if(mask) {
      for(int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        if(c != ' ') {
          int remain = Character.toLowerCase(c) - 'z' + key;
          if(remain <= 0) {
            text.setCharAt(i, (char) (c + key));
          } else {
            text.setCharAt(i, (char) (Character.isUpperCase(c) ? ('@' + remain) : ('`' + remain)));
          }
        }
      }
    } else {
      for(int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        if(c != ' ') {
          int remain = Character.toLowerCase(c) - 'a' - key;
          if(remain >= 0) {
            text.setCharAt(i, (char) (c - key));
          } else {
            text.setCharAt(i, (char) (Character.isUpperCase(c) ? ('[' + remain) : ('{' + remain)));
          }
        }
      }
    }
    System.out.println("Resultado: " + text);
  }
}
