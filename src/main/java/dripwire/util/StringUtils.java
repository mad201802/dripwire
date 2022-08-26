package dripwire.util;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    private static final String[] RAINBOW = { "§4", "§c", "§6", "§e", "§a", "§2", "§b", "§3", "§9", "§1", "§5", "§d" };
    public static String rainbow(String string, int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == ' ') sb.append(c);
            else sb.append(RAINBOW[i/width % RAINBOW.length]).append(c);
        }
        return sb.toString();
    }
    public static String rainbow(String string) {
        return rainbow(string, 1);
    }

    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
}
