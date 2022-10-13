import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class Main {
  private static LinkedList<SimboloGerador> simbolosGeradores;
  private static LinkedList<Character> naoTerminais = new LinkedList<>();
  private static HashMap<String, HashMap<String, String>> table = new HashMap<>();
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    System.out.print("Escreva o nome do arquivo que deseja ser lido(OBS: Colocar a extensao dele): ");
    String filename = in.nextLine();

    LeitorGramatica leitor = new LeitorGramatica();
    simbolosGeradores = leitor.leitorArquivo(filename);
    setFirsts();
    printFirsts();
    printTable();
  }

  public static void printTable() {
    System.out.println("========= Tabela =========");
    HashMap<String, HashSet<Character>> firsts = getFirsts();
    for (Map.Entry<String, HashSet<Character>> entry : firsts.entrySet()) {
      String key = entry.getKey();
      HashSet<Character> val = entry.getValue();
      for (SimboloGerador g : simbolosGeradores) {
        if (g.getNome().equals(key)) {
          String msg = "";
          for (SimboloProducao p : g.getProducoes()) {
            msg = msg + " " + key + " -> " + p.getNome() + ",";
          }
          System.out.println(key + " | " + msg + " | firsts: " + val);
        } 
      }
    }
  }
  

  // private void regraPalavraVazia() {
  //     LinkedList<SimboloProducao> aux = new LinkedList<>();
  //     for (SimboloGerador sg : simbolosGeradores) {
  //       HashSet<SimboloProducao> producoes = sg.getProducoes();
  //       boolean hasPalavraVazia = false;
  //       for (SimboloProducao sp : producoes) {
  //         String first = sp.getNome().substring(0, 1);
  //         boolean isTerminal = first.matches("[0-9]|[a-z]");
  //         if (first.equals("E")) {
  //           hasPalavraVazia = true;
  //           // se houver palavra vazia na producao e o primeiro simbolo for nao terminal aplica a regra
  //         } else if (!isTerminal && hasPalavraVazia) {
  //           aux.add(sp);
  //         } 
  //       }
  //     }
      
  //     for (SimboloProducao p : aux) {
  //       if (p.getNome().length() > 1) {
  //         p.
  //       }
  //     }
  // }

  public static void printFirsts() {
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

  private static boolean derivar(SimboloProducao p) {
    boolean achouAlgo = true;
    for (SimboloGerador s : simbolosGeradores) {
      if (p.getNome().substring(0, 1).equals(s.getNome())) {
        HashSet<SimboloProducao> producoesAux = s.getProducoes();
        for (SimboloProducao auxP : producoesAux) {
          if (!auxP.getFirsts().isEmpty()) { // pode estar errado
            for (Character first : auxP.getFirsts()) {
              p.adicionarFirst(first);
            }
          } else {
            achouAlgo = false;
          }
        }
        break;
      }
    }
    return achouAlgo;
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
            if (!first.equals("E")) {
              naoTerminais.add(first.charAt(0));
            }
          } else {
            aux.add(sp);
          }
        }
      }

      // derivar 
      while (!aux.isEmpty()) {
        for (SimboloProducao p : aux) {
          boolean derivou = derivar(p);
          if (derivou) aux.remove(p);
        }
      }
  }
}
