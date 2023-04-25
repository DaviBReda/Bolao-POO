package Models;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Aposta {
    private ArrayList<Integer> numeros;
    private ArrayList<Jogador> jogadores;
    private Jogador organizador;

    public Aposta()
    {
        numeros = new ArrayList<Integer>();
        jogadores = new ArrayList<Jogador>();
    }

    public boolean vencedora(ArrayList<Integer> sorteados)
    {
        return sorteados.containsAll(this.numeros);
    }

    public Jogador inserirOrganizador()
    {
        organizador = new Jogador();
        jogadores.add(organizador);
        return organizador;
    }

    public void listarJogadoresDoBolao(ArrayList<Jogador> jogadoresDoBolao, ArrayList<Jogador> jogadoresDaAposta)
    {
        for (Jogador jogador : jogadoresDoBolao)
        {
            if(!jogadoresDaAposta.contains(jogador))
                jogador.listarDados();
        }
    }

    public void inserirNumeros()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a quantidade de números que deseja apostar (no mínimo 6): ");
        int quantidade = sc.nextInt();
        int count = 0;
        int numero = 0;

        while(quantidade < 6)
        {
            System.out.println("Quantidade inválida! Digite novamente: ");
            quantidade = sc.nextInt();
        }

        while(quantidade > count)
        {
            System.out.println("Digite o " + (count+1) + "º número: ");
            numero = sc.nextInt();
            if(numero > 0 && numero < 61)
            {
                if(!numeros.contains(numero))
                {
                    numeros.add(numero);
                    count++;
                }
                else
                    System.out.println("Número já inserido!");
            }
            else
                System.out.println("Número inválido!");
        }
    }

    public ArrayList<Jogador> inserirJogadores(ArrayList<Jogador> jogadoresDoBolao)
    {
        listarJogadoresDoBolao(jogadoresDoBolao, jogadores);

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a quantidade de jogadores que deseja adicionar: ");
        int quantidade = sc.nextInt();
        
        if(quantidade > (jogadoresDoBolao.size() - jogadores.size())){
            return null;
        }

        for(int i = 1; i <= quantidade; i++)
        {
            System.out.println("Digite o CPF do jogador número " + i + ": " );
            String cpf = sc.next();
            Jogador jogador = getJogadorPeloCpf(cpf, jogadoresDoBolao);
            
            if(jogador != null)
                jogadores.add(jogador);
        }

        return jogadores;
    }

    private Jogador getJogadorPeloCpf(String cpf, ArrayList<Jogador> jogadores)
    {
        for (Jogador jogador : jogadores) 
        {
            if(jogador.getCpf().equals(cpf))
                return jogador;
        }
        return null;
    }
    
    private void dividirPremio(Double premioDividido, double dezPorcentoOrganizador)
    {
        for(Jogador jogador : jogadores)
        {
            if(!organizador.cpf.equals(jogador.cpf))
                jogador.setSaldo(jogador.getSaldo() + premioDividido);
        }
    }

    public void listarVencedores(Double premio)
    {
        double dezPorcento = (premio * 0.1);
        double divisaoDoPremio = (premio - dezPorcento) / (jogadores.size() - 1) ;
        organizador.setSaldo(dezPorcento);
        dividirPremio(divisaoDoPremio, dezPorcento);
        for (Jogador jogador : jogadores) 
        {
            jogador.listarDados();
            System.out.println("Valor do prêmio recebido para o jogador " + jogador.getNome()+ ": " + jogador.getSaldo());
        }
    }
}
