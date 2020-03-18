package game.settings;

import java.util.*;

import game.research.*;

public class ResearchRegister {
	
	public static final ArrayList<Research> getWholeResearchList(TechTree techtree) {
		ArrayList<Research> output = new ArrayList<Research>();
		output.addAll(getPropulsionResearch(techtree));
		output.addAll(getAttackResearch(techtree));
		return output;
	}
	
	public static final ArrayList<Research> getPropulsionResearch(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				
				
				));
	}
	
	public static final ArrayList<Research> getAttackResearch(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				
				
				));
	}

}
