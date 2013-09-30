package annotator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.examples.RunAE;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.NGram;
import edu.cmu.deiis.types.Question_Type;
import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.Answer_Type;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.SentenceQA;
import edu.cmu.deiis.types.Token;

import java.util.Comparator;

public class EvaluateAnnotator extends JCasAnnotator_ImplBase {
	/*
	 * As we get the score of each answer, we can use the sort method to rank the answer. After getting the sorted answer, what I am going to do is to judge evaluation of my model. I will calculate the Òprecistion at NÓ . N is the correct answer in the ground truth. And also the destroy method also let me calculate the average precision.
	 * */
	private double confidence = 1.0;
	private static double averagescore = 0;
	private static double cnt = 0;
	public static Comparator<AnswerScore> cmp = new Comparator<AnswerScore>() {

		public int compare(AnswerScore a, AnswerScore b) {
			if (a.getScore() < b.getScore())
				return 1;
			else {
				return 0;
			}

		}
	};

	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
//		System.out.println(this.getClass().toString());
		cnt = cnt + 1;
		FSIndex idq = aJCas.getAnnotationIndex(Question.type);
		Iterator itq = idq.iterator();
		Question q = (Question) itq.next();

		FSIndex ida = aJCas.getAnnotationIndex(AnswerScore.type);
		Iterator ita = ida.iterator();

		int N = 0;
		List<AnswerScore> list = new ArrayList<AnswerScore>();
		while (ita.hasNext()) {
			AnswerScore aScore = (AnswerScore) ita.next();

			if (aScore.getAnswer().getIsCorrect()) {
				N++;
			}
			list.add(aScore);
		}
		Collections.sort(list, cmp);
		int correctnum = 0;
		for (int i = 0; i < N; ++i) {
			if (list.get(i).getAnswer().getIsCorrect()) {
				correctnum += 1;
			}
		}
		double precision = (double) correctnum / N;
		averagescore += precision;

		System.out.println("Question: " + q.getCoveredText() + "?");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAnswer().getIsCorrect()) {
				System.out.print("+ ");
			} else {
				System.out.print("- ");
			}
			System.out.print(String.format("%.2f ", list.get(i).getScore()));
			System.out.println(list.get(i).getCoveredText());
		}
		System.out.println(String.format("Precision at %.1f: %.2f\n",
				(double) N, precision));

	}

	public void destroy() {

		System.out.println(String.format("Average Precision: %.2f",
				averagescore / cnt));
	}

}
