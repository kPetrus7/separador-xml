import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ManipuladorXml {
/*===============================================================================================================================*/
    public static ArrayList<NotaXml> getArquivos(String diretorio) {

        File pasta = new File(diretorio);
        File[] arquivos = pasta.listFiles();

        ArrayList<NotaXml> notasXml = new ArrayList<>();

        int n = 0;

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".xml")) {
                  notasXml.add(lerArquivo(arquivo, n));
                  n++;
                }
            }
        }
        return notasXml;
    }
/*===============================================================================================================================*/
    public static NotaXml lerArquivo (File arquivoXml, int id) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(arquivoXml);
            doc.getDocumentElement().normalize();

            NodeList dataDoc = doc.getElementsByTagName("dhEmi");
            NodeList nomeNota = doc.getElementsByTagName("xNome");
            NodeList numNota = doc.getElementsByTagName("nNF");
            
            String data;
            String emissor;
            String destinatario;
            String numero;
            Path caminho;

            if (dataDoc.getLength() > 0) {    
                data = dataDoc.item(0).getTextContent().substring(5, 10);
            } else {data = null;}

            if (nomeNota.getLength() > 1) {
                emissor = nomeNota.item(0).getTextContent();
                destinatario = nomeNota.item(1).getTextContent();
            } else {emissor = destinatario = null;}

            if (numNota.getLength() > 0){
                numero = numNota.item(0).getTextContent();
            } else {numero = null;}

            caminho = arquivoXml.toPath();

            NotaXml notaXml = new NotaXml(id,emissor,destinatario, numero, data, caminho, arquivoXml);
            return notaXml;

        } catch (Exception e){
            System.out.print("Erro de leitura em: "+e.getMessage());
            return null;
        }
    }
/*===============================================================================================================================*/
    public static void renomeadorXml (boolean tipoNota, NotaXml nota){

        File arquivoOrigem = nota.getPath().toFile();
        File arquivoFinal;

        String xNome = tipoNota ? nota.getEmissor() : nota.getDestino();

        arquivoFinal = new File(arquivoOrigem.getParent() + File.separator + xNome + " NF - " + nota.getNumero() + ".xml");

        if(arquivoFinal.exists()){
            System.err.println("Arquivo ja existente: " + arquivoFinal.getName() +"\nNão será renomeado!");
            return;
        }
        
        boolean sucesso = arquivoOrigem.renameTo(arquivoFinal);

        if(sucesso){
            //System.out.println(nota.getPath().getFileName() + "RENOMEADO");
            nota.setPath(arquivoFinal.getPath());
        } else {
            System.err.println("Erro ao renomear: " + arquivoOrigem.getName());
        }
    }
/*===============================================================================================================================*/
     public static boolean moverArquivos(Path origem, Path destino) {
        try {
            Files.move(origem, destino, StandardCopyOption.REPLACE_EXISTING);
            //System.out.println("Movendo: " + origem);
            return true;

        } catch (IOException e) {
            System.err.println("Erro ao mover arquivo: " + origem + " para a pasta: " + destino);
            return false;
        }
    }
}