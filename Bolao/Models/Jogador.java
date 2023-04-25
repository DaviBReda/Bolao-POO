package Models;

import java.util.Scanner;

public class Jogador extends Pessoa {
    protected String pix;
    protected boolean organizador = false;

    public Jogador(){
        Scanner sc = new Scanner(System.in);
        if(organizador == true){
            System.out.println("Digite a chave pix do organizador: ");
            this.pix = sc.nextLine(); 
        }
        System.out.println("Digite a chave pix do jogador: ");
        this.pix = sc.nextLine(); 
    }

    public void listarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("PIX: " + this.pix);
    }

    public String getNome(){
        return this.nome;
    }
}
