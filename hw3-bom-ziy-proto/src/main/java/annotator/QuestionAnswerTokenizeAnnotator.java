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

public class QuestionAnswerTokenizeAnnotator extends JCasAnnotator_ImplBase{
	private double confidence = 1.0;//@@这个地方可以写成函数
	/*
	 * This annotator will parse the Question and Answer first. I use the java package Matcher, Pattern. For the question I use the “"Q\\s(.*)\\?" to match the question sentence from the input string. And use the "A\\s([01])\\s(.*)\\." To match the answer sentence. This initial word of each sentence really helps my to recognize the question and answer.
	 * */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		Pattern qpattern  = Pattern.compile("Q\\s(.*)\\?");
		Pattern apattern = Pattern.compile("A\\s([01])\\s(.*)\\.");
		String doc = aJCas.getDocumentText();
		Matcher m = qpattern.matcher(doc);
		while(m.find())
		{
			SentenceQA now = new SentenceQA(aJCas);
			Question ann  = new Question(aJCas);
			ann.setBegin(m.start()+2);
			ann.setEnd(m.end()-1);
			ann.setConfidence(confidence);
			ann.setCasProcessorId(this.getClass().toString());//可以代替不？
			
			
			now.setBegin(m.start()+2);
			now.setEnd(m.end()-1);
			
			now.setConfidence(confidence);
			now.setCasProcessorId(this.getClass().toString());//可以代替不？
			
			String[] tokenString = ann.getCoveredText().split("\\s");
			FSArray array = new FSArray(aJCas, tokenString.length);
			now.setElement(array);
			int nowst  = m.start()+2;
			for (int i = 0; i < tokenString.length; i++) {
				now.setElement(i,inserttoken( tokenString[i], aJCas,nowst));
				nowst+=tokenString[i].length()+1;
			}
			
			now.addToIndexes();
			ann.setSentence(now);
			ann.addToIndexes();
			
			
		}
		
		Matcher ma = apattern.matcher(doc);
		while(ma.find())
		{
			SentenceQA now = new SentenceQA(aJCas);
			Answer ann = new Answer(aJCas);
			ann.setBegin(ma.start()+4);
			ann.setEnd(ma.end()-1);
			ann.setConfidence(confidence);
			ann.setCasProcessorId(this.getClass().toString());
			
			if(Integer.parseInt(ma.group(1))==1)
			{
				ann.setIsCorrect(true);
			
			}
			else {
				ann.setIsCorrect(false);
			}
			
			String[] tokenString = ann.getCoveredText().split("\\s");
			
			FSArray array = new FSArray(aJCas, tokenString.length);
			now.setBegin(ma.start()+4);
			now.setEnd(ma.end()-1);
			
			now.setConfidence(confidence);
			now.setCasProcessorId(this.getClass().toString());//可以代替不？
			now.setElement(array);
			int nowst  = ma.start()+4;
			for (int i = 0; i < tokenString.length; i++) {
				now.setElement(i,inserttoken( tokenString[i], aJCas,nowst));
				nowst+=tokenString[i].length()+1;
				
			}
			now.addToIndexes();
			ann.setSentence(now);
			ann.addToIndexes();
		}
	}
	public Token inserttoken(String tokenstring,JCas aJCas,int now) {
		
			int len = tokenstring.length();
			Token ann = new Token(aJCas);
			ann.setBegin(now);
			now += len;
			ann.setEnd(now);
			ann.setConfidence(confidence);
			ann.setCasProcessorId(this.getClass().toString());
			ann.addToIndexes();
			return ann;
	}
	
	
}
