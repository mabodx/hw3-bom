package annotator;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import java.util.Iterator;

import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.NGram;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.SentenceQA;
import edu.cmu.deiis.types.Token;



public class GoldAnswerScore extends JCasAnnotator_ImplBase{
	/*
	 * This annotator will use the the gold pipeline method to give the score to each answer sentence. This calculating formula is inspect answer key, rank all correct answers 1.0, 0.0 otherwise.
	 * */
	private double confidence = 1.0;
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		FSIndex idq = aJCas.getAnnotationIndex(Question.type);
		Iterator itq = idq.iterator();
		FSIndex ida;
		Iterator ita;
		Question nowq;
		Answer nowa;
		int i =0 ,j = 0;
		while (itq.hasNext()) {
//			System.out.println("i++: "+i++);
			nowq = (Question) itq.next();
			ida = aJCas.getAnnotationIndex(Answer.type);
			ita = ida.iterator();
			while(ita.hasNext())
			{
				//System.out.println("j++: "+j++);
				nowa = (Answer) ita.next();
				AnswerScore ann = new AnswerScore(aJCas);
//				System.out.println("j--: "+j++);
				double score = 0;
				if(nowa.getIsCorrect()==true)
					score = 1;
//				System.out.println("j--: "+j++);
				ann.setBegin(nowa.getBegin());
				ann.setEnd(nowa.getEnd());
//				System.out.println("j--: "+j++);
				ann.setConfidence(confidence);
//				System.out.println("j--: "+j++);
				ann.setCasProcessorId(this.getClass().toString());
//				System.out.println("j--: "+j++);
				ann.setScore(score);
//				System.out.println("j--: "+j++);
				ann.setAnswer(nowa);
				ann.addToIndexes();
				
			}
		}
	}
	

}
