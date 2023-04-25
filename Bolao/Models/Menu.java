package Models;

import java.util.Scanner;

public class Menu {
    
    public static void startaPrograma(Bolao bolao, int opcao)
    {
        while(opcao != 4){
            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Cadastrar jogador");
            System.out.println("2 - Cadastrar aposta");
            System.out.println("3 - Inserir sorteio");
            System.out.println("4: - Sair");
            Scanner sc = new Scanner(System.in);
            opcao = sc.nextInt();
            switch(opcao) 
            {
                case 1:
                    bolao.cadastrarJogador();
                    break;
                case 2:
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
}
