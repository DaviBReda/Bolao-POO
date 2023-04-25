package Models;
import java.util.ArrayList;
import java.util.Scanner;

public class Bolao {
    private ArrayList<Aposta> apostas;
    private ArrayList<Jogador> jogadores;

    public Bolao(){
        apostas = new ArrayList<Aposta>();
        jogadores = new ArrayList<Jogador>();
    }

    public static void startaPrograma(int opcao){
        Bolao bolao = new Bolao();
        while(opcao != 4){
            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Cadastrar jogador");
            System.out.println("2 - Cadastrar aposta");
            System.out.println("3 - Inserir sorteio");
            System.out.println("4: - Sair");
            Scanner sc = new Scanner(System.in);
            opcao = sc.nextInt();
            switch(opcao){
                case 1:
                    bolao.cadastrarJogador();
                    break;
                case 2:
                    System.out.println("");

                    bolao.cadastrarAposta();
                    break;
                case 3:
                    bolao.inserirSorteio();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
            }
        }
    }

    //Jogador do bolão. Vários jogadores podem fazer bolões.
    public void cadastrarJogador(){
        Jogador jogador = new Jogador();
        jogadores.add(jogador);
    }

    public void cadastrarAposta(){
        Aposta aposta = new Aposta();
        aposta.inserirOrganizador();
        aposta.inserirJogador();
        apostas.add(aposta);
    }

    private ArrayList<Aposta> vencedoras (ArrayList<Integer> sorteados){
        ArrayList<Aposta> apostasVencedoras = new ArrayList<Aposta>();
        for (Aposta aposta : apostas) {
            if(aposta.vencedora(sorteados))
                apostasVencedoras.add(aposta);
        }
        return apostasVencedoras;
    }

    public void inserirSorteio(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> sorteados = new ArrayList<Integer>();
        double premioTotal = 0.0;

        for(int i = 0; i < 7; i++){
            System.out.println("Digite o " + (i+1) + "º número sorteado: ");
            sorteados.add(sc.nextInt()); //Adiciona o número sorteado no array de números sorteados.
        }

        System.out.println("Digite o valor do prêmio: ");
        premioTotal = sc.nextDouble();
        
        ArrayList<Aposta> apostasVencedoras = vencedoras(sorteados);
        double valorDoPremioDividido = premioTotal / apostasVencedoras.size();

        for (Aposta aposta : apostasVencedoras) {
            aposta.listarVencedores(valorDoPremioDividido);
        }
    }
}
