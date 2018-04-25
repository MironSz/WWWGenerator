import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Adm {
    static LinkedList<String> candidates;
    String cssPath = "css/main";
    String destination = "/home/miron/IdeaProjects/WWWGenerator/src/results/";
    Adm parent;
    String code;
    int nrOkr;
    LinkedList<Adm> childs;
    String name;
    String link;
    LinkedList<Result> results;
    Result summaryResult;
    Set<String> generatedChilds;
    LinkedList<Adm> path;

    protected Adm() {
    }


    protected boolean isResultInSameAdm(String code1, int nrOkr1, String code2, int nrOkr2) {
        return true;
    }

    protected static String resultToName(Result result) {
        return "Undefined";
    }

    public void generateResults() {
        for (Result result : parent.results) {
            if (isResultInSameAdm(result.code, result.okreg, code, nrOkr)) {
                results.add(result);
                result.add(result);
                if (summaryResult == null)
                    summaryResult = result;
                else
                    summaryResult = summaryResult.add(result);
            }
        }
    }

    public void generateChilds() {

    }

    public Adm(String nazwa, String code, int nrOkr, Adm parent) {
        this.parent = parent;
        results = new LinkedList<>();
        childs = new LinkedList<>();
        generatedChilds = new HashSet<>();
        name = nazwa;
        this.code = code;
        this.nrOkr = nrOkr;
        this.parent = parent;
        link = parent.link + "/" + nazwa.replace(" ", "");
        new File(destination + link.replace(" ", "")).mkdir();
        cssPath = "../" + parent.cssPath;
        generateResults();
        generateChilds();
        createHtml();
    }


    public String head() {
        return String.format("<!doctype html>\n" +
                "<html class=\"no-js\" lang=\"\">\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<meta name=\"description\" content=\"\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + "%s" +
                "<title>%s</title>\n" +

                "\n" +
                "\n" +
                "<link rel=\"stylesheet\" href=\"%s\">\n" +
                "</head>", chart(), "Wybory prezydenta Rzeczypospolitej 2000", cssPath + ".css");
    }

    public String candidatesResultToString() {
        String result;
        result = "<tr>\n" +
                "<th>Kandydat</th>\n" +
                "<th>Liczba oddanych głosów na kandydata</th>\n" +
                "<th>Procent uzyskanych głosów</th>\n" +
                "</tr>\n";
        for (int i = 0; i < candidates.size(); i++) {
            String candidateResult = " <tr>\n<td>" + candidates.get(i) + "</td>\n<td>" + summaryResult.candidatesVotes.get(i) + "</td>\n<td>"
                    + String.format("%.2f", 100 * new Float(summaryResult.candidatesVotes.get(i)) / new Float(summaryResult.allVotes + 1)) +

                    "%</td>\n</tr>\n";
            result += candidateResult;
        }

        return result;
    }

    public String smallerAdmList() {
        String result = new String();
        for (Adm adm : childs) {
            result = result + "<li><a href=\"" + name.replace(" ", "") + "/" +
                    adm.name.replace(" ", "") + ".html\">" +
                    adm.name + "</a></li>\n";
        }
        return result;
    }

    public String path() {
        String result = new String();
        String help = new String();
        Adm adm = this;
        while (adm != null) {
            result = result + "<li><a href=\"" + help + adm.name.replace(" ", "")
                    + ".html\">" + adm.name.replace("index", "Polska") + "</a></li>" + "\n";
            help += "../";
            adm = adm.parent;
        }
        System.out.println(name + "     " + result);
        return result;
    }

    public String statisticToString() {
        return new String();
    }

    public String whereAreResults() {
        return "Not Defined";

    }

    public String header() {
        String result = "<header>\n<h1>";


        result = result + whereAreResults() + "\n" + name +
                "\n</h1>\n";

        result = result + "<nav><ul>" + path() + "</ul></nav></header>";

        return result;
    }

    public String body() {
        return "<body>\n" +
                header() + "<div>" +
                "\n" +
                "<section class=\"flex-container\">\n" +
                "\n" +
                "<section><div id=\"resultDiv\">\n" +
                "<table id=\"resultTable\">" + candidatesResultToString() + "</table> </div></section>\n\n" +

                "<section>\n" +
                "<div id=\"piechart\"></div>\n" +
                " </section></section>" +
                "<nav>" +
//                "<h3>Wyniki w :</h3>\n" +
                "<ul>" +
                smallerAdmList() + "</ul>\n" +
                "</nav>\n" +
                "\n" +
                "<footer>\n" +
                "\n" +
                "</footer>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }


    public String toString() {
        return head() + body();
    }

    public void createHtml() {
        try {
            path();
            PrintWriter writer = new PrintWriter(destination + link + ".html", "UTF-8");
            System.out.println(destination + link + ".html");//, "UTF-8");
            writer.println(this.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String chart() {
        String result = new String();
        String candS = new String();
        for (int i = 0; i < candidates.size(); i++) {
            candS = candS + "              ['" + candidates.get(i) + "', " +
                    String.format("%.2f", 100 * new Float(summaryResult.candidatesVotes.get(i)) / new Float(summaryResult.allVotes + 1)) +
                    "]";
            if (i + 1 < candidates.size())
                candS = candS + ",";
            candS = candS + "\n";
        }
        result = " <script src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                "    <script>\n" +
                "      google.charts.load('current', {'packages':['corechart']});\n" +
                "      google.charts.setOnLoadCallback(drawChart);\n" +
                "\n" +
                "      function drawChart() {\n" +
                "\n" +
                "        var data = google.visualization.arrayToDataTable([\n" +
                "          ['Kandydat', 'Procent uzyskanych głosów'],\n" +
                candS +

                "        ]);\n" +
                "\n" +
                "        var options = {\n" +
                "          title: 'Wizualizacja wyników'\n" +
                "        };\n" +
                "\n" +
                "        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n" +
                "\n" +
                "        chart.draw(data, options);\n" +
                "      }\n" +
                "    </script>";

        return result;
    }


}
