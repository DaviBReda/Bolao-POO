package Models;
import java.util.ArrayList;

public class Aposta {
    private ArrayList<Integer> numeros;
    private ArrayList<Jogador> jogadores;

    public Aposta(){
            numeros = new ArrayList<Integer>();
            jogadores = new ArrayList<Jogador>();
    }

    public boolean vencedora(ArrayList<Integer> sorteados){
        return sorteados.containsAll(this.numeros);
    }

    public void inserirOrganizador(){
        Jogador organizador = new Jogador();
        organizador.organizador = true;
        jogadores.add(organizador);
    }

    public void inserirJogador(){
        Jogador jogador = new Jogador();
        jogadores.add(jogador);
    }    

    public void listarVencedores(Double premio){
        double dezPorcento = (premio * 0.1);
        double divisaoDoPremio = (premio - dezPorcento) / jogadores.size();

        for (Jogador jogador : jogadores) {
            if(jogador.organizador == true)
                System.out.println("O jogador " + jogador.getNome() + " ganhou " + (dezPorcento + divisaoDoPremio) + " reais.");
            System.out.println("O jogador " + jogador.getNome() + " ganhou " + divisaoDoPremio + " reais.");
        }
    }
}
