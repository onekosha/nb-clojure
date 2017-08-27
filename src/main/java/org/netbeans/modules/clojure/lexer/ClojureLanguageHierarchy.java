package org.netbeans.modules.clojure.lexer;

import java.util.*;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

public class ClojureLanguageHierarchy extends LanguageHierarchy<ClojureTokenId> {

    private static final List<ClojureTokenId> tokens = new ArrayList<ClojureTokenId>();
    private static final Map<Integer, ClojureTokenId> idToToken = new HashMap<Integer, ClojureTokenId>();

    static {
        TokenType[] tokenTypes = TokenType.values();
        for (TokenType tokenType : tokenTypes) {
            tokens.add(new ClojureTokenId(tokenType.name(), tokenType.category, tokenType.id));
        }
        tokens.forEach((token) -> {
            idToToken.put(token.ordinal(), token);
        });
    }

    static synchronized ClojureTokenId getToken(int id) {
        return idToToken.get(id);
    }

    @Override
    protected synchronized Collection<ClojureTokenId> createTokenIds() {
        return tokens;
    }

    @Override
    protected synchronized Lexer<ClojureTokenId> createLexer(LexerRestartInfo<ClojureTokenId> info) {
        return new ClojureNBLexer(info);
    }

    @Override
    protected String mimeType() {
        return "text/x-clojure";
    }
}