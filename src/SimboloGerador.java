import java.util.HashSet;
import java.util.LinkedHashSet;

public class SimboloGerador {

    private String nome;
    private boolean simboloInicial;
    private HashSet<SimboloProducao> producoes; /// { {(E)}, { id } }
    private LinkedHashSet<Character> follows;

    public SimboloGerador(String nome, boolean simboloInicial) {
        this.nome = nome;
        this.simboloInicial = simboloInicial;

        //Visto que so pode ter 10 producoes no maximo
        this.producoes = new HashSet<>(10);
        this.follows = new LinkedHashSet<>();
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

    public boolean contemSimboloNaoTerminal(String SimboloNaoTerminal) {
        for(SimboloProducao sp : producoes) {
            if (sp.getNome().contains(SimboloNaoTerminal)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarFollow(char follow) {
        this.follows.add(follow);
    }

    public LinkedHashSet<Character> getFollows() {
        return this.follows;
    }
}
