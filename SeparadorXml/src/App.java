import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); 
        String respostaRepeticao; 

        do{
            ArquivosXml arquivosXml = new ArquivosXml();
            Set<String> competencias = new HashSet<>(); 

            int acertos = 0;
            int erros = 0;

            boolean renomear;

            System.out.println("Insira o caminho da pasta:"); 
            String diretorio = scanner.nextLine();

            System.out.println("Renomear arquivos? [S/N]");
            String respostaRenomear = scanner.nextLine().trim().toUpperCase(); 

            if(respostaRenomear.equals("S")){
                renomear = true;
            } else {renomear = false;}

            for(File arquivo:arquivosXml.getArquivos(renomear, diretorio)){ 
                competencias.add(LeitorXml.extrairData(arquivo)); 
            }

            for(String competencia:competencias){ 
                File pasta = new File(diretorio+"/"+competencia); 
                if(!pasta.exists()){
                    pasta.mkdir();
                }
            }
            for(File arquivo:arquivosXml.getArquivos(false, diretorio)){ 
                for(String dia:competencias){ 
                    if(LeitorXml.extrairData(arquivo).equals(dia)){
                        Path nota = arquivo.toPath(); 
                        Path pasta = Paths.get(diretorio, dia); 
                        Path destino = pasta.resolve(nota.getFileName()); 
                        boolean sucesso = TransferirArquivos.moverArquivos(nota, destino); 
                        if(sucesso){acertos++;}else{erros++;} 
                        break; 
                    }
                }
            }
            System.out.println("Número de notas processadas: "+(acertos+erros));
            System.out.println("Número de acertos: "+acertos);
            System.out.println("Número de erros: "+erros);
            System.out.println("Fim\n"); 
            System.out.println("Deseja executar novamente em outra pasta? [S/N]");
            respostaRepeticao = scanner.nextLine().trim().toUpperCase(); 
        }while(respostaRepeticao.equals("S"));
        scanner.close(); 
    }
}