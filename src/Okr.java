public class Okr extends Adm {
    public Okr(String s, String code, int okreg, Woj woj) {
        super(s,code,okreg,woj);

    }

    protected boolean isResultInSameAdm(String code1, int nrOkr1, String code2, int nrOkr2) {
        System.out.println(nrOkr1+"     "+nrOkr2+"\n");
        return nrOkr1 == nrOkr2;
    }

    protected static String resultToName(Result result) {
        return "Okręg "+new Integer(result.okreg);
    }

    public String whereAreResults(){
        return "Wyniki ";

    }

    public void generateChilds() {
        for (Result result : results) {

            if (generatedChilds.contains(Gmi.resultToName(result)) == false) {
                generatedChilds.add(Gmi.resultToName(result));
                childs.add(new Gmi(Gmi.resultToName(result), result.code, result.okreg, this));
            }
        }
    }

}
