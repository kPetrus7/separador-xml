import java.io.File;
import java.util.ArrayList;

public class ArquivosXml{
    public ArrayList<File> getArquivos(boolean renomear, String diretorio){

        File pasta = new File(diretorio); 
        File[] arquivos = pasta.listFiles(); 

        ArrayList<File> arquivosXML = new ArrayList<>(); 

        if (arquivos != null) { 
            for (File arquivo : arquivos) { 
                if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".xml")) { 
                    if(renomear){
                        File arquivoRenomeado = new File(arquivo.getParent(), LeitorXml.renomeadorXml(arquivo).getName());
                        //System.out.println(arquivoRenomeado.getName());
                        arquivosXML.add(arquivoRenomeado);
                    }
                    else{
                        //System.out.println(arquivo.getName());
                        arquivosXML.add(arquivo);
                    } 
                }
            }
       } return arquivosXML; 
    }
}

