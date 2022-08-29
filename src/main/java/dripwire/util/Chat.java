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
        TextComponent evComp = null;

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
//                    Dripwire.INSTANCE.getLogger().warning("Unescaped & with no terminating ; in text: " + text);
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
                    case "gt" -> {
                        buf.append('>');
                        continue;
                    }
                    case "lt" -> {
                        buf.append('<');
                        continue;
                    }
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
            else if (c == '<') {
//                if (text.charAt(i+1) != '@') {
//                    buf.append(c);
//                    continue;
//                }
                // {@run ; /tpaccept TestUser ; Accept@}
                // <run /tpaccept TestUser>Accept</run>
                // <run="/ping" hover="test">Ping</run>
                int j = i + 1;
                boolean found = false;
                StringBuilder buf2 = new StringBuilder();
                String tagName;
                String arg;
                int innerTagsOfSameType = 0;

                // find the end of the tag & get argument
                for (; j < text.length(); j++) {
                    char c2 = text.charAt(j);
                    if (c2 == '>') {
                        found = true;
                        i = j;
                        break;
                    }
                    buf2.append(c2);
                }

                if (!found) {
                    Dripwire.INSTANCE.getLogger().warning("Unescaped < with no terminating > in text: " + text);
                    buf.append(c);
                    continue;
                }

                String tag = buf2.toString();
                buf2.setLength(0);
                String[] tagSplit = tag.split(" ", 2);
                tagName = tagSplit[0];
                arg = tagSplit.length > 1 ? tagSplit[1] : "";

                // Get content inside of String
                found = false;
                for (j += 1; j < text.length(); ++j) {
                    char c2 = text.charAt(j);
                    try {
                        if (text.substring(j, j+tagName.length()+1).equals("<" + tagName)) {
                            innerTagsOfSameType++;
                        }
                        else if (text.substring(j, j+tagName.length()+2).equals("</" + tagName)) {
                            innerTagsOfSameType--;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    if (innerTagsOfSameType < 0) {
                        found = true;
                        i = j + tagName.length() + 2;
                        break;
                    }
                    buf2.append(c2);
                }

                if (!found) {
                    Dripwire.INSTANCE.getLogger().warning("No closing tag for '" + tagName + "' in text: " + text);
                    buf.append(c);
                    continue;
                }

                System.out.println("Adding component with arg " + arg);
                System.out.println("parsing inner...");
                evComp = parse(buf2.toString(), color);
                System.out.println("parsing inner done");
                switch(tagName) {
                    case "run" -> {
                        System.out.println("component before run: " + evComp);
                        evComp = evComp.clickEvent(ClickEvent.runCommand(arg));
                        System.out.println("component after run: " + evComp);
                    }
                    case "suggest" -> {
                        evComp = evComp.clickEvent(ClickEvent.suggestCommand(arg));
                    }
                    case "url" -> {
                        evComp = evComp.clickEvent(ClickEvent.openUrl(arg));
                    }
                    case "hover" -> {
                        System.out.println("before hover: " + evComp);
                        evComp = evComp.hoverEvent(HoverEvent.showText(parse(arg)));
                        System.out.println("after hover: " + evComp);
                    }
                    default -> {
                        Dripwire.INSTANCE.getLogger().warning("Unknown tag: " + tagName);
                    }
                }
            }
            else {
                buf.append(c);
            }

            if (styleChanged || i >= text.length()-1) {
                if (buf.length() > 0) {
                    components.add(Chat.text(buf.toString(), color, decoration));
                    if (evComp != null) {
                        components.add(evComp);
                    }
                    buf.setLength(0);
                }
                evComp = null;
                color = newColor;
                decoration = newDecoration;
                styleChanged = false;
            }
        }

        if (components.size() == 1) return components.get(0);

        TextComponent res = Component.empty();
        for (TextComponent tc : components) {
            System.out.println("Adding component " + tc.toString());

            res = res.append(tc);
        }
        System.out.println("Result: " + res.toString());
        return res;
    }
    public static TextComponent parse(String text) {
        return parse(text, null);
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

    public static TextComponent interactive(String text, @Nullable ClickEvent ce, @Nullable HoverEvent he) {
        TextComponent tc = parse(text);
        if (ce != null) {
            tc = tc.clickEvent(ce);
        }
        if (he != null) {
            tc = tc.hoverEvent(he);
        }
        return tc;
    }
}
