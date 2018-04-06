import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Page {
    int type; //kraj/woj/okr/gmina   0/1/2/3
    Result resultsonPage;
    String name;
    String link;
    Code code;
    List<Page> childs;
    List<Result> results;
    Page parent;
    int allVotes;   


    public Page(Code code){
        results = new LinkedList<>();
        childs = new LinkedList<>();
        if(type != 0){ //wygeneruj resulty w jednostce
            for(Result result:parent.results){
//                if(result.code.isInside(code)){
                    results.add(result);
//                }
            }
            parent.childs.add(this);
        }
        else {
            try {
                FileInputStream fstream = new FileInputStream("C:\\Users\\Miron\\IdeaProjects\\WWWGenerator\\src\\resources\\sjie2-wqcgv.csv");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    System.out.println(strLine);
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String toString(){
        return new String();
    }

}
