package annotator;

import org.apache.activemq.transport.stomp.Stomp.Headers.Send;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.examples.RunAE;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.NGram;
import edu.cmu.deiis.types.Question_Type;
import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.Answer_Type;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.SentenceQA;
import edu.cmu.deiis.types.Token;

public class NGramAnnotator extends JCasAnnotator_ImplBase{
	/*
	 * This annotator will use the Ngram language model to get the unigram, bigram, trigram. Then the next step I can use the Ngram to get the score of each answer.
	 * */
	private int numberN = 3;
	private double confidence = 1.0;
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		runq(aJCas);
		runa(aJCas);
		
	}
	
	public void runq(JCas aJCas)
	{
		
		String doc = aJCas.getDocumentText();
		FSIndex id = aJCas.getAnnotationIndex(Question.type);
		Iterator iterator = id.iterator();
		
		while (iterator.hasNext()) {
			Question nowq = (Question) iterator.next();
			int cnt = 0;
			SentenceQA now = nowq.getSentence();
			
			FSArray element = now.getElement();
			int len = element.size();
			len = len*3-3;
			
			FSArray Ngram = new FSArray(aJCas, len);
			nowq.setElementNgram(Ngram); //@@应该可以不要
			
			FSArray array;
			for (int i = 0; i < element.size(); i++) {
				
				Token st = (Token)element.get(i);
				int start = st.getBegin();
				for (int j = 0; j<3 && i+j< element.size(); j++) {
					
					Token en = (Token)element.get(i+j);
					int end = en.getEnd();
					NGram ann = new NGram(aJCas);
					ann.setBegin(start);
					ann.setEnd(end);;
					ann.setCasProcessorId(this.getClass().toString());
					ann.setConfidence(confidence);
					ann.setElementType(Token.class.toString());
					
					array = new FSArray(aJCas, j+1);
					for (int k = 0; k <= j; k++) {
						array.set(k, element.get(i+k));
					}
					ann.setElements(array);
					
					
					ann.addToIndexes();
					nowq.setElementNgram(cnt++, ann);
				}
			}
			
		}
	}
	
	public void runa(JCas aJCas)
	{
		
		String doc = aJCas.getDocumentText();
		FSIndex id = aJCas.getAnnotationIndex(Answer.type);
		Iterator iterator = id.iterator();
		
		while (iterator.hasNext()) {
			Answer nowq = (Answer) iterator.next();
			int cnt = 0;
			SentenceQA now = nowq.getSentence();
			
			FSArray element = now.getElement();
			int len = element.size();
			len = len*3-3;
			
			FSArray Ngram = new FSArray(aJCas, len);
			nowq.setElementNgram(Ngram); //@@应该可以不要
			
			FSArray array;
			for (int i = 0; i < element.size(); i++) {
				
				Token st = (Token)element.get(i);
				int start = st.getBegin();
				for (int j = 0; j<3 && i+j< element.size(); j++) {
					
					Token en = (Token)element.get(i+j);
					int end = en.getEnd();
					NGram ann = new NGram(aJCas);
					ann.setBegin(start);
					ann.setEnd(end);;
					ann.setCasProcessorId(this.getClass().toString());
					ann.setConfidence(confidence);
					ann.setElementType(Token.class.toString());
					
					array = new FSArray(aJCas, j+1);
					for (int k = 0; k <= j; k++) {
						array.set(k, element.get(i+k));
					}
					ann.setElements(array);
					
					
					ann.addToIndexes();
					nowq.setElementNgram(cnt++, ann);
				}
			}
			
		}
	}
	
	

	
}
