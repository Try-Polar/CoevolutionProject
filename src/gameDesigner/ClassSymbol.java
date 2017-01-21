package gameDesigner;

public class ClassSymbol extends Symbol {
	
	String[] classStrings; 
	int freeSlot = 0;
	
	ClassSymbol (String symbolName, String[] symbolContent) {
		super(symbolName);
		classStrings = symbolContent;
		freeSlot = symbolContent.length;
	}
}
