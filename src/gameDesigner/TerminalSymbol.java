package gameDesigner;

public class TerminalSymbol extends Symbol {
	
	String content;

	public TerminalSymbol (String symbolName, String symbolContent) {
		super(symbolName);
		content = symbolContent;
	}
}
