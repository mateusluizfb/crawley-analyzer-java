package repo_analyzer.analyzer.services;

import java.util.ArrayList;
import java.util.List;

import repo_analyzer.analyzer.visitors.ErlangParser;
import repo_analyzer.analyzer.visitors.ErlangParser.TryCatchContext;

public class StatmentsAnalyser {
	private static int occurencesCount = 0;

	private List<String> supervisorStrategies = new ArrayList<String>() {{
		add("one_for_all");
		add("one_for_one");
		add("rest_for_one");
		add("simple_one_for_one");
	}};	
	
	public void analyseToken(ErlangParser.TokAtomContext ctx) {
		if(analyseSupervisor() && supervisorStrategies.contains(ctx.getText())) {
			occurencesCount++;
			System.out.println("Supervisor strategies count: " + occurencesCount);
		}
	}
	
	public void analyseToken(TryCatchContext ctx) {
		if(analyseTryCatch()) {
			occurencesCount++;
			System.out.println("Try/Catch count " + occurencesCount);	
		}
	}
	
	private boolean analyseSupervisor() {
		return System.getenv("STATMENT_TO_ANALYSE").equals("SUPERVISOR");
	}
	

	private boolean analyseTryCatch() {
		return System.getenv("STATMENT_TO_ANALYSE").equals("TRY_CATCH");
	}
}
