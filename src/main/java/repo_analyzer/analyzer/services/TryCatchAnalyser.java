package repo_analyzer.analyzer.services;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import repo_analyzer.analyzer.visitors.ErlangParser;

public class TryCatchAnalyser {
	public void analyseCatchError(ParserRuleContext ctx) {
		int childrenSize = ctx.children.size();
		
		while(childrenSize > 0) {
			analyseCatchError(ctx.children.get(childrenSize - 1));
			
			childrenSize--;
		}
	}
	
	public void analyseCatchError(ParseTree parseTree) {
		if(parseTree instanceof ErlangParser.AtomOrVarContext) {
			analyseCatchError((ErlangParser.AtomOrVarContext) parseTree);
		}
		
		if(parseTree instanceof ErlangParser.TryClausesContext) {
			analyseCatchError((ParserRuleContext) parseTree);
		}
		
		if(parseTree instanceof ErlangParser.TryClauseContext) {
			analyseCatchError((ParserRuleContext) parseTree);
		}
	}
		
	
	public void analyseCatchError(ErlangParser.AtomOrVarContext ctx) {
		int childrenSize = ctx.children.size();

		// Guardar as ocorrencias 
		String errorType = ctx.getText();
	}
}
