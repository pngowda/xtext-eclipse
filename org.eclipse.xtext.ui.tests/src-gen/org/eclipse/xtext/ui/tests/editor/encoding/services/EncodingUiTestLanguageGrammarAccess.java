/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.encoding.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class EncodingUiTestLanguageGrammarAccess extends AbstractGrammarElementFinder {
	
	public class ModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ui.tests.editor.encoding.EncodingUiTestLanguage.Model");
		private final Assignment cWordsAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cWordsWordParserRuleCall_0 = (RuleCall)cWordsAssignment.eContents().get(0);
		
		//Model:
		//	words+=Word*;
		@Override public ParserRule getRule() { return rule; }
		
		//words+=Word*
		public Assignment getWordsAssignment() { return cWordsAssignment; }
		
		//Word
		public RuleCall getWordsWordParserRuleCall_0() { return cWordsWordParserRuleCall_0; }
	}
	public class WordElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ui.tests.editor.encoding.EncodingUiTestLanguage.Word");
		private final Assignment cValueAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cValueLEXEMETerminalRuleCall_0 = (RuleCall)cValueAssignment.eContents().get(0);
		
		//Word:
		//	value=LEXEME;
		@Override public ParserRule getRule() { return rule; }
		
		//value=LEXEME
		public Assignment getValueAssignment() { return cValueAssignment; }
		
		//LEXEME
		public RuleCall getValueLEXEMETerminalRuleCall_0() { return cValueLEXEMETerminalRuleCall_0; }
	}
	
	
	private final ModelElements pModel;
	private final WordElements pWord;
	private final TerminalRule tLEXEME;
	private final TerminalRule tWS;
	private final TerminalRule tANY_OTHER;
	
	private final Grammar grammar;

	@Inject
	public EncodingUiTestLanguageGrammarAccess(GrammarProvider grammarProvider) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.pModel = new ModelElements();
		this.pWord = new WordElements();
		this.tLEXEME = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ui.tests.editor.encoding.EncodingUiTestLanguage.LEXEME");
		this.tWS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ui.tests.editor.encoding.EncodingUiTestLanguage.WS");
		this.tANY_OTHER = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ui.tests.editor.encoding.EncodingUiTestLanguage.ANY_OTHER");
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.eclipse.xtext.ui.tests.editor.encoding.EncodingUiTestLanguage".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	

	
	//Model:
	//	words+=Word*;
	public ModelElements getModelAccess() {
		return pModel;
	}
	
	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}
	
	//Word:
	//	value=LEXEME;
	public WordElements getWordAccess() {
		return pWord;
	}
	
	public ParserRule getWordRule() {
		return getWordAccess().getRule();
	}
	
	//terminal LEXEME:
	//	'a'..'z' | 'A'..'Z' | '0'..'9' | '�' | '�' | '�' | '�' | '�' | '�' | '�'*;
	public TerminalRule getLEXEMERule() {
		return tLEXEME;
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return tWS;
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return tANY_OTHER;
	}
}