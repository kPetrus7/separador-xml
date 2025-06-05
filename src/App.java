import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        /*
         *  ESTE PROGRAMA SERVE PARA FAZER A SEPARAÇÃO NOTAS FISCAIS NO FORMATO XML EM PASTAS SEPARADAS POR DIA.
         *  AS NOTAS NECESSARIAMENTE DEVEM SER DE UM ÚNICO MÊS.
         * 
         *  ESTRUTURA DO PROGRAMA:
         *      -> LOOP PRINCIPAL;
         *      -> CLASSE DE OBJETO NOTAXML;
         *      -> CLASSE DE MÉTODOS PARA LEITURA E MANIPULAÇÃO DE ARQUIVOS XML;
         * 
         *  FLUXOGRAMA DO PROGRAMA:
         * 
         *      ==>> INICIO DO LOOP PRINCIPAL;
         * 
         *      ==>> ENTRADA DE DADOS POR PARTE DO USUÁRIO COMO: DIRETÓRIO ONDE ESTÃO AS NOTAS, OPÇÃO DE RENOMEAR AS NOTAS, TIPO
         *      DE NOTA [ENTRADA/SAÍDA];
         * 
         *      ==>> INICIO DO LOOP DE LEITURA E MANIPULAÇÃO DAS NOTAS; 
         * 
         *      ==>> FUNÇÃO getArquivos PERCORRE O DIRETÓRIO EM BUSCA DE ARQUIVOS XML; 
         * 
         *      ==>> FUNÇÃO lerArquivo INSTANCIA UM DOM A PARTIR DE CADA XML E RETORNA O OBJETO NotaXml; 
         * 
         *      ==>> FUNÇÃO getArquivos LISTA OS OBJETOSNotaXml DO RETORNO DE lerArquivos EM UM ARRAY E RETORNA ESSE ARRAY PARA A
         *      FUNÇÃO PRINCIPAL;
         * 
         *      ==>> SE FOI REQUERIDO PELO USUÁRIO, A FUNÇÃO renomeadorXml ALTERA O NOME DOS ARQUIVOS DE ACORDO COM O TIPO DA NOTA;
         * 
         *      ==>> PARA CADA DIA ENCONTRADO NA TAG "dhEmi" DENTRO DOS ARQUIVOS XML, É CRIADO UMA PASTA COM O MÊS E O DIA 
         *      REFENTES. DIAS IGUAIS DENTRO DO MÊS SÃO DESCONSIDERADOS;
         * 
         *      ==>> FUNÇÃO moverArquivos TENTA MOVAR INDIVIDUALMENTE CADA ARQUIVO PARA A PASTA COM O DIA ESPECÍFICO E RETORNA true
         *      CASO REALIZE COM SUCESSO OU false CASO NÃO;
         * 
         *      ==>> AS TENTATIVAS DE MOVER AS NOTAS SÃO CONTABILIZADAS COMO ACERTOS E ERROS;
         * 
         *      ==>> É EXIBIDO NO TERMONAL O FIM DA EXECUÇÃO COM O NÚMERO DE NOTAS, ACERTOS E ERROS ALÉM DO PROGRAMA OFERECER A
         *      EXECUÇÃO DO LOOP PRINCIPAL NOVAMENTE EM OUTRO DIRETÓRIO.
         *          
         */

        Scanner scanner = new Scanner(System.in);
        String respostaRepeticao;

        do{
            Set<String> competencias = new HashSet<>();

            int acertos = 0;
            int erros = 0;
            boolean renomear;
            boolean tipoEntradaSaida = false;
            boolean validador = false;

            System.out.println("Insira o caminho da pasta:");
            String diretorio = scanner.nextLine();

            do{
                validador = false;
                System.out.println("Renomear arquivos? [S/N]");
                String respostaRenomear = scanner.nextLine().trim().toUpperCase();

                if(respostaRenomear.equals("S")){
                    renomear = true;
                    validador = true;
                } else if(respostaRenomear.equals("N")){
                    renomear = false;
                    validador = true;
                } else {
                    renomear = false; 
                }
            }while(!validador);
            
            if(renomear){
                do{
                    validador = false;
                    System.out.println("Suas notas são de entrada ou saída?[E/S]");
                    String respostaEntradaSaida = scanner.nextLine().trim().toUpperCase();

                    if(respostaEntradaSaida.equals("E")){
                        tipoEntradaSaida = true;
                        validador = true;
                    } else if (respostaEntradaSaida.equals("S")){
                        tipoEntradaSaida = false;
                        validador = true;
                    }
                }while(!validador);
            }

            for(NotaXml nota:ManipuladorXml.getArquivos(diretorio)){

                competencias.add(nota.getData());

                if(renomear){
                    ManipuladorXml.renomeadorXml(tipoEntradaSaida ,nota);
                }

                File pasta = new File(diretorio + File.separator + nota.getData()); 
                if(!pasta.exists()){
                    pasta.mkdir();
                }

                boolean sucesso = ManipuladorXml.moverArquivos(nota.getPath(), Paths.get(diretorio, nota.getData()).resolve(nota.getPath().getFileName()));
                
                if(sucesso){
                    acertos++;
                } else if(!sucesso){
                    erros++;
                }

                System.out.println(nota.toString());
            }

            System.out.println("===================================================================");
            System.out.println("Número de notas processadas: " + (acertos + erros));
            System.out.println("Número de acertos: " + acertos);
            System.out.println("Número de erros: " + erros);
            System.out.println("Fim");
            System.out.println("===================================================================");
            System.out.println("Deseja executar novamente em outra pasta? [S/N]");
            respostaRepeticao = scanner.nextLine().trim().toUpperCase(); 
        } while(respostaRepeticao.equals("S"));

        scanner.close();
    }
}
