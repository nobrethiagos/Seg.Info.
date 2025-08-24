import java.util.*;
import java.io.*;

class ParInteiros {
  int primeiro;
  int segundo;

  public ParInteiros(int primeiro, int segundo) {
    this.primeiro = primeiro;
    this.segundo = segundo;
  }

  @Override
  public String toString() {
    return "(" + primeiro + ", " + segundo + ")";
  }
}

public class PlayfairCipher {
  private static char[][] matrix = new char[5][5];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean mask;
    System.out.print("Deseja encriptar[1] ou decriptar[2] o texto? ");
    if(br.readLine().equals("1")) {
      mask = true;
    } else {
      mask = false;
    }
    System.out.print("Informe a chave: ");
    String key = br.readLine().toUpperCase().replaceAll("J", "I");
    int pos = 0;
    char c = 'A';
    Map<Character, ParInteiros> charPos = new HashMap<>();
    for(int i = 0; i < 5; i++) {
      for(int j = 0; j < 5; j++) {
        if(pos < key.length()) {
          char currentChar = key.charAt(pos);
          if(currentChar == 'J') currentChar = 'I';
          if(!temChar(currentChar)) {
            matrix[i][j] = currentChar;
            charPos.put(currentChar, new ParInteiros(i, j));
            pos++;
          } else {
            pos++;
            j--;
          }
        } else {
          while(true) {
            if(c == 'J') {
              c++;
              continue;
            }
            if(!temChar(c)) {
              matrix[i][j] = c;
              charPos.put(c, new ParInteiros(i, j));
              c++;
              break;
            }
            c++;
          }
        }
      }
    }
    System.out.println("Matriz Playfair:");
    for(int i = 0; i < 5; i++) {
      for(int j = 0; j < 5; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
    System.out.print("Informe o texto: ");
    String preText = br.readLine().toUpperCase().replaceAll("J", "I");
    StringBuilder text = new StringBuilder();
    text.append(preText.replaceAll("\\s+", ""));
    for(int i = 0; i < text.length() - 1; i += 2) {
      if(text.charAt(i) == text.charAt(i + 1)) {
        text.insert(i + 1, 'Q');
      }
    }
    if(text.length() % 2 != 0) {
      text.append('Q');
    }
    System.out.println("Texto processado: " + text.toString());
    StringBuilder resultado = new StringBuilder();
    if(mask) {
      for(int i = 0; i < text.length(); i += 2) {
        char char1 = text.charAt(i);
        char char2 = text.charAt(i + 1);
        ParInteiros pos1 = charPos.get(char1);
        ParInteiros pos2 = charPos.get(char2);
        if(pos1.primeiro == pos2.primeiro) {
          resultado.append(matrix[pos1.primeiro][(pos1.segundo + 1) % 5]);
          resultado.append(matrix[pos2.primeiro][(pos2.segundo + 1) % 5]);
        } else if(pos1.segundo == pos2.segundo) {
          resultado.append(matrix[(pos1.primeiro + 1) % 5][pos1.segundo]);
          resultado.append(matrix[(pos2.primeiro + 1) % 5][pos2.segundo]);
        } else {
          resultado.append(matrix[pos1.primeiro][pos2.segundo]);
          resultado.append(matrix[pos2.primeiro][pos1.segundo]);
        }
      }
      System.out.println("Texto encriptado: " + resultado.toString());
    } else {
      for(int i = 0; i < text.length(); i += 2) {
        char char1 = text.charAt(i);
        char char2 = text.charAt(i + 1);
        ParInteiros pos1 = charPos.get(char1);
        ParInteiros pos2 = charPos.get(char2);
        if(pos1.primeiro == pos2.primeiro) {
          resultado.append(matrix[pos1.primeiro][(pos1.segundo + 4) % 5]);
          resultado.append(matrix[pos2.primeiro][(pos2.segundo + 4) % 5]);
        } else if(pos1.segundo == pos2.segundo) {
          resultado.append(matrix[(pos1.primeiro + 4) % 5][pos1.segundo]);
          resultado.append(matrix[(pos2.primeiro + 4) % 5][pos2.segundo]);
        } else {
          resultado.append(matrix[pos1.primeiro][pos2.segundo]);
          resultado.append(matrix[pos2.primeiro][pos1.segundo]);
        }
      }
      System.out.println("Texto decriptado: " + resultado.toString());
    }
  }

  private static boolean temChar(char c) {
    for(int i = 0; i < 5; i++) {
      for(int j = 0; j < 5; j++) {
        if(matrix[i][j] == c) {
          return true;
        }
      }
    }
    return false;
  }
    
  public static List<Character> encontrarChavesPorValoresExatos(HashMap<Character, ParInteiros> mapa, int primeiro, int segundo) {
    List<Character> chaves = new ArrayList<>();
      for(Map.Entry<Character, ParInteiros> entry : mapa.entrySet()) {
        ParInteiros valor = entry.getValue();
        if(valor.primeiro == primeiro && valor.segundo == segundo) {
          chaves.add(entry.getKey());
        }
      }
    return chaves;
  }
}
