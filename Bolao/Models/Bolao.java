package Models;
import java.util.ArrayList;
import java.util.Scanner;

public class Bolao
{
    private ArrayList<Aposta> apostas;
    private ArrayList<Jogador> jogadores;

    public Bolao()
    {
        apostas = new ArrayList<Aposta>();
        jogadores = new ArrayList<Jogador>();
    }

    //Jogador do bolão. Vários jogadores podem fazer bolões.
    public void cadastrarJogador()
    {
        Jogador jogador = new Jogador();
        jogadores.add(jogador);
    }

    public void cadastrarAposta()
    {
        
        if(jogadores.size() == 0) //-1 porque o organizador já está na lista e não pode ser considerado um jogador solo.
        {
            System.out.println("Não há jogadores cadastrados no bolão!");
            return;
        }

        System.out.println("Lembre-se que esse é o organizador!");
        Aposta aposta = new Aposta();
        Jogador organizador = aposta.inserirOrganizador();
        jogadores.add(organizador);

        if(aposta.inserirJogadores(jogadores) == null){
            System.out.println("Não é possível inserir mais jogadores do que jogadores existentes. Por favor, cadastre mais jogadores.");
            jogadores.remove(organizador);
            return;
        }

        aposta.inserirJogadores(jogadores);
        aposta.inserirNumeros();
        apostas.add(aposta);
    }

    private ArrayList<Aposta> vencedoras (ArrayList<Integer> sorteados)
    {
        ArrayList<Aposta> apostasVencedoras = new ArrayList<Aposta>();
        for (Aposta aposta : apostas) 
        {
            if(aposta.vencedora(sorteados))
                apostasVencedoras.add(aposta);
        }
        return apostasVencedoras;
    }

    public void inserirSorteio()
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> sorteados = new ArrayList<Integer>();
        double premioTotal = 0.0;

        while(sorteados.size() < 6)
        {
            System.out.println("Digite o " + (sorteados.size()+1) + "º número: ");
            int numero = sc.nextInt();
            if(numero > 0 && numero < 61)
            {
                if(!sorteados.contains(numero))
                    sorteados.add(numero);
                else
                    System.out.println("Número já inserido!");
            }
            else
                System.out.println("Numero inválido! Insira um número entre 1 e 60.");          
        }

        System.out.println("Digite o valor do prêmio: ");
        premioTotal = sc.nextDouble();
        
        ArrayList<Aposta> apostasVencedoras = vencedoras(sorteados);
        double valorDoPremioDividido = premioTotal / apostasVencedoras.size();

        int contadorDeApostas = 1;

        if(!apostasVencedoras.isEmpty())
        {
            for (Aposta aposta : apostasVencedoras) 
            {
                System.out.println("-=-=-=-=-=-=-=-=-=-= " + contadorDeApostas + "ª Aposta Vencedora-=-=-=-=-=-=-=-=-=-=");
                aposta.listarVencedores(valorDoPremioDividido);
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                contadorDeApostas++;
            }
        }
        else
            System.out.println("Não é possível dividir o prêmio, pois não há apostas vencedoras.");
        
    }
}
