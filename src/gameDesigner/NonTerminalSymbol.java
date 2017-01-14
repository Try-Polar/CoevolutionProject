package gameDesigner;

import java.util.LinkedList;
import java.util.List;

public class NonTerminalSymbol extends Symbol {
	
	List<Symbol> children = new LinkedList<Symbol>();
	
	public NonTerminalSymbol (String symbolName) {
		super(symbolName);
	}
	
	public void addChild (Symbol child) {
		children.add(child);
	}

}
