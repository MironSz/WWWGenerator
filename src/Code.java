import java.util.Map;

public class Code {
    private static Map<String, String> keyToWName;
    private static Map<String, String> keyToPName;
    private String code;

    public static void addW(String code, String wName) {
        keyToWName.put(code, wName);
    }

    public static void addP(String code, String pName) {
        keyToPName.put(code, pName);
    }

    public Code(String code) {
        this.code = code;
    }

    public String getWCode() {
        return code;
    }

    public String getW() {
        return code.substring(0, 2);
    }

    public String getP() {
        return code.substring(0, 4);
    }

    public boolean sameW(Code c) {
        return c.getW().equals(getW());
    }

    public boolean sameP(Code c) {
        return c.getP().equals(getP());
    }

    public String getWName() {
        return keyToWName.get(getW());
    }

    public String getPName() {
        return keyToPName.get(getP());
    }

    public boolean isInside(Code c){
        return (c.code.contains(code));
    }
}
