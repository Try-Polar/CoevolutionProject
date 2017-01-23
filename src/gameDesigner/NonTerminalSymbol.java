package gameDesigner;

import java.util.LinkedList;
import java.util.List;

public class NonTerminalSymbol extends Symbol {
	
	List<Symbol> children = new LinkedList<Symbol>();
	boolean repeatable;
	boolean optional;
	boolean choice;
	
	public NonTerminalSymbol (String symbolName, boolean repeater, boolean option, boolean chooser) {
		super(symbolName);
		repeatable = repeater;
		optional = option;
		choice = chooser;
	}
	
	public void addChild (Symbol child) {
		children.add(child);
	}

}
