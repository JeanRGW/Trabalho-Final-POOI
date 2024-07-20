package armazenamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jogodavelha.jogadores.JogadorHumano;

public class GerenciaJogadoresArquivo implements GerenciaJogadores {
    private String arquivo;

    public GerenciaJogadoresArquivo() {
        this.arquivo = "";
        read();
    }

    public void add(JogadorHumano jogador) {
        if (arquivo.contains("{" + jogador.getNome() + ",")) {
            remove(jogador.getNome());
        }

        arquivo += jogador.toString();

        write();
    }

    public void remove(String player) {
        int indexInicio = arquivo.indexOf("{" + player + ",");
        int indexFim;
        if (indexInicio != -1) {
            indexFim = indexInicio;

            while (arquivo.charAt(indexFim) != '}') {
                indexFim++;
            }

            indexFim++;
            arquivo = arquivo.substring(0, indexInicio) + arquivo.substring(indexFim);
            write();
        }
    }

    private void write() {
        try (FileWriter fw = new FileWriter("GameData")) {
            fw.write(arquivo);
        } catch (IOException e) {
            System.err.println("I/O Exception: " + e);
        }
    }

    private void read() {
        try (BufferedReader fr = new BufferedReader(new FileReader("GameData"))) {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = fr.readLine()) != null) {
                sb.append(s);
            }
            arquivo = sb.toString();
        } catch (IOException e) {
            System.err.println("I/O Exception: " + e);
        }
    }

    public List<JogadorHumano> getJogadores() {
        List<JogadorHumano> list = new ArrayList<>();
        int i = 0;

        while (i < arquivo.length()) {
            if (arquivo.charAt(i) == '{') {
                i++;
                StringBuilder nome = new StringBuilder();
                StringBuilder pontuacaoString = new StringBuilder();

                while (arquivo.charAt(i) != ',') {
                    nome.append(arquivo.charAt(i));
                    i++;
                }

                i++;
                while (arquivo.charAt(i) != '}') {
                    pontuacaoString.append(arquivo.charAt(i));
                    i++;
                }

                i++;
                String nomeStr = nome.toString();
                int pontuacao = Integer.parseInt(pontuacaoString.toString());

                list.add(new JogadorHumano(nomeStr, pontuacao));
            } else {
                i++;
            }
        }

        return list;
    }

    public void fromArrayList(List<JogadorHumano> jogadores) {
        StringBuilder sb = new StringBuilder();

        for (JogadorHumano jogador : jogadores) {
            sb.append("{").append(jogador.getNome()).append(",").append(jogador.getPontos()).append("}");
        }

        arquivo = sb.toString();
        write();
    }
}
