package model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NotaXml {

    private int id;
    private String nomeEmissor;
    private String nomeDestinatario;
    private String numNota;
    private String dataEmi;
    private Path caminhoNota;
    private File nomeArquivo;

    public NotaXml (int identificador, String emissor, String destinatario, String numero, String data, Path caminho, File arquivo){
        this.id = identificador;
        this.nomeEmissor = emissor;
        this.nomeDestinatario = destinatario;
        this.numNota = numero;
        this.dataEmi = data;
        this.caminhoNota = caminho;
        this.nomeArquivo = arquivo;
    }

    public int getId(){
        return id;
    }

    public String getEmissor(){
        return nomeEmissor;
    }

    public String getDestino(){
        return nomeDestinatario;
    }

    public String getNumero(){
        return numNota;
    }

    public String getData(){
        return dataEmi;
    }

    public Path getPath(){
        return caminhoNota;
    }

    public File getArquivo(){
        return nomeArquivo;
    }

    public void setPath(String caminho){
        this.caminhoNota = Paths.get(caminho);
    }

    @Override
    public String toString() {
        return "NotaXml{" + 
                "id='" + id +'\n' +
                "emissor='" + nomeEmissor + '\n' +
                "destinatario='" + nomeDestinatario + '\n' +
                "numero='" + numNota + '\n' +
                "data='" + dataEmi + '\n' +
                "caminho='" + caminhoNota + '\n' +
                "}\n";
    }
}