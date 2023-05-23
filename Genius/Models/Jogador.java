package Genius.Models;
import java.io.*;

public class Jogador {
    private String nome;
    private int pontos = 0;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontos = 0;
    }
 
    public Jogador (BufferedReader b) {
        try {
           this.nome = b.readLine();
           this.pontos = Integer.parseInt(b.readLine()); 
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarRecorde(int p){
        if (p > this.pontos)
            this.pontos = p;
    }
    
    public void salvarArq (BufferedWriter b) throws IOException {
        b.write(this.nome + "\n");
        b.write(this.pontos + "\n");
    }
    
    public void atualizarNome(String novoNome){
        this.nome = novoNome;
    }
    
    public boolean comparaNome(String N){
        return (this.nome.equals(N));
    }
    
    public String retornaNome(){
        return this.nome;
    }


    public int getPontos(){
        return pontos;
    }

    public boolean comparaPontos(int pontos){
        if(this.pontos > pontos)
            return true;
        return false;
    }

    public void setPontos(int pontos){
        this.pontos = pontos;
    }
}