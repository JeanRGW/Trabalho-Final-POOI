package armazenamento;

import java.util.ArrayList;
import java.util.List;

import jogodavelha.jogadores.JogadorHumano;

public class GerenciaJogadoresArrayList implements GerenciaJogadores {
    ArrayList<JogadorHumano> list;

    GerenciaJogadoresArrayList() {
        this.list = new ArrayList<>();
    }

    GerenciaJogadoresArrayList(List<JogadorHumano> list) {
        this.list = new ArrayList<>(list);
    };

    public void remove(String nome) {
        for (JogadorHumano x : list) {
            if (x.getNome().equals(nome)) {
                list.remove(list.indexOf(x));
            }
        }
    }

    public void add(JogadorHumano x) {
        if (list.contains(x)) {
            list.set(list.indexOf(x), x);
        } else {
            list.add(x);
        }
    }

    public List<JogadorHumano> getJogadores() {
        return list;
    }
}
