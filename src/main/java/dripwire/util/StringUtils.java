package dripwire.util;

public class StringUtils {

    private static final String[] RAINBOW = { "§4", "§c", "§6", "§e", "§a", "§2", "§b", "§3", "§9", "§1", "§5", "§d" };

    /**
     * Returns a rainbow-colored string.
     * @param string The string to color.
     * @param width The amount of character per color.
     * @return The colored string.
     */
    public static String rainbow(String string, int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == ' ') sb.append(c);
            else sb.append(RAINBOW[i/width % RAINBOW.length]).append(c);
        }
        return sb.toString();
    }

    /**
     * Returns a rainbow-colored string.
     * @param string The string to color.
     * @return The colored string.
     */
    public static String rainbow(String string) {
        return rainbow(string, 1);
    }

    /**
     * Repeat a string {@code times} times.
     * @param str The string to repeat.
     * @param times The amount of times to repeat.
     * @return The repeated string.
     */
    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
}
