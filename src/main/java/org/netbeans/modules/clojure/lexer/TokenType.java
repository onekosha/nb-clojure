package org.netbeans.modules.clojure.lexer;

public enum TokenType {
    T__0(1, "bracket"),
    T__1(2, "bracket"),
    T__2(3, "bracket"),
    T__3(4, "bracket"),
    T__4(5, "bracket"),
    T__5(6, "bracket"),
    T__6(7, "bracket"),
    T__7(8, "comment"),
    T__8(9, "comment"),
    T__9(10, "comment"),
    T__10(11, "comment"),
    T__11(12, "comment"),
    T__12(13, "comment"),
    T__13(14, "comment"),
    T__14(15, "string"),
    T__15(16, "comment"),
    T__16(17, "comment"),
    T__17(18, "comment"),
    T__18(19, "comment"),
    T__19(20, "keyword"),
    STRING(21, "string"),
    FLOAT(22, "number"),
    HEX(23, "number"),
    BIN(24, "number"),
    LONG(25, "number"),
    BIGN(26, "number"),
    CHAR_U(27, "character"),
    CHAR_NAMED(28, "character"),
    CHAR_ANY(29, "character"),
    NIL(30, "keyword"),
    BOOLEAN(31, "keyword"),
    SYMBOL(32, "atom"),
    NS_SYMBOL(33, "atom"),
    PARAM_NAME(34, "builtin"),
    TRASH(35, "comment");

    public int id;
    public String category;
    public String text;

    private TokenType(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public static TokenType valueOf(int id) {
        TokenType[] values = values();
        for (TokenType value : values) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("The id " + id + " is not recognized");
    }
}
