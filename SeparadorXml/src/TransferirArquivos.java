import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class TransferirArquivos {
    public static boolean moverArquivos(Path origem, Path destino) { 
        try { 
            Files.move(origem,destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Movendo: "+ origem);
            return true; 

        } catch (IOException e) {
            System.out.println("Erro ao mover arquivo: "+origem+" para a pasta: "+destino);
            return false; 
        }
    }
}
