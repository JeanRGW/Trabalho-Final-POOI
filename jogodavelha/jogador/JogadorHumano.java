package jogodavelha.jogador;

import java.util.Scanner;
import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

public class JogadorHumano implements Jogador {
    private String nome;
    private Boolean player;

    public JogadorHumano(String nome, Boolean player){
        this.nome = nome;
        this.player = player;
    }

    public Jogada Jogar(Tabuleiro tabuleiro){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Insira a linha: ");
        int x = (scan.nextInt() - 1);
        System.out.println("Insira a coluna: ");
        int y = (scan.nextInt() - 1);

        while(x < 0 || y < 0 || x >= tabuleiro.getSize() || y >= tabuleiro.getSize() || tabuleiro.getPos(x,y) != ' '){
            System.out.println("Coordenada invadida, inserindo novamente.");
            System.out.println("Insira a linha: ");
            x = scan.nextInt() - 1;
            System.out.println("Insira a coluna: ");
            y = scan.nextInt() - 1;
        }

        return new Jogada(player, x, y);
    }

    public String getNome(){
        return nome;
    }
}
