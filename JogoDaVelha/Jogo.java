package jogodavelha;

import entradadados.Entrada;
import jogodavelha.jogador.*;

public class Jogo {
    Tabuleiro tabuleiro;
    Jogador jogadores[];
    private Boolean usandoIA;

    public Jogo(Boolean usandoIA){
        this.usandoIA = usandoIA;
        tabuleiro = new Tabuleiro(3);
        jogadores = new Jogador[2];
    }

    public void Iniciar(){
        System.out.println("Insira o nome para o jogador 1: ");
        jogadores[0] = new JogadorHumano(Entrada.nextLine(), false);

        if(!usandoIA){
            System.out.println("Insira o nome para o jogador 2: ");
            jogadores[1] = new JogadorHumano(Entrada.nextLine(), true);
        } else {
            jogadores[1] = new JogadorIA(true);
        }
        
        
        tabuleiro.print(); 
        int turno = 0;
        while(!tabuleiro.encerrado()){
            Jogador jogador = jogadores[turno % 2];
            System.out.println("Vez do jogador: " + jogador.getNome());

            try {
                tabuleiro.add(jogador.Jogar(tabuleiro));
            } catch (Exception x) {
                System.out.println(x);
            }

            tabuleiro.print();
            turno++;
        };
    }
}