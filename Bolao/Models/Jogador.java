package Models;

import java.util.Scanner;

public class Jogador extends Pessoa
{
    protected String pix;
    protected Double saldo = 0.0;

    public Jogador()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o pix do jogador: ");
        this.pix = sc.nextLine();
    }

    public void listarDados()
    {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("PIX: " + this.pix);
    }

    protected String getNome()
    {
        return this.nome;
    }

    protected Double getSaldo()
    {
        return this.saldo;
    }    

    protected void setSaldo(Double value)
    {
        this.saldo = value;
    }

    protected String getCpf()
    {
        return this.cpf;
    }
}
