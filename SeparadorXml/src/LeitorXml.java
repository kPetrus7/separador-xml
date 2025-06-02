import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeitorXml {
    public static String extrairData(File arquivoXml){
        try{ 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder Builder = factory.newDocumentBuilder(); 
            Document doc = Builder.parse(arquivoXml); 
           
            doc.getDocumentElement().normalize(); 

            NodeList dataDoc = doc.getElementsByTagName("dhEmi"); 
            if(dataDoc.getLength() > 0){ 
                return filtroCompetencia(dataDoc.item(0).getTextContent()); 
            }
        }catch (Exception e){ 
            System.out.println("Erro de leitura arquivo xml: " +arquivoXml.getName());
        }
        return null;
    }

    public static String filtroCompetencia(String data) {
        String dia;
        try{
        dia = data.substring(8,10);
        } catch (Exception e) {
            return null; 
        }
        return dia;
    }

    public static File renomeadorXml(File arquivoEntrada) {

        String fornecedor = null;
        String nota = null;

        try{ 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder Builder = factory.newDocumentBuilder(); 
            Document doc = Builder.parse(arquivoEntrada); 
            doc.getDocumentElement().normalize(); 

            NodeList nomeFornecedor = doc.getElementsByTagName("xNome"); 
            if(nomeFornecedor.getLength() > 0) {
                Node node = nomeFornecedor.item(0); 
                fornecedor = node.getTextContent().trim();
            }else{
                nomeFornecedor = doc.getElementsByTagName("xFant");
                if(nomeFornecedor.getLength() > 0) {
                Node node = nomeFornecedor.item(0); 
                fornecedor = node.getTextContent().trim();
                } else{
                    System.out.println("Erro ao renomear");
                }
            }
        }catch (Exception e){
            e.printStackTrace(); 
            }
        try{ 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder Builder = factory.newDocumentBuilder(); 
            Document doc = Builder.parse(arquivoEntrada); 
            doc.getDocumentElement().normalize(); 

            NodeList numNota = doc.getElementsByTagName("nNF"); 
            if(numNota.getLength() > 0) {
                Node node = numNota.item(0); 
                nota = node.getTextContent().trim();
            } else {
                System.out.println("Erro na nota");
            }
        }catch (Exception e){ 
            e.printStackTrace();
        }
        String caminho = arquivoEntrada.getParent() + File.separator + fornecedor + " - NF." + nota + ".xml";

        File arquivoRenomeado = new File(caminho);
        arquivoEntrada.renameTo(arquivoRenomeado);

        return arquivoRenomeado;
    }
}