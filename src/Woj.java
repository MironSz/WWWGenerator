import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

public class Woj extends Adm {
    private static Map<String, String> codeToName = new HashMap<String, String>() {{

        put("02", "DOLNOŚLĄSKIE");
        put("04", "KUJAWSKO-POMORSKIE");
        put("06", "LUBELSKIE");
        put("08", "LUBUSKIE");
        put("10", "ŁÓDZKIE");
        put("12", "MAŁOPOLSKIE");
        put("14", "MAZOWIECKIE");
        put("16", "OPOLSKIE");
        put("18", "PODKARPACKIE");
        put("20", "PODLASKIE");
        put("22", "POMORSKIE");
        put("24", "ŚLĄSKIE");
        put("26", "ŚWIĘTOKRZYSKIE");
        put("28", "WARMIŃSKO-MAZURSKIE");
        put("30", "WIELKOPOLSKIE");
        put("32", "ZACHODNIOPOMORSKIE");
    }};

    protected static String resultToName(Result result) {
        return codeToName.get(result.code.substring(0, 2));
    }

    protected boolean isResultInSameAdm(String code1, int nrOkr1, String code2, int nrOkr2) {
        return code1.substring(0, 2).equals(code2.substring(0, 2));
    }


    public Woj(String s, String code, int okreg, Adm parent) {
        super(s, code, okreg, parent);


        System.out.println(s);
    }


    public void generateChilds() {
        for (Result result : results) {

            if (generatedChilds.contains(Okr.resultToName(result)) == false) {
                generatedChilds.add(Okr.resultToName(result));
                childs.add(new Okr(Okr.resultToName(result), result.code, result.okreg, this));
            }
        }
    }


    public String whereAreResults() {
        return "Wyniki województwo";

    }

}
