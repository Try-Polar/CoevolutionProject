package gameDesigner;

public class InterchangableSymbol extends Symbol {
	
	String[] classStrings; 
	int freeSlot = 0;
	
	InterchangableSymbol (String symbolName, String[] symbolContent) {
		super(symbolName);
		classStrings = symbolContent;
		freeSlot = symbolContent.length;
	}
}
