package com.github.davidmoten.aws.lw.client.xml.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.github.davidmoten.aws.lw.client.internal.util.Preconditions;

public final class Xml {

    private final String name;

    private final Xml parent;

    private Map<String, String> attributes = new HashMap<>();

    private List<Xml> children = new ArrayList<>();

    private String content;

    private boolean prelude = true;

    private Xml(String name) {
        this(name, null);
    }

    private Xml(String name, Xml parent) {
        checkPresent(name, "name");
        this.name = name;
        this.parent = parent;
    }

    public static Xml create(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Xml excludePrelude() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Xml element(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Xml e(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Xml attribute(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Xml a(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Xml content(String content) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Xml up() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void checkPresent(String s, String name) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " must be non-null and non-blank");
        }
    }

    private String toString(String indent) {
        StringBuilder b = new StringBuilder();
        if (indent.length() == 0 && prelude) {
            b.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        }
        // TODO encode attributes and content for xml
        String atts = attributes.entrySet().stream().map(entry -> " " + entry.getKey() + "=\"" + encodeXml(entry.getValue(), true) + "\"").collect(Collectors.joining());
        b.append(String.format("%s<%s%s>", indent, name, atts));
        if (content != null) {
            b.append(encodeXml(content, false));
            b.append(String.format("</%s>", name));
            if (parent != null) {
                b.append("\n");
            }
        } else {
            b.append("\n");
            for (Xml xml : children) {
                b.append(xml.toString(indent + "  "));
            }
            b.append(String.format("%s</%s>", indent, name));
            if (parent != null) {
                b.append("\n");
            }
        }
        return b.toString();
    }

    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static final Map<Integer, String> CONTENT_CHARACTER_MAP = createContentCharacterMap();

    private static final Map<Integer, String> ATTRIBUTE_CHARACTER_MAP = createAttributeCharacterMap();

    private static Map<Integer, String> createContentCharacterMap() {
        Map<Integer, String> m = new HashMap<>();
        m.put((int) '&', "&amp;");
        m.put((int) '>', "&gt;");
        m.put((int) '<', "&lt;");
        return m;
    }

    private static Map<Integer, String> createAttributeCharacterMap() {
        Map<Integer, String> m = new HashMap<>();
        m.put((int) '\'', "&apos;");
        m.put((int) '\"', "&quot;");
        return m;
    }

    private static String encodeXml(CharSequence s, boolean isAttribute) {
        StringBuilder b = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i);
            if (c >= 0xd800 && c <= 0xdbff && i + 1 < len) {
                // UTF16 decode
                c = ((c - 0xd7c0) << 10) | (s.charAt(++i) & 0x3ff);
            }
            if (c < 0x80) {
                // ASCII range: test most common case first
                if (c < 0x20 && (c != '\t' && c != '\r' && c != '\n')) {
                    // Illegal XML character, even encoded. Skip or substitute
                    // Unicode replacement character
                    b.append("&#xfffd;");
                } else {
                    String r = CONTENT_CHARACTER_MAP.get(c);
                    if (r != null) {
                        b.append(r);
                    } else if (isAttribute) {
                        String r2 = ATTRIBUTE_CHARACTER_MAP.get(c);
                        if (r2 != null) {
                            b.append(r2);
                        } else {
                            b.append((char) c);
                        }
                    } else {
                        b.append((char) c);
                    }
                }
            } else if ((c >= 0xd800 && c <= 0xdfff) || c == 0xfffe || c == 0xffff) {
                // Illegal XML character, even encoded. Skip or substitute
                // Unicode replacement character
                b.append("&#xfffd;");
            } else {
                b.append("&#x");
                b.append(Integer.toHexString(c));
                b.append(';');
            }
        }
        return b.toString();
    }
}
