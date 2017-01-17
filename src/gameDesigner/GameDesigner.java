package gameDesigner;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
	TerminalSymbol levelMapping;
	TerminalSymbol variableChar;
	TerminalSymbol greaterThan;
	TerminalSymbol avatar;
	TerminalSymbol wall;
	
	List<Symbol> gameSymbols = new LinkedList<Symbol>();
	
	Random rnd = new Random();
	
	public GameDesigner() {
		establishVGDLSymbols();
		basicGame();
	}
	
	private void establishVGDLSymbols() {
		//Declare Non-Terminals
		game = new NonTerminalSymbol("game");
		levelBlock = new NonTerminalSymbol("levelBlock");
		charMap = new NonTerminalSymbol("charMap");
		spriteType = new NonTerminalSymbol("spriteType");
		
		//Declare Terminals		
		eol = new TerminalSymbol("eol","\n");
		indent = new TerminalSymbol("indent","	");
		identifier = new TerminalSymbol("identifier","IDENTIFIER");
		lambda = new TerminalSymbol("lambda","LAMBDA");
		//strings
		game_class = new TerminalSymbol("game_class","BasicGame"); //will not actually be terminal as it can expand to a number of things but this is simpler for now
		levelMapping = new TerminalSymbol("levelMapping","LevelMapping");
		variableChar = new TerminalSymbol("char","CHAR"); //Just using "CHAR" as place holder for now
		greaterThan = new TerminalSymbol("greater than"," > ");
		avatar = new TerminalSymbol("avatar","avatar");
		wall = new TerminalSymbol("wall","wall");
		
		//Add Children
		game.addChild(game_class);
		game.addChild(eol);
		game.addChild(indent);
		game.addChild(levelBlock);
		//g.sb
		//g.ib
		//g.tb
		levelBlock.addChild(levelMapping);
		levelBlock.addChild(eol);
		levelBlock.addChild(indent);
		levelBlock.addChild(charMap);
		charMap.addChild(variableChar);
		charMap.addChild(greaterThan);
		charMap.addChild(spriteType);
		//charMap.addChild(lambda);
		spriteType.addChild(identifier);
	}
	
	public void basicGame() {
		//game_class
			//LevelMapping
				//CHAR > IDENTIFIER
		int i = 0;
		gameSymbols.add(game);
		System.out.println(gameSymbols.get(0).name);
		while (containsNonTerminals()) {
			if (i > gameSymbols.size()-1) {
				i = 0;
			}
			Symbol currentSymbol = gameSymbols.get(i);
			if (currentSymbol instanceof NonTerminalSymbol) {
				if (((NonTerminalSymbol) currentSymbol).repeatable == true) {
					//EA makes decision somehow as to if the thing is repeated
					//for now its just 50/50 random
					if (rnd.nextInt(2) == 1)
					{
						gameSymbols.remove(i);
					}
				}
				else
				{
					gameSymbols.remove(i);
				}
				//System.out.println(((NonTerminalSymbol)currentSymbol).children.size());
				for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
					i++;
				}
			}
			else {
				i++;
			}
		}
		System.out.println(gameSymbols.size());
		for (int j=0; j<gameSymbols.size(); j++) {	
			System.out.print(((TerminalSymbol)gameSymbols.get(j)).content+" ");
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
