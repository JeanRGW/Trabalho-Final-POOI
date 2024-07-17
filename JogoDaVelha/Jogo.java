package JogoDaVelha;

import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);

        System.out.println("Insira o nome para o jogador 1: ");
        jogadores[0] = new JogadorHumano(scan.nextLine(), false);

        if(!usandoIA){
            System.out.println("Insira o nome para o jogador 2: ");
            jogadores[1] = new JogadorHumano(scan.nextLine(), true);
        } else {
            jogadores[1] = new JogadorIA(true);
        }
        

        while(!tabuleiro.encerrado()){
            for(Jogador jogador: jogadores){
                tabuleiro.print();
                try {
                    tabuleiro.add(jogador.Jogar(tabuleiro));
                } catch (Exception x) {
                    System.out.println(x);
                }
                
            }
        };
    }
}