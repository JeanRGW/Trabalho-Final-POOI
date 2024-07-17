package JogoDaVelha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tabuleiro {
    private Character tabuleiro[][];
    private int size;
    private List<Pair<Number, Number>> posicoesRestantes;

    public Tabuleiro(int size){
        this.size = size;
        tabuleiro = new Character[size][size];

        posicoesRestantes = new ArrayList<>();
        for(int i=0; i < size; i++){
            for(int j=0; j < size; j++){
                posicoesRestantes.add(new Pair<>(i, j));
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public void add(Jogada x) throws Exception {
        Character player = x.getPlayer()?'o':'x';
        Character adversario = x.getPlayer()?'x':'o';

        Pair<Number, Number> coordenada = new Pair<>(x.getX(), x.getY());

        if(posicoesRestantes.indexOf(coordenada) == -1){
            throw new Exception();
        }

        tabuleiro[x.getX()][x.getY()] = player;
        posicoesRestantes.remove(posicoesRestantes.indexOf(coordenada));
    };

    public Boolean encerrado(){
        return false;
    }

    public int getSize(){
        return size;
    }

    public void print(){
        for(int i=0; i<size; i++){
            String linha = "|";

            for(int j=0; j<size; j++){
                linha += tabuleiro[i][j] + "|";
            }

            System.out.println(linha);
        }
    }

    public Jogada jogadaVitoriosa(Boolean jogador){
        Character player = jogador ? 'o':'x';

        // Verificar linhas horizontais;
        for(int i=0; i<size; i++){
            int contador = 0;
            int xVazio = -1, yVazio = -1;

            for(int j=0; j<size; j++){
                if(tabuleiro[i][j] == player){
                    contador++;
                }
                    
                if(tabuleiro[i][j] == ' '){
                    xVazio = i;
                    yVazio = j;
                }
            }

            if(contador == 2 && xVazio != -1){
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar linhas verticais;
        for(int i=0; i<size; i++){
            int contador = 0;
            int xVazio = -1, yVazio = -1;

            for(int j=0; j<size; j++){
                if(tabuleiro[j][i] == player){
                    contador++;
                }
                    
                if(tabuleiro[j][i] == ' '){
                    xVazio = j;
                    yVazio = i;
                }
            }

            if(contador == 2 && xVazio != -1){
                return new Jogada(jogador, xVazio, yVazio);
            }
        }

        // Verificar a diagonal principal;
        {int contador = 0;
        int xVazio = -1, yVazio = -1;
        for (int i = 0; i < size; i++) {
            if (tabuleiro[i][i] == player) {
                contador++;
            }
            if (tabuleiro[i][i] == ' ') {
                xVazio = i;
                yVazio = i;
            }
        }
        if (contador == 2 && xVazio != -1) {
            return new Jogada(jogador, xVazio, yVazio);
        }}

        // Verificar a diagonal secundária
        {int contador = 0;
        int xVazio = -1;
        int yVazio = -1;
        for (int i = 0; i < size; i++) {
            if (tabuleiro[i][size - 1 - i] == player) {
                contador++;
            }
            if (tabuleiro[i][size - 1 - i] == ' ') {
                xVazio = i;
                yVazio = size - 1 - i;
            }
        }
        if (contador == 2 && xVazio != -1) {
            return new Jogada(jogador, xVazio, yVazio);
        }}

        return null;
    }

    public Jogada jogadaAleatoria(Boolean jogador){
        Random random = new Random();

        Pair<Number, Number> pos = posicoesRestantes.get(random.nextInt(posicoesRestantes.size()));

        return new Jogada(jogador, pos.x.intValue(), pos.y.intValue());
    }
}
