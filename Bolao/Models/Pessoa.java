package Models;

import java.util.Scanner;

public class Pessoa {
    protected String nome;
    protected String cpf;
    
    public Pessoa(){

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite o nome da pessoa: ");
        this.nome = sc.nextLine();
        System.out.println("Digite o cpf da pessoa: : ");
        this.cpf = sc.nextLine();

    }

    public void listarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
    }
}
