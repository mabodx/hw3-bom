package annotator;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.NGram;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.SentenceQA;
import edu.cmu.deiis.types.Token;

public class TokenOverlapAnswerScoreAnnotator extends JCasAnnotator_ImplBase{
	/*
	 * This annotator will use the the Tokenoverlap method to give the score to each answer sentence. This calculating formula is “question tokens found / total answer tokens“
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
				double score = Tokenoverlapscore(nowq.getElementNgram(), nowa.getElementNgram());
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
	public double Tokenoverlapscore(FSArray Ngramq,FSArray Ngrama) {
		double score=0,ans=0;
		int flag =0;
//		System.out.println(Ngrama.size());
		for (int i = 0; i < Ngramq.size(); i++) {
			NGram  qGram = (NGram)Ngramq.get(i);
			
			if(qGram.getElements().size()==1)
			{
				String qString = qGram.getCoveredText();
//				System.out.println("Q:"+qString);
				for (int j = 0; j < Ngrama.size(); j++) {
					NGram aGram = (NGram)Ngrama.get(j);
					if(aGram.getElements().size()==1)
					{
						String aString = aGram.getCoveredText();
//						System.out.println("A:"+aString);
						if(aString.equals(qString))
						{
							score =score +1;
							
						}
						if(flag==0)
							ans = ans+1;
					}
				}
			}
			flag = 1;
		}
		return score/ans;
	}

}
