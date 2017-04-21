package gameDesigner;

public class TerminalSymbol extends Symbol {
	
	String content;
	NonTerminalSymbol parent;

	public TerminalSymbol (String symbolName, String symbolContent, NonTerminalSymbol par) {
		super(symbolName);
		content = symbolContent;
		parent = par;
	}
	
	public TerminalSymbol (String symbolName, String symbolContent) {
		super(symbolName);
		content = symbolContent;
		parent = null;
	}
}
