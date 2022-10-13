import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
  private static LinkedList<SimboloGerador> simbolosGeradores;
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    System.out.print("Escreva o nome do arquivo que deseja ser lido(OBS: Colocar a extensao dele): ");
    String filename = in.nextLine();

    LeitorGramatica leitor = new LeitorGramatica();
    simbolosGeradores = leitor.leitorArquivo(filename);
    setFirsts();
    for (SimboloGerador sg : simbolosGeradores) {
      sg.printFirsts();
      System.out.println();
    }

    HashMap<String, HashSet<Character>> dicFirst = getFirsts();
    for (Map.Entry<String, HashSet<Character>> entry : dicFirst.entrySet()) {
      String key = entry.getKey();
      HashSet<Character> val = entry.getValue();
      System.out.println(key + " -> " + val.toString());
    }
  }

  public static HashMap<String, HashSet<Character>> getFirsts() {
    HashMap<String, HashSet<Character>> dicFirst = new HashMap<>();
    for (SimboloGerador sg : simbolosGeradores) {
      HashSet<Character> firsts = new HashSet<>();
      HashSet<SimboloProducao> producoes = sg.getProducoes();
      for (SimboloProducao sp : producoes) {
        HashSet<Character> aux = sp.getFirsts();
        for (Character first : aux) {
          firsts.add(first);
        }
      }
      dicFirst.put(sg.getNome(), firsts);
    }
    return dicFirst;
  }

  public static void setFirsts() {
    LinkedList<SimboloProducao> aux = new LinkedList<>();
    

      //pega os firsts faceis
      for (SimboloGerador sg : simbolosGeradores) {
        HashSet<SimboloProducao> producoes = sg.getProducoes();
        for (SimboloProducao sp : producoes) {
          String first = sp.getNome().substring(0, 1);
          boolean isTerminal = first.matches("[0-9]|[a-z]|E");
          if (isTerminal) {
            sp.adicionarFirst(first.charAt(0));
            sg.adicionaFirst(first.charAt(0));
          } else {
            aux = new LinkedList<>();
            aux.add(sp);
          }
        }
      }

      // derivar 
      boolean achouAlgo = true;
      while (!aux.isEmpty()) {
        for (SimboloProducao p : aux) {
          for (SimboloGerador s : simbolosGeradores) {
            if (p.getNome().substring(0, 1).equals(s.getNome())) {
              HashSet<SimboloProducao> producoesAux = s.getProducoes();
              for (SimboloProducao auxP : producoesAux) {
                if (!auxP.getFirsts().isEmpty()) {
                  for (Character first : auxP.getFirsts()) {
                    p.adicionarFirst(first);
                  }
                } else {
                  achouAlgo = false;
                }   
              }
              if (achouAlgo) {
                aux.remove(p);
              }
              break;
            }
          }
        }
        
      }
  }
}
