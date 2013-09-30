package annotator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

import java.util.Iterator;
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


public class AnswerscoreAnnotator extends JCasAnnotator_ImplBase {
	/*
	 * his annotator will use the Ngram results. And calculate the score of each answer. It will find the Ngram that in the question appears also in the answer and use
this formula ÒquestionN-Grams found / total ansewr N-Grams (for 1-, 2- and 3-grams)Ó to give the score to each answer
	 * */
	private double confidence = 1.0;
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
//		System.out.println(this.getClass().toString());
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
				double score = NGramscore(nowq.getElementNgram(), nowa.getElementNgram());
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
	
	public double NGramscore(FSArray Ngramq,FSArray Ngrama) {
		double score=0;
//		System.out.println(Ngrama.size());
		for (int i = 0; i < Ngramq.size(); i++) {
			NGram  qGram = (NGram)Ngramq.get(i);
			String qString = qGram.getCoveredText();
			for (int j = 0; j < Ngrama.size(); j++) {
				NGram aGram = (NGram)Ngrama.get(j);
				String aString = aGram.getCoveredText();
				if(aString.equals(qString))
				{
					score =score +1;
					break;
				}
			}
		}
		return score/Ngrama.size();
	}

}
