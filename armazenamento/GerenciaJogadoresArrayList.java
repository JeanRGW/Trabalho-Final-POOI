package armazenamento;

import java.util.ArrayList;
import jogodavelha.jogadores.Jogador;
import jogodavelha.jogadores.JogadorHumano;

public class GerenciaJogadoresArrayList implements GerenciaJogadores {
    ArrayList<Jogador> list;

    GerenciaJogadoresArrayList(ArrayList<Jogador> list){
        this.list = list;
    };

    public void remove(String nome){
        for(Jogador x: list){
            if(x.getNome().equals(nome)){
                list.remove(list.indexOf(x));
            }
        }
    }

    public void add(JogadorHumano x){
        if(list.contains(x)){
            list.set(list.indexOf(x), x);
        } else {
            list.add(x);
        }
    }
}
