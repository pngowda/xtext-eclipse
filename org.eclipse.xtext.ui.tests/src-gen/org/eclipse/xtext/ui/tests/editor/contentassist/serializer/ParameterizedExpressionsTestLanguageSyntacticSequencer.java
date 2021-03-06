/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.eclipse.xtext.ui.tests.editor.contentassist.services.ParameterizedExpressionsTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class ParameterizedExpressionsTestLanguageSyntacticSequencer extends AbstractSyntacticSequencer {

	protected ParameterizedExpressionsTestLanguageGrammarAccess grammarAccess;
	protected AbstractElementAlias match_FunctionDeclaration_SemicolonKeyword_1_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (ParameterizedExpressionsTestLanguageGrammarAccess) access;
		match_FunctionDeclaration_SemicolonKeyword_1_q = new TokenAlias(false, true, grammarAccess.getFunctionDeclarationAccess().getSemicolonKeyword_1());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_FunctionDeclaration_SemicolonKeyword_1_q.equals(syntax))
				emit_FunctionDeclaration_SemicolonKeyword_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     ';'?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) 'function' '(' ')' (ambiguity) (rule start)
	 *     body=Block (ambiguity) (rule end)
	 *     generator?='*' '(' ')' (ambiguity) (rule end)
	 *     name=Identifier '(' ')' (ambiguity) (rule end)
	 */
	protected void emit_FunctionDeclaration_SemicolonKeyword_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
