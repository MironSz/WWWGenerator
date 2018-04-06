import java.util.LinkedList;
import java.util.List;

public class Result {
    String code;
    String powiat;
    String gmina;
    int okreg;
    String wojewodztwo;


    int allVotes;
    int correctVotes;
    int incorrectVotes;
    int authorizied;
    List<Integer> candidatesVotes;

    public Result(String s){
        s=s.replace("\"","");
        s.replace(" ","-");
        candidatesVotes = new LinkedList<>();
        String[] parts = s.split(",");
        okreg = new Integer(parts[0]);
        code = parts[1];
        gmina = parts[2];
        powiat = parts[3];
        authorizied = new Integer(parts[5]);
        allVotes = new Integer(parts[7]);
        incorrectVotes = new Integer(parts[8]);
        correctVotes = new Integer(parts[9]);
        for(int i=10; i<parts.length;i++){
            candidatesVotes.add(new Integer(parts[i]));
        }
    }


    private Result(){};
    public Result add(Result result){
        Result added = new Result();
        added.candidatesVotes = new LinkedList<>();
        added.correctVotes = correctVotes+result.correctVotes;
        added.incorrectVotes = incorrectVotes+result.incorrectVotes;
        added.allVotes = allVotes+result.allVotes;
        added.authorizied =authorizied+result.authorizied;
        for(int i = 0; i< candidatesVotes.size(); i ++){
            added.candidatesVotes.add(candidatesVotes.get(i)+result.candidatesVotes.get(i));
        }
        return added;
    }

}
