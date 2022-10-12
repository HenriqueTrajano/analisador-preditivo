import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Escreva o nome do arquivo que deseja ser lido(OBS: Colocar a extensao dele): ");
        String fileName = in.nextLine();

        LeitorGramatica leitor = new LeitorGramatica();
        LinkedList<SimboloGerador> simbolosGeradores = leitor.leitorArquivo(fileName);


    }
}
