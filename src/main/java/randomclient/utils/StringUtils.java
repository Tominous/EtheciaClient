package randomclient.utils;

public class StringUtils {
    private static final String[] VOWELS = new String[] {"A", "E", "I", "O", "U"};
    public static String replaceVowels(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c1 : s.toCharArray()) {
            String s2 = new String(new char[] {c1});
            for (String s3 : VOWELS) {
                s2 = s2.replace(s3, "e");
                s2 = s2.replace(s3.toLowerCase(), "e");
            }
            sb.append(s2);
        }
        return sb.toString();
    }
}
