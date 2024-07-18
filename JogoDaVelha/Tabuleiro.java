package jogodavelha;

import java.util.ArrayList;
import java.util.List;

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
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public void add(Jogada x) throws Exception {
        Character player = x.getPlayer()?'o':'x';

        Pair<Number, Number> coordenada = new Pair<>(x.getX(), x.getY());

        if(posicoesRestantes.indexOf(coordenada) == -1){
            throw new Exception();
        }

        tabuleiro[x.getX()][x.getY()] = player;
        posicoesRestantes.remove(posicoesRestantes.indexOf(coordenada));
    };

    public Boolean encerrado(){
        return posicoesRestantes.size() == 0;
    }

    public int getSize(){
        return size;
    }

    public void print(){
        for(int i=0; i<size; i++){
            String linha = "";

            for(int j=0; j<size; j++){
                Character jogada = tabuleiro[i][j];
                linha += (jogada == 'o' ? ("\033[33m" + 'o' + "\033[39m") : (jogada == 'x' ? ("\033[31m" + 'x' + "\033[39m") : jogada) );
                if (j < size - 1)
                    linha += " | ";
            }

            System.out.println(linha);
            if (i < size - 1)
                System.out.println("- + - + -");
        }
    }

    public Character[][] getTabuleiro(){
        return tabuleiro;
    }

    public Character getPos(int x, int y){
        return tabuleiro[x][y];
    }

    public List<Pair<Number, Number>> getPosicoesRestantes(){
        return posicoesRestantes;
    }
}
