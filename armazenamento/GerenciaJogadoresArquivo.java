package armazenamento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import jogodavelha.jogadores.JogadorHumano;

public class GerenciaJogadoresArquivo implements GerenciaJogadores {
    private final static String filename = "GameData";
    private Map<String, Integer> jogadores;

    public GerenciaJogadoresArquivo() throws IOException {
        carregarJogadores();
    }

    private void carregarJogadores() throws IOException{
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            Map<String, Integer> jogadores = new TreeMap<>();
            String linhaJogador;
            
            while((linhaJogador = br.readLine()) != null){
                String infoJogador[] = linhaJogador.split(",");
                jogadores.put(infoJogador[0],  Integer.parseInt(infoJogador[1]));
            }

            br.close();
            this.jogadores = jogadores;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, iniciando nova lista de jogadores.");
            this.jogadores = new TreeMap<>();
        }
    }

    private void salvarJogadores() {
        try {
            FileWriter fw = new FileWriter(filename);
            
            for(Entry<String, Integer> x : jogadores.entrySet()){
                fw.write(x.getKey() + "," + x.getValue() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo.\nIOException: " + e.getMessage());
        }
    }

    public void remove(String x){
        jogadores.remove(x);
        
        salvarJogadores();
    }

    public void add(JogadorHumano x){
        jogadores.put(x.getNome(), x.getPontos());

        salvarJogadores();
    }

    public List<JogadorHumano> getJogadores(){
        List<JogadorHumano> list = new ArrayList<>();

        for(Entry<String, Integer> x: jogadores.entrySet()){
            list.add(new JogadorHumano(x.getKey(), x.getValue()));
        }

        return list;
    }
}
