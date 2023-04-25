package Models;

import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class Pessoa {
    protected String nome;
    protected String cpf;
    private ArrayList<String> cpfs;
    public Pessoa(){
        cpfs = new ArrayList<String>();
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
