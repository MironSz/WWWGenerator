import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;

public class Kraj extends Adm {

    public Kraj(String csvLocation) {
        name = "index";
        link = "index";
        results = new LinkedList<>();
        candidates = new LinkedList<>();
        childs = new LinkedList<>();
        generatedChilds = new HashSet<>();
        try {
            new File(destination + link).mkdir();

            FileInputStream fstream = new FileInputStream(csvLocation);//"C:/Users/Miron/IdeaProjects/WWWGenerator/src/resources/gm-kraj_gl.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            String firstLine;
            boolean first = true;
            while ((strLine = br.readLine()) != null) {
                if (first == false) {
                    results.add(new Result(strLine));
                    if (summaryResult != null)
                        summaryResult = summaryResult.add(new Result(strLine));
                    else
                        summaryResult = new Result(strLine);
                } else {
                    first = false;
                    firstLine = strLine.replace("\"", "");
                    for (int i = 10; i < firstLine.split(",").length; i++) {
                        Adm.candidates.add((firstLine.split(",")[i]));
                    }

                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        generateChilds();
    }

    public void generateChilds() {
        for (Result result : results) {
            if (generatedChilds.contains(Woj.resultToName(result)) == false) {
                generatedChilds.add(Woj.resultToName(result));
                childs.add(new Woj(Woj.resultToName(result), result.code, result.okreg, this));
            }
        }
    }

    public String header() {
        return " <header>\n<h1>" + whereAreResults() + "\n" + "\n" +
                "\n" +
                "</h1></header>\n" +
                "\n";
    }

    public String whereAreResults() {
        return "Wyniki Polska";

    }

}
