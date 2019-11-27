package repo_analyzer.analyzer.services;

import java.util.ArrayList;
import java.util.List;

import repo_analyzer.analyzer.visitors.ErlangParser;

public class StatmentsAnalyser {
	private int occurencesCount = 0;

	private List<String> supervisorStrategies = new ArrayList<String>() {{
		add("one_for_all");
		add("one_for_one");
		add("rest_for_one");
		add("simple_one_for_one");
	}};	
	
	public void analyseToken(ErlangParser.TokAtomContext ctx) {
		if(analyseSupervisor() && supervisorStrategies.contains(ctx.getText())) {
			occurencesCount++;
			System.out.println(occurencesCount);
		}
	}
	
	private boolean analyseSupervisor() {
		return System.getenv("STATMENT_TO_ANALYSE").equals("SUPERVISOR");
	}
}
