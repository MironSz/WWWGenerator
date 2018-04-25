import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Kraj kraj = new Kraj("/home/miron/IdeaProjects/WWWGenerator/src/resources/gm-kraj_gl.csv");
//            System.out.println(kraj);
        kraj.createHtml();


    }
}
