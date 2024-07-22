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

/**
 * Implementação da interface GerenciaJogadores que utiliza um arquivo para
 * armazenar e gerenciar os dados dos jogadores.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public class GerenciaJogadoresArquivo implements GerenciaJogadores {
    private final static String filename = "GameData";
    private Map<String, Integer> jogadores;

    /**
     * Construtor que inicializa a lista de jogadores a partir do arquivo de dados.
     * 
     * @throws IOException          se ocorrer um erro de I/O durante a leitura do
     *                              arquivo
     * @throws DataParsingException se ocorrer um erro ao processar o conteúdo do
     *                              arquivo
     */
    public GerenciaJogadoresArquivo() throws IOException, DataParsingException {
        carregarJogadores();
    }

    /**
     * Carrega os dados dos jogadores a partir do arquivo.
     * 
     * @throws IOException          se ocorrer um erro de I/O durante a leitura do
     *                              arquivo
     * @throws DataParsingException se ocorrer um erro ao processar o conteúdo do
     *                              arquivo
     */
    private void carregarJogadores() throws IOException, DataParsingException {
        BufferedReader br = null;
        Map<String, Integer> jogadores = new TreeMap<>();
        String linhaJogador = null;

        try {
            br = new BufferedReader(new FileReader(filename));

            try {
                while ((linhaJogador = br.readLine()) != null) {
                    String infoJogador[] = linhaJogador.split(",");
                    jogadores.put(infoJogador[0], Integer.parseInt(infoJogador[1]));
                }
            } catch (NumberFormatException e) {
                throw new DataParsingException("Erro ao processar arquivo GameData, número inválido na linha: "
                        + linhaJogador + "\nNumberFormatException: " + e.getMessage());
            } catch (Exception e) {
                throw new DataParsingException("Erro ao processar arquivo GameData, linha atual: " + linhaJogador);
            }

            this.jogadores = jogadores;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, iniciando nova lista de jogadores.");
            this.jogadores = jogadores;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    /**
     * Salva os dados dos jogadores no arquivo.
     */
    private void salvarJogadores() {
        try {
            FileWriter fw = new FileWriter(filename);

            for (Entry<String, Integer> x : jogadores.entrySet()) {
                fw.write(x.getKey() + "," + x.getValue() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo.\nIOException: " + e.getMessage());
        }
    }

    /**
     * Remove um jogador do armazenamento pelo nome.
     * 
     * @param x o nome do jogador a ser removido
     */
    public void remove(String x) {
        jogadores.remove(x);

        salvarJogadores();
    }

    /**
     * Adiciona um jogador ao armazenamento ou atualiza as informações se o jogador
     * já existir.
     * 
     * @param x o jogador humano a ser adicionado ou atualizado
     */
    public void add(JogadorHumano x) {
        jogadores.put(x.getNome(), x.getPontos());

        salvarJogadores();
    }

    /**
     * Retorna a lista de todos os jogadores armazenados.
     * 
     * @return uma lista de objetos JogadorHumano representando todos os jogadores
     *         armazenados
     */
    public List<JogadorHumano> getJogadores() {
        List<JogadorHumano> list = new ArrayList<>();

        for (Entry<String, Integer> x : jogadores.entrySet()) {
            list.add(new JogadorHumano(x.getKey(), x.getValue()));
        }

        return list;
    }
}
