import java.io.IOException;
import java.util.List;

import armazenamento.*;

import entradadados.Console;
import jogodavelha.Jogo;
import jogodavelha.interfaces.InterfaceConsole;
import jogodavelha.jogadores.JogadorHumano;

/**
 * Classe responsável por integrar as diversas partes do jogo, iniciando e
 * gerenciando o jogo da velha.
 * 
 * Esta classe fornece a interface de usuário para iniciar o jogo, alternar
 * entre
 * modo de um jogador e dois jogadores, e escolher jogadores. Além disso,
 * gerencia
 * a pontuação e armazenamento dos jogadores.
 * 
 * @author GuilhermeKT e JeanRGW
 * @version 1.0
 */
public class IniciaJogo {
    private static JogadorHumano player1 = null, player2 = null;
    private static boolean singlePlayer = true;
    private static GerenciaJogadoresArquivo pManager;

    /**
     * Método principal que inicia a aplicação e inicializa o gerenciamento de
     * jogadores.
     * 
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        try {
            pManager = new GerenciaJogadoresArquivo();
        } catch (DataParsingException e) {
            System.out.println(
                    "Erro ao interpretar GameData, verifique o arquivo.\nDataParsingException: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Erro ao ler jogadores.\nIOException: " + e.getMessage());
            return;
        }

        menuInicial();
    }

    /**
     * Exibe o menu inicial do jogo, permitindo ao usuário iniciar o jogo, alternar
     * entre
     * modos de jogador, escolher jogadores ou sair do programa.
     */
    public static void menuInicial() {
        int escolha = 0;

        do {
            Console.cls();
            System.out.println("|| Jogo da Velha ||");
            System.out.println("1.Iniciar.");
            System.out.println("2.Alternar Jogadores (" + (singlePlayer ? "1" : "2") + ")");
            System.out.println("3.Escolher Jogador");
            System.out.println("4.Sair");

            System.out.print("\nEscolha: ");
            escolha = Console.nextInt();

            switch (escolha) {
                case 1: {
                    jogar();
                }
                    break;

                case 2: {
                    singlePlayer = !singlePlayer;
                }
                    break;

                case 3: {
                    Console.cls();

                    if (singlePlayer) {
                        player1 = escolherJogador();
                    } else {
                        player2 = escolherJogador();
                    }
                }
                    break;
            }
        } while (escolha != 4);
    }

    /**
     * Permite ao usuário escolher um jogador existente ou criar um novo jogador.
     * 
     * @return o jogador escolhido ou criado
     */
    private static JogadorHumano escolherJogador() {
        List<JogadorHumano> jogadores = pManager.getJogadores();

        System.out.println("Escolha o jogador, ou insira 'n' para novo jogador:");
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + jogadores.get(i).getNome() + ", " + jogadores.get(i).getPontos() + " pts");
        }

        while (true) {
            String input = Console.nextLine();

            // Cria um novo jogador se a entrada for 'n'
            if (input.equalsIgnoreCase("n")) {
                JogadorHumano novoJogador = criarJogador();
                pManager.add(novoJogador);
                return novoJogador;
            }

            // Retorna o jogador existente se a entrada for válida
            if (Console.isIntParseable(input)) {
                int index = Integer.parseInt(input);
                if (index > 0 && index <= jogadores.size()) {
                    return jogadores.get(index - 1);
                }
            }

            // Mensagem de erro para entradas inválidas
            System.out.println("Entrada inválida, insira um número válido ou 'n' para novo jogador.");
        }
    }

    /**
     * Cria um novo jogador com o nome fornecido e inicializa a pontuação com 0.
     * 
     * @return o novo jogador criado
     */
    private static JogadorHumano criarJogador() {
        System.out.print("Insira o nome: ");

        return new JogadorHumano(Console.nextLine(), 0);
    }

    /**
     * Inicia uma nova partida do jogo da velha com os jogadores escolhidos e o modo
     * (um jogador ou dois jogadores). Atualiza a pontuação e salva o estado dos
     * jogadores.
     */
    private static void jogar() {
        while (player1 == null) {
            player1 = escolherJogador();
        }

        while (!singlePlayer && (player2 == null || player2.getNome().equals(player1.getNome()))) {
            Console.cls();
            System.out.println("Jogador 2 inválido, escolha novamente.");
            player2 = escolherJogador();
        }

        Jogo jogo;

        if (singlePlayer) {
            jogo = new Jogo(player1, new InterfaceConsole());
        } else {
            jogo = new Jogo(player1, player2, new InterfaceConsole());
        }

        jogo.iniciar();

        if (jogo.getGanhador() == 0) {
            System.out.println("Partida terminou em empate");
        } else {
            System.out.println("Jogador " + jogo.getGanhador() + " ganhou a partida.");
        }

        switch (jogo.getGanhador()) {
            case 1: {
                player1.addPonto();
                pManager.add(player1);

            }
                break;

            case 2: {
                if (!singlePlayer) {
                    player2.addPonto();
                    pManager.add(player2);
                }
            }
                break;
        }

    }
}