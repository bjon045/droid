package bioroid.utils;

import java.util.ArrayList;
import java.util.List;

import bioroid.Constants;

public class StringUtils {

    public static List<String> breakUpByWords(String string, int maxWdith) {
        if (isBlank(string)) {
            return null;
        }
        String[] words = string.split(" ");
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<String>();
        for (String s : words) {
            if (s.equals(Constants.LINE_BREAK)) {
                result.add(sb.toString());
                sb.setLength(0);
                continue;
            }
            if ((sb.length() + s.length()) > maxWdith) {
                result.add(sb.toString());
                sb.setLength(0);
            }
            sb.append(s).append(" ");
        }
        result.add(sb.toString());
        return result;
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if ((cs == null) || ((strLen = cs.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }

    public static boolean notEquals(CharSequence cs1, CharSequence cs2) {
        return !equals(cs1, cs2);
    }

}
