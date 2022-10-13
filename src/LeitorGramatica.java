import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LeitorGramatica {

  private LinkedList<SimboloGerador> geradores;

  public LeitorGramatica() {
    this.geradores = new LinkedList<>();
  }

  public LinkedList<SimboloGerador> leitorArquivo(String filename) {
    String[] auxReader;
    String[] auxReaderProd;
    boolean first = true;
    SimboloGerador auxSG;
    SimboloProducao auxSP;

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

      String line = null;
      while ((line = br.readLine()) != null) {
        auxReader = line.split("-->");

        // Pega o simbolo inicial
        if (first) {
          auxSG = new SimboloGerador(auxReader[0].trim(), true);
          auxSG.adicionarFollow('$');
          first = false;
        } else {
          auxSG = new SimboloGerador(auxReader[0].trim(), false);
        }

        // Se tiver mais de uma producao
        if (auxReader[1].contains("|")) {
          auxReaderProd = auxReader[1].split("\\|");
        } else {
          auxReaderProd = new String[1];
          auxReaderProd[0] = auxReader[1].trim();
        }

        // Coloca as producoes no simbolo gerador
        for (int i = 0; i < auxReaderProd.length; i++)
          auxSG.adicinarProducoes(new SimboloProducao(auxReaderProd[i].trim(), auxSG));
      
        this.geradores.add(auxSG);
      }
    } catch (IOException e1) {
      System.err.print("Erro na leitura do arquivo " + e1 + "\n");
    } catch (Exception e2) {
      System.err.print("Erro " + e2 + "\n");
    }
    return geradores;
  }
}
