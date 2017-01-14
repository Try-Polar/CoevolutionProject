package gameDesigner;

import java.util.LinkedList;
import java.util.List;

public class GameDesigner {
	//Symbols
	//Declare Non-Terminals
	NonTerminalSymbol game;
	NonTerminalSymbol levelBlock;
	NonTerminalSymbol charMap;
	NonTerminalSymbol spriteType;
			
	//Declare Terminals	
	TerminalSymbol eol;
	TerminalSymbol indent;
	TerminalSymbol identifier;
	TerminalSymbol lambda;
	//strings
	TerminalSymbol game_class;
	TerminalSymbol LevelMapping;
	TerminalSymbol variableChar;
	TerminalSymbol greaterThan;
	TerminalSymbol avatar;
	TerminalSymbol wall;
	
	List<Symbol> gameSymbols = new LinkedList<Symbol>();
	
	public GameDesigner() {
		establishVGDLSymbols();
		basicGame();
	}
	
	private void establishVGDLSymbols() {
		//Declare Non-Terminals
		NonTerminalSymbol game = new NonTerminalSymbol();
		NonTerminalSymbol levelBlock = new NonTerminalSymbol();
		NonTerminalSymbol charMap = new NonTerminalSymbol();
		NonTerminalSymbol spriteType = new NonTerminalSymbol();
		
		//Declare Terminals		
		TerminalSymbol eol = new TerminalSymbol("\n");
		TerminalSymbol indent = new TerminalSymbol("	");
		TerminalSymbol identifier = new TerminalSymbol("IDENTIFIER");
		TerminalSymbol lambda = new TerminalSymbol("LAMBDA");
		//strings
		TerminalSymbol game_class = new TerminalSymbol("BasicGame"); //will not actually be terminal as it can expand to a number of things but this is simpler for now
		TerminalSymbol LevelMapping = new TerminalSymbol("LevelMapping");
		TerminalSymbol variableChar = new TerminalSymbol("CHAR"); //Just using "CHAR" as place holder for now
		TerminalSymbol greaterThan = new TerminalSymbol(" > ");
		TerminalSymbol avatar = new TerminalSymbol("avatar");
		TerminalSymbol wall = new TerminalSymbol("wall");
		
		//Add Children
		game.addChild(game_class);
		game.addChild(eol);
		game.addChild(indent);
		game.addChild(levelBlock);
		//g.sb
		//g.ib
		//g.tb
		levelBlock.addChild(charMap);
		charMap.addChild(spriteType);
		charMap.addChild(lambda);
		spriteType.addChild(identifier);
	}
	
	public void basicGame() {
		//game_class
			//LevelMapping
				//CHAR > IDENTIFIER
		int i = 0;
		gameSymbols.add(game);
		while (containsNonTerminals()) {
			Symbol currentSymbol = gameSymbols.get(i);
			if (currentSymbol instanceof NonTerminalSymbol) {
				gameSymbols.remove(i);
				System.out.println(((NonTerminalSymbol)currentSymbol).children.size());
				for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
				}
			}
			else {
				i++;
			}
		}
		System.out.println(gameSymbols.size());
		for (int j=0; j<gameSymbols.size(); j++) {	
			System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
		}
	}
	
	private boolean containsNonTerminals() {
		boolean result = false; 
		for (int i=0; i<gameSymbols.size(); i++) {
			if (gameSymbols.get(i) instanceof NonTerminalSymbol) {
				result = true;
				break;
			}
		}
		return result;
	}

}
