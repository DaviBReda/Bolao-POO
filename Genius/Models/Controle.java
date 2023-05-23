package Genius.Models;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import java.io.*;

public class Controle {
    private ArrayList<Jogador> jogadores;
    private Jogador atual;
    private String correta;
    private ArrayList<Jogador> jogadoresDaSessaoAtual;


    public Controle() {
        this.correta = "";
        this.jogadores = new ArrayList<Jogador>();
        this.jogadoresDaSessaoAtual = new ArrayList<Jogador>();
        this.carregarArq();
        this.bemVindo();
    }
    
    public int Sortear(){
        Random r = new Random();
        int numero = r.nextInt(9) + 1;
        return numero;
    }
    
    public Jogador localizarJogador(String nome){
        for (Jogador jogador : jogadores){
            if (jogador.comparaNome(nome)){
                if(!this.jogadoresDaSessaoAtual.contains(jogador)){
                    Jogador jogadorSessaoAtual = new Jogador(jogador.retornaNome());
                    jogadorSessaoAtual.setPontos(-1);
                    this.jogadoresDaSessaoAtual.add(jogadorSessaoAtual);    
                }
                return jogador;
            }
        }
        Jogador jogador = new Jogador(nome);
        jogadores.add(jogador);
        this.jogadoresDaSessaoAtual.add(jogador);
        return jogador;
    }
    
    public Jogador atualizarJogadoresAtuais(String nome, int pontos){
        for (Jogador jogador : jogadoresDaSessaoAtual){
            if (jogador.comparaNome(nome)){
                jogador.atualizarRecorde(pontos);
                return jogador;
            }
        }
        return null;
    }
    
    public void salvarArq(){
        try{
            FileWriter f = new FileWriter("jogadores.txt");
            BufferedWriter b = new BufferedWriter(f);
            
            b.write(this.jogadores.size() + "\n");
            
            for (Jogador i : this.jogadores){
                i.salvarArq(b);
            }
            
            b.close();
        }
        catch (IOException e){
            System.out.println("Erro ao salvar o arquivo de jogadores.");
        }
    }
    
    public void carregarArq(){
        try{
            FileReader f = new FileReader("jogadores.txt");
            BufferedReader b = new BufferedReader(f);
            
            int c = Integer.parseInt(b.readLine() );
            
            for (int i=0; i<c; i++){
                this.jogadores.add(new Jogador(b));
            }
            
            b.close();
            System.out.println(this.jogadores.size() + " jogadores carregados.");
        }
        catch (IOException e){
            this.jogadores = new ArrayList<Jogador>();
            System.out.println("Nenhum jogador carregado.");
        }
    }
    
    public Jogador bemVindo() {
        String nome = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Bem vindo!", JOptionPane.PLAIN_MESSAGE);
        while (nome == null || nome.equals("")) { 
            nome = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Nome Inválido!", JOptionPane.PLAIN_MESSAGE);
        }

        this.atual = this.localizarJogador(nome);
        return this.atual;
    }
    
    public boolean errou(int pontos) {
        int x = JOptionPane . showConfirmDialog (null ," Voce acertou " + pontos + " numeros. Deseja comecar um novo jogo?", " Fim de jogo", JOptionPane .YES_NO_OPTION ) ;
        if(x == 0)
            return true;
        return false;
    }
    
    public Jogador recordista(){
        int recordeAtual = -1;
        String nomeRecordistaAtual = "";
        Jogador jogadorRecordista = new Jogador("");
        for(Jogador jogador : this.jogadores) {
            if(jogador.comparaPontos(recordeAtual)){
                recordeAtual = jogador.getPontos();
                nomeRecordistaAtual = jogador.retornaNome();
                jogadorRecordista = jogador;
            }
        }

        return jogadorRecordista;
    }
    
    public void bye(String Nome, int pontos){
        int recordeDaSessao = -1;
        String nomeRecordistaDaSessao = "";

        for(Jogador jogador : this.jogadoresDaSessaoAtual) {
            if(jogador.comparaPontos(recordeDaSessao)){
                recordeDaSessao = jogador.getPontos();
                nomeRecordistaDaSessao = jogador.retornaNome();
            }
        }

        JOptionPane.showMessageDialog (null , " Recorde da sessao: " + nomeRecordistaDaSessao + " - " + recordeDaSessao + " pontos. " + "Geral: " + this.recordista().retornaNome() + " - " + this.recordista().getPontos() + " ponto(s).", " RECORDES ", JOptionPane.PLAIN_MESSAGE );
    }
    
    public void jogo(){
        int count = 0;
        while(true) {
            
            String novoNumeroSorteado = Integer.toString(Sortear());
            this.correta += novoNumeroSorteado;
            String numero = JOptionPane.showInputDialog(null, "O novo número é: " + novoNumeroSorteado , "Digite a sequência completa", JOptionPane.PLAIN_MESSAGE);

            if(this.correta.equals(numero))
                count++;
            else {
                this.atual.atualizarRecorde((count));
                this.salvarArq();
                this.atualizarJogadoresAtuais(this.atual.retornaNome(), count);
                if(this.errou(count)) {
                    this.correta = "";
                    this.bemVindo();
                    count = 0;
                }
                else {
                    this.bye(numero, count);
                    break;
                } 
            }
        }
    }
}
