import java.util.HashSet;

public class SimboloProducao {

    private String nome;
    private SimboloGerador simboloGerador;
    private HashSet<Character> firsts;

    public SimboloProducao(String nome, SimboloGerador simboloGerador) {
        this.nome = nome;
        this.simboloGerador = simboloGerador;
        firsts = new HashSet<>();
    }

    public String getNome() {
        return this.nome;
    }

    public SimboloGerador getSimboloGerador() {
        return this.simboloGerador;
    }

    public HashSet<Character> getFirsts() {
        return this.firsts;
    }

    public void adicionarFirst(char naoTerminal) {
        this.firsts.add(naoTerminal);
    }
}
