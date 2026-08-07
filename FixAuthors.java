import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class FixAuthors {
    public static void main(String[] args) throws Exception {
        Files.walk(Paths.get("d:/Projeto_LeoLemos/Projeto_JAVA_PDV/infox/src/main/java"))
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java") || p.toString().endsWith(".form"))
            .forEach(p -> {
                try {
                    String content = new String(Files.readAllBytes(p), StandardCharsets.UTF_8);
                    boolean changed = false;
                    
                    if (content.contains("Professor José de Assis")) {
                        content = content.replace("Professor José de Assis", "Leonardo Lemos");
                        changed = true;
                    }
                    if (content.contains("josedeassis.com.br")) {
                        content = content.replace("josedeassis.com.br", "leonardolemos.com");
                        changed = true;
                    }
                    
                    if (changed) {
                        Files.write(p, content.getBytes(StandardCharsets.UTF_8));
                        System.out.println("Atualizado: " + p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }
}
