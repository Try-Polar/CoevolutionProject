package gameDesigner;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameDesigner {
	//Symbols
	//Declare Non-Terminals
	NonTerminalSymbol game;
	NonTerminalSymbol levelBlock;
	NonTerminalSymbol spriteBlock;
	NonTerminalSymbol interactionBlock;
	NonTerminalSymbol terminationBlock;
	NonTerminalSymbol charMap;
	NonTerminalSymbol spriteType;
	NonTerminalSymbol spriteDef;
	NonTerminalSymbol interactionDef;
	NonTerminalSymbol terminationDef;
	NonTerminalSymbol spriteSimple;
	NonTerminalSymbol option;
	NonTerminalSymbol eol;
	
			
	//Declare Terminals	
	TerminalSymbol newline;
	TerminalSymbol indent;
	TerminalSymbol identifier;
	TerminalSymbol lambda;
	TerminalSymbol greaterThan;
	TerminalSymbol hash;
	TerminalSymbol space;
	TerminalSymbol equals;
	//strings
	TerminalSymbol game_class;
	TerminalSymbol sprite_class;
	TerminalSymbol interaction_method;
	TerminalSymbol termination_class;
	TerminalSymbol levelMapping;
	TerminalSymbol variableChar;
	TerminalSymbol avatar;
	TerminalSymbol wall;
	TerminalSymbol spriteSet;
	TerminalSymbol interactionSet;
	TerminalSymbol terminationSet;
	
	
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
		spriteBlock = new NonTerminalSymbol("spriteBlock");
		interactionBlock = new NonTerminalSymbol("interactionBlock");
		terminationBlock = new NonTerminalSymbol("terminationBlock");
		charMap = new NonTerminalSymbol("charMap");
		spriteType = new NonTerminalSymbol("spriteType");
		spriteDef = new NonTerminalSymbol("spriteDef");
		interactionDef = new NonTerminalSymbol("interactionDef");
		terminationDef = new NonTerminalSymbol("terminationdef");
		spriteSimple = new NonTerminalSymbol("spriteSimple");
		option = new NonTerminalSymbol("option");
		eol = new NonTerminalSymbol("eol");
		
		//Declare Terminals		
		newline = new TerminalSymbol("newline", "\n");
		indent = new TerminalSymbol("indent","	");
		identifier = new TerminalSymbol("identifier","IDENTIFIER");
		lambda = new TerminalSymbol("lambda","LAMBDA");
		greaterThan = new TerminalSymbol("greater than"," > ");
		hash = new TerminalSymbol("hash", "#");
		space = new TerminalSymbol("space", " ");
		equals = new TerminalSymbol("equals", "=");
		//strings
		game_class = new TerminalSymbol("game_class","BasicGame"); //will not actually be terminal as it can expand to a number of things but this is simpler for now
		sprite_class = new TerminalSymbol("sprite_class", ""); //will need to be passed as some sort of parameter or made into nonTerminal
		interaction_method = new TerminalSymbol("interaction_method", ""); //will need to be passed as some sort of parameter or made into nonTerminal
		termination_class = new TerminalSymbol("termination_class", "");
		levelMapping = new TerminalSymbol("levelMapping","LevelMapping");
		variableChar = new TerminalSymbol("char","CHAR"); //Just using "CHAR" as place holder for now
		avatar = new TerminalSymbol("avatar","avatar");
		wall = new TerminalSymbol("wall","wall");
		spriteSet = new TerminalSymbol("spriteSet","SpriteSet");
		interactionSet = new TerminalSymbol("interactionSet","InteractionSet");
		terminationSet = new TerminalSymbol("terminationSet","TerminationSet");
		
		//Add Children
		//game
		game.addChild(game_class);
		game.addChild(eol);
		game.addChild(indent);
		game.addChild(levelBlock);
		game.addChild(spriteBlock);
		game.addChild(interactionBlock);
		game.addChild(terminationBlock);
		//level-block
		levelBlock.addChild(levelMapping);
		levelBlock.addChild(eol);
		levelBlock.addChild(indent);
		levelBlock.addChild(charMap);
		levelBlock.addChild(newline);
		//sprite-block
		spriteBlock.addChild(spriteSet);
		spriteBlock.addChild(eol);
		spriteBlock.addChild(indent);
		spriteBlock.addChild(charMap);
		spriteBlock.addChild(newline);
		//interaction-block
		interactionBlock.addChild(interactionSet);
		interactionBlock.addChild(eol);
		interactionBlock.addChild(indent);
		interactionBlock.addChild(interactionDef);
		interactionBlock.addChild(eol);
		//termination-block
		terminationBlock.addChild(terminationSet);
		terminationBlock.addChild(eol);
		terminationBlock.addChild(indent);
		terminationBlock.addChild(terminationDef);
		terminationBlock.addChild(eol);
		//char-map
		charMap.addChild(variableChar);
		charMap.addChild(greaterThan);
		charMap.addChild(spriteType);
		//sprite-def
		spriteDef.addChild(spriteSimple);
		//spriteDef.addChild(eol); //on hold for now until repeatable and optional sections are setup
		//spriteDef.addChild(indent);
		//spriteDef.addChild(spriteDef);
		//spriteDef.addChild(eol);
		//sprite-simple
		spriteSimple.addChild(spriteType);
		spriteSimple.addChild(greaterThan);
		spriteSimple.addChild(sprite_class);
		spriteSimple.addChild(space);
		spriteSimple.addChild(option);
		//interaction-def
		interactionDef.addChild(spriteType);
		interactionDef.addChild(spriteType);
		interactionDef.addChild(greaterThan);
		interactionDef.addChild(interaction_method);
		interactionDef.addChild(space);
		interactionDef.addChild(option);
		//termination-def
		terminationDef.addChild(termination_class);
		terminationDef.addChild(space);
		terminationDef.addChild(option);
		//eol
		//eol.addChild(space);
		//eol.addChild(hash);
		//eol.addChild(variableChar);
		//eol.addChild(space);
		eol.addChild(newline);
		//option
		option.addChild(identifier);
		option.addChild(equals);
		option.addChild(spriteType);
		//option.addChild(evaluable); //requires OR functionality
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
