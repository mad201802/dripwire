package dripwire.util;

import dripwire.Dripwire;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Chat {
    public static class Color {
        public static final TextColor ORANGE = TextColor.color(0xFF9439);
        public static final TextColor GREEN = TextColor.color(0x4EFF6E);
        public static final TextColor RED = TextColor.color(0xdd3322);
        public static final TextColor WHITE = TextColor.color(0xffffff);
        public static final TextColor BLACK = TextColor.color(0x000000);
        public static final TextColor GRAY = TextColor.color(0xaaaaaa);
        public static final TextColor YELLOW = TextColor.color(0xffff22);
        public static final TextColor BLUE = TextColor.color(0x22aaff);
        public static final TextColor PURPLE = TextColor.color(0xff22ff);
        public static final TextColor LIGHT_PURPLE = TextColor.color(0xCF83FF);
    }


    public static TextComponent parse(String text, @Nullable TextColor defaultColor) {
        ArrayList<TextComponent> components = new ArrayList<>();

        StringBuilder buf = new StringBuilder();
        TextDecoration decoration = null;
        TextColor color = defaultColor;
        boolean styleChanged = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            System.out.println("CHAR: " + c);

            TextDecoration newDecoration = null;
            TextColor newColor = null;

            // parse color codes (§rrggbb)
            if (c == '§') {
                // allow escaping of § with §§
                if (text.charAt(i+1) == '§') {
                    buf.append('§');
                    i++;
                } else if (i + 6 < text.length()) {
                    int colorCode = Integer.parseInt(text.substring(i + 1, i + 6), 16);
                    newColor = TextColor.color(colorCode);
                    i += 6;
                    styleChanged = true;
                }

            // parse symbols (&symbol;)
            }
            else if (c == '&') {
                StringBuilder symbol = new StringBuilder("");
                // allow escaping of & with &&
                if (i+1 < text.length() && text.charAt(i+1) == '&') {
                    buf.append(c);
                    i++;
                    continue;
                }
                boolean found = false;
                for (int j = i+1; j < text.length(); ++j) {
                    char c2 = text.charAt(j);
                    if (c2 == ';') {
                        found = true;
                        i=j;
                        break;
                    }
                    symbol.append(c2);
                }
                if (!found) {
                    Dripwire.INSTANCE.getLogger().warning("Unescaped & with no terminating ; in text: " + text);
                    buf.append(c);
                    continue;
                }
                System.out.println(symbol.toString());
                switch (symbol.toString()) {
                    case "reset" -> {
//                        newDecoration = null;
                        newColor = defaultColor;
                    }
                    case "null" -> {} // Can be used to escape, for example between { and @
                    case "bold" -> newDecoration = TextDecoration.BOLD;
                    case "italic" -> newDecoration = TextDecoration.ITALIC;
                    case "underline" -> newDecoration = TextDecoration.UNDERLINED;
                    case "strikethrough" -> newDecoration = TextDecoration.STRIKETHROUGH;
                    case "obfuscated" -> newDecoration = TextDecoration.OBFUSCATED;
                    case "red" -> newColor = Color.RED;
                    case "green" -> newColor = Color.GREEN;
                    case "orange" -> newColor = Color.ORANGE;
                    case "white" -> newColor = Color.WHITE;
                    case "black" -> newColor = Color.BLACK;
                    case "gray" -> newColor = Color.GRAY;
                    case "yellow" -> newColor = Color.YELLOW;
                    case "blue" -> newColor = Color.BLUE;
                    case "purple" -> newColor = Color.PURPLE;
                    case "lightpurple" -> newColor = Color.LIGHT_PURPLE;
                    default ->  Dripwire.INSTANCE.getLogger().warning("Unknown symbol: " + symbol);
                }
//                components.add(getComponent(buf.toString(), color, decoration));
//                buf.setLength(0);
                styleChanged = true;
            }
            else if (c == '{') {
                if (text.charAt(i+1) != '@') {
                    buf.append(c);
                    continue;
                }
                // {@run ; /tpaccept TestUser ; Accept@}
                int j = i + 2;
                boolean found = false;
                StringBuilder buf2 = new StringBuilder();
                for (; j < text.length(); ++j) {
                    char c2 = text.charAt(j);
                    if (c2 == '@' && j+1 < text.length() && text.charAt(j+1) == '}') {
                        found = true;
                        i = j+1;
                        break;
                    }
                    buf2.append(c2);
                }
                String[] args = buf2.toString().split(" ; ");
                System.out.println("Adding component with args: " + Arrays.toString(args));
                TextComponent tc = parse(args[2], color);
                switch(args[0]) {
                    case "run" -> {
                        tc = tc.clickEvent(ClickEvent.runCommand(args[1]));
                    }
                    case "suggest" -> {
                        tc = tc.clickEvent(ClickEvent.suggestCommand(args[1]));
                    }
                    case "url" -> {
                        tc = tc.clickEvent(ClickEvent.openUrl(args[1]));
                    }
                    case "hover" -> {
                        tc = tc.hoverEvent(HoverEvent.showText(parse(args[1], color)));
                    }
                    default -> {
                        Dripwire.INSTANCE.getLogger().warning("Unknown command: " + args[0]);
                    }
                }
                components.add(tc);
            }
            else {
                buf.append(c);
            }

            if (styleChanged || i >= text.length()-1) {
                if (buf.length() > 0) {
                    components.add(Chat.text(buf.toString(), color, decoration));
                    buf = new StringBuilder();
                }
                color = newColor;
                decoration = newDecoration;
                styleChanged = false;
            }
        }
        TextComponent res = Component.empty();
        for (TextComponent tc : components) {
            res = res.append(tc);
        }
        return res;
    }

    public static TextComponent text(String text, @Nullable TextColor color, @Nullable TextDecoration decoration) {
        return decoration == null
                ? Component.text(text, color)
                : Component.text(text, color, decoration);
    }
    public static TextComponent text(String text, TextColor color) {
        return text(text, color, null);
    }
    public static TextComponent text(String text, TextDecoration decoration) {
        return text(text, null, decoration);
    }
    public static TextComponent text(String text) {
        return text(text, null, null);
    }

//    private static String clickable(ClickEvent.Action type, String value) {
//        if (type == ClickEvent)
//    }
}
