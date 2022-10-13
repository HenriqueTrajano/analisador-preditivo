import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class SimboloGerador {

  private String nome;
  private boolean simboloInicial;
  private HashSet<SimboloProducao> producoes; /// { {(E)}, { id } }
  private LinkedHashSet<Character> follows;
  private LinkedList<Character> firsts;

  public SimboloGerador(String nome, boolean simboloInicial) {
    this.nome = nome;
    this.simboloInicial = simboloInicial;

    // Visto que so pode ter 10 producoes no maximo
    this.producoes = new HashSet<>(10);
    this.follows = new LinkedHashSet<>();
    this.firsts = new LinkedList<>();
  }

  public String getNome() {
    return this.nome;
  }

  public boolean verificaSimboloInicial() {
    return this.simboloInicial;
  }

  public void adicinarProducoes(SimboloProducao sp) {
    this.producoes.add(sp);
  }

  public HashSet<SimboloProducao> getProducoes() {
    return this.producoes;
  }

  public LinkedList<Character> getFirsts() {
    return this.firsts;
  }

  public boolean contemSimboloNaoTerminal(String SimboloNaoTerminal) {
    for (SimboloProducao sp : producoes) {
      if (sp.getNome().contains(SimboloNaoTerminal)) {
        return true;
      }
    }
    return false;
  }

  public void adicionarFollow(char follow) {
    this.follows.add(follow);
  }

  public void adicionaFirst(char first) {
    this.firsts.add(first);
  }

  public void printFirsts() {
    this.firsts.stream().forEach(System.out::print);
  }

  public LinkedHashSet<Character> getFollows() {
    return this.follows;
  }
}
