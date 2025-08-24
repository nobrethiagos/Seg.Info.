import java.util.*;
import java.io.*;

public class KeywordCipher {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Deseja encriptar[1] ou decriptar[2] o texto: ");
    boolean mask;
    if(Integer.parseInt(br.readLine()) == 1) {
      mask = true;
    } else {
      mask = false;
    }
    System.out.print("Informe a chave: ");
    String key = br.readLine().toUpperCase();
    Map<Character, Character> dic = new HashMap<>();
    if(!mask) {
      char cDic = 'A';
      for(int i = 0; i < key.length(); i++) {
        char c = key.charAt(i);
        if(c != ' ' && !(dic.containsValue(c))) {
          dic.put(cDic, c);
          dic.put(Character.toLowerCase(cDic), Character.toLowerCase(c));
          cDic++;
        }
      }
      char cDic2 = 'A';
      for(int i = 0; i < 26; i++) {
        if(!(dic.containsValue(cDic2))) {
          dic.put(cDic, cDic2);
          dic.put(Character.toLowerCase(cDic), Character.toLowerCase(cDic2));
          cDic++;
        }
        cDic2++;
      }
    } else {
      char cDic = 'A';
      for(int i = 0; i < key.length(); i++) {
        char c = key.charAt(i);
        if(c != ' ' && !(dic.containsKey(c))) {
          dic.put(c, cDic);
          dic.put(Character.toLowerCase(c), Character.toLowerCase(cDic));
          cDic++;
        }
      }
      char cDic2 = 'A';
      for(int i = 0; i < 26; i++) {
        if(!(dic.containsKey(cDic2))) {
          dic.put(cDic, cDic2);
          dic.put(Character.toLowerCase(cDic), Character.toLowerCase(cDic2));
          cDic++;
        }
        cDic2++;
      }
    }
    System.out.print("Informe o texto: ");
    String preText = br.readLine();
    StringBuilder text = new StringBuilder();
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
