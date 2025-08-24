import java.util.*;
import java.io.*;

public class MonoalphaCipher {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean mask;
    System.out.print("Deseja encriptar[1] ou decriptar[2] o texto: ");
    if(Integer.parseInt(br.readLine()) == 1) {
      mask = true;
    } else {
      mask = false;
    }
    Map<Character, Character> dic = new HashMap<>();
    System.out.print("Informe a chave (informe em caixa alta e com espaço entre as letras): ");
    String key = br.readLine();
    int k = 0;
    for(char i = 'A'; i <= 'Z'; i++) {
      char c = key.charAt(k);
      k += 2;
      if(mask) {
        dic.put(i, c);
        dic.put(Character.toLowerCase(i), Character.toLowerCase(c));
      } else {
        dic.put(c, i);
        dic.put(Character.toLowerCase(c), Character.toLowerCase(i));
      }
    }
    StringBuilder text = new StringBuilder();
    System.out.print("Informe o texto: ");
    String preText = br.readLine();
    text.append(preText);
    for(int pos = 0; pos < text.length(); pos++) {
      char c = text.charAt(pos);
      if(c != ' ') {
        text.setCharAt(pos, dic.get(c));
      }
    }
    System.out.println("Resultado: " + text);
  }
}
