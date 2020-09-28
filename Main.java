import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String pastaContendoOsArquivos = "arquivos";

        ArvoreAVL arvore = new ArvoreAVL();
        String palavraDigitada = "";

        LinkedList<CaminhoEResultado> caminhosEResultados = new LinkedList<CaminhoEResultado>();

        
        Scanner scannerDaPalavra = new Scanner(System.in);
        System.out.print("Entre com um termo a ser pesquisado: ");
        palavraDigitada = scannerDaPalavra.next();
        scannerDaPalavra.close();
        
        File folder = new File(pastaContendoOsArquivos);
        File[] files = folder.listFiles();
        
        for (File file : files) {
            if (file.isFile()) {
                
                Scanner scannerDoArquivo = new Scanner(new File(file.toString()));
                while (scannerDoArquivo.hasNextLine()) {
                  
                    Scanner scannerDaLinha = new Scanner(scannerDoArquivo.nextLine());
                    while (scannerDaLinha.hasNext()) {
                        
                        arvore.insere_elemento(scannerDaLinha.next().replace(".", "").replace(",", ""));
                    }
                }

               
                No noRetornado = arvore.retorna_no(palavraDigitada);
                caminhosEResultados
                        .add(new CaminhoEResultado(file.toString().replace(pastaContendoOsArquivos + "/", ""),
                                (noRetornado == null ? 0 : noRetornado.contador)));

           
                arvore.esvazia();
            }
        }

        
        int totalDeOcorrencias = 0;
        for (CaminhoEResultado caminhoEResultado : caminhosEResultados) {
            totalDeOcorrencias += caminhoEResultado.resultado;
        }
       

        
        System.out.println("\nTotal de ocorrencias de \"" + palavraDigitada + "\": " + totalDeOcorrencias + "\n");
        for (CaminhoEResultado caminhoEResultado : caminhosEResultados) {
            System.out.println("Arquivo \"" + caminhoEResultado.caminho + "\": " + caminhoEResultado.resultado);
        }
      
    }
}