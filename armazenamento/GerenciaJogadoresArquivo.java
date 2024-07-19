    package armazenamento;

    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import jogodavelha.jogadores.JogadorHumano;

    public class GerenciaJogadoresArquivo implements GerenciaJogadores {
        public static void write(List<JogadorHumano> jogadores){
            try {
                FileWriter fw = new FileWriter("GameData");

                for(JogadorHumano x: jogadores){
                    
                }

                fw.close();
            } catch (IOException e) {
                System.err.println("I/O Exception: " + e);
            }
        }

        public static List<JogadorHumano> read(){
            try {
                ArrayList<JogadorHumano> list = new ArrayList<>();
                
                FileReader fr = new FileReader("GameData");

                while(fr.read() == '{'){
                    char c = (char) fr.read();
                    String nome = "";
                    String pontuacaoString = "";

                    while(c != ','){
                        nome += c;
                        c = (char) fr.read();
                    }

                    c = (char) fr.read();
                    while(c != '}'){
                        pontuacaoString += c;
                    }

                    int pontuacao = Integer.parseInt(pontuacaoString);

                    list.add(new JogadorHumano(nome, pontuacao));
                }
            }
        }
        
    }