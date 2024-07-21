package jogodavelha.jogadores;

import entradadados.Console;
import jogodavelha.Jogada;
import jogodavelha.Tabuleiro;

public class JogadorHumano implements Jogador {
    private String nome;
    private int pts;

    public JogadorHumano(String nome, int pts){
        this.nome = nome;
        this.pts = pts;
    }

    public boolean isAI(){
        return false;
    }

    public Jogada jogar(Tabuleiro tabuleiro, boolean player){
        System.out.println("Insira a linha: ");
        int x = (Console.nextInt() - 1);
        System.out.println("Insira a coluna: ");
        int y = (Console.nextInt() - 1);

        while(x < 0 || y < 0 || x >= tabuleiro.getSize() || y >= tabuleiro.getSize() || tabuleiro.getPos(x,y) != ' '){
            System.out.println("Coordenada invadida, inserindo novamente.");
            System.out.println("Insira a linha: ");
            x = Console.nextInt() - 1;
            System.out.println("Insira a coluna: ");
            y = Console.nextInt() - 1;
        }

        return new Jogada(player, x, y);
    }

    public String getNome(){
        return nome;
    }

    public int getPontos(){
        return pts;
    }

    public void addPonto(){
        pts++;
    }

    @Override
    public boolean equals(Object o){
        if(o.getClass() == getClass()){
            Jogador obj = (Jogador) o;
            if(obj.getNome() == getNome()){
                return true;
            }
        }

        return false;
    }
}
