package org.netbeans.modules.clojure.lexer;

import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;

@LanguageRegistration(mimeType = "text/x-clojure")
public class ClojureLanguage extends DefaultLanguageConfig {

    @Override
    public Language getLexerLanguage() {
	return new ClojureLanguageHierarchy().language();
    }

    @Override
    public String getDisplayName() {
	return "Clojure";
    }

}
