public class Gmi extends Adm {
    public Gmi(String s, String code, int okreg, Adm adm) {
        super(s,code,okreg,adm);
    }

    protected static String resultToName(Result result) {
        return result.gmina;
    }

    public String whereAreResults(){
        return "Wyniki  gminia";
    }


    protected boolean isResultInSameAdm(String code1, int nrOkr1, String code2, int nrOkr2) {
        return code1.equals(code2);
    }

}
