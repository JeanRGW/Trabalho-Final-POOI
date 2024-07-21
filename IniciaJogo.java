
import java.io.IOException;
import java.util.List;

import armazenamento.*;

import entradadados.Console;
import jogodavelha.Jogo;
import jogodavelha.jogadores.JogadorHumano;

public class IniciaJogo {
    private static JogadorHumano player1 = null, player2 = null;
    private static boolean singlePlayer = true;
    private static GerenciaJogadoresArquivo pManager;

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

        while (menuInicial()) {
        }
        ;
    }

    public static boolean menuInicial() {
        Console.cls();
        System.out.println("1.Iniciar.");
        System.out.println("2.Alternar Jogadores (" + (singlePlayer ? "1" : "2") + ")");
        System.out.println("3.Escolher Jogadores");
        System.out.println("4.Sair");

        switch (Console.nextInt()) {
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

            case 4:
                return false;

            default: {

            }
                break;
        }

        return true;
    }

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

    private static JogadorHumano criarJogador() {
        System.out.print("Insira o nome: ");

        return new JogadorHumano(Console.nextLine(), 0);
    }

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
            jogo = new Jogo(player1);
        } else {
            jogo = new Jogo(player1, player2);
        }

        jogo.iniciar();

        if (jogo.getGanhador() == 0) {
            System.out.println("Partida terminou em empate");
        } else {
            System.out.println("Jogador " + jogo.getGanhador() + " ganhou a partida.");
        }
        System.out.println("Insira qualquer coisa para continuar");
        Console.nextLine();

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