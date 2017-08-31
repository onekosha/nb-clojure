package org.netbeans.modules.clojure.lexer;

import org.netbeans.api.lexer.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import src.main.antlr4.ClojureLexer;

class ClojureNBLexer implements Lexer<ClojureTokenId> {

    private final LexerRestartInfo<ClojureTokenId> info;

    private final ClojureLexer lexer;

    public ClojureNBLexer(LexerRestartInfo<ClojureTokenId> info) {
        this.info = info;
        AntlrCharStream charStream = new AntlrCharStream(info.input(), "ClojureEditor");
        lexer = new ClojureLexer(charStream);
    }

    @Override
    public Token<ClojureTokenId> nextToken() {
        org.antlr.v4.runtime.Token token = lexer.nextToken();
        if (token.getType() != lexer.EOF) {
            ClojureTokenId tokenId = ClojureLanguageHierarchy.getToken(token.getType());
            return info.tokenFactory().createToken(tokenId);
        }
        return null;
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }

}
