package annotator;

import org.apache.commons.pool.impl.GenericKeyedObjectPool.Config;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.examples.RunAE;
import org.apache.uima.jcas.JCas;
import org.cleartk.ne.type.NamedEntityMention;

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
	public static int cntentity;
	public static int sumentity;
//	public void process(JCas aJCas) throws AnalysisEngineProcessException {
//		// TODO Auto-generated method stub
////		System.out.println(this.getClass().toString());
//		FSIndex idq = aJCas.getAnnotationIndex(Question.type);
//		Iterator itq = idq.iterator();
//		FSIndex ida;
//		Iterator ita;
//		Question nowq;
//		Answer nowa;
//		int i =0 ,j = 0;
//		while (itq.hasNext()) {
////			System.out.println("i++: "+i++);
//			nowq = (Question) itq.next();
//			ida = aJCas.getAnnotationIndex(Answer.type);
//			ita = ida.iterator();
//			while(ita.hasNext())
//			{
//				//System.out.println("j++: "+j++);
//				nowa = (Answer) ita.next();
//				AnswerScore ann = new AnswerScore(aJCas);
////				System.out.println("j--: "+j++);
//				FSArray Ngramq = nowq.getElementNgram();
//				FSArray Ngrama = nowa.getElementNgram();
//				double score=0;
////				System.out.println(Ngrama.size());
//				for ( i = 0; i < Ngramq.size(); i++) {
//					NGram  qGram = (NGram)Ngramq.get(i);
//					String qString = qGram.getCoveredText();
//					for ( j = 0; j < Ngrama.size(); j++) {
//						NGram aGram = (NGram)Ngrama.get(j);
//						String aString = aGram.getCoveredText();
//						if(aString.equals(qString))
//						{
//							score =score +1;
//							break;
//						}
//					}
//				}
//				
//				int cntentity = 0;
//				FSIndex neIndex = aJCas.getAnnotationIndex(NamedEntityMention.type);
//				Iterator neIterator = neIndex.iterator();
//				while (neIterator.hasNext()) {
//					NamedEntityMention nem = (NamedEntityMention) neIterator.next();
//					if (nem.getBegin() >= nowa.getBegin() && nem.getEnd() <= nowa.getEnd()) {
//						if (nowq.getCoveredText().indexOf(nem.getCoveredText()) >= 0) {
//							score++;
//							cntentity++;
//						}
//					}
//				}
//
//				
//				
//				score = score/(Ngrama.size()+ cntentity);
//				
//				
//				
////				System.out.println("j--: "+j++);
//				ann.setBegin(nowa.getBegin());
//				ann.setEnd(nowa.getEnd());
////				System.out.println("j--: "+j++);
//				ann.setConfidence(confidence);
////				System.out.println("j--: "+j++);
//				ann.setCasProcessorId(this.getClass().toString());
////				System.out.println("j--: "+j++);
//				ann.setScore(score);
////				System.out.println("j--: "+j++);
//				ann.setAnswer(nowa);
//				ann.addToIndexes();
//				
//			}
//		}
//	}
//	
//	public double NGramscore(FSArray Ngramq,FSArray Ngrama) {
//	
//		double score=0;
////		System.out.println(Ngrama.size());
//		for (int i = 0; i < Ngramq.size(); i++) {
//			NGram  qGram = (NGram)Ngramq.get(i);
//			String qString = qGram.getCoveredText();
//			for (int j = 0; j < Ngrama.size(); j++) {
//				NGram aGram = (NGram)Ngrama.get(j);
//				String aString = aGram.getCoveredText();
//				if(aString.equals(qString))
//				{
//					score =score +1;
//					break;
//				}
//			}
//		}
//		return score/Ngrama.size();
//	}
/************************************************************/
	
	
public void process(JCas aJCas) throws AnalysisEngineProcessException {
        
		 FSIndex Idtoken = aJCas.getAnnotationIndex(Token.type);
	     Iterator iterto = Idtoken.iterator();
	     while (iterto.hasNext()) {
	         Token token = (Token) iterto.next();
	     
	         FSIndex Idscnlp = aJCas.getAnnotationIndex(org.cleartk.token.type.Token.type);
	         Iterator iternlp = Idscnlp.iterator();
	         int flag =0;
	         while (iternlp.hasNext()) {
	             org.cleartk.token.type.Token scnlpToken = (org.cleartk.token.type.Token) iternlp.next();
	             if (scnlpToken.getBegin() <= token.getBegin() && scnlpToken.getEnd() >= token.getEnd()) {
	             	
	                 token.setPosTag(scnlpToken.getPos());
	                 token.setFeatureLemma(scnlpToken.getLemma());
	                 flag = 1;
	                 break;
	             }
	         }
	        
	         if (flag==0){
	             token.setFeatureLemma(token.getCoveredText());
	         }
	     }
        
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
				FSArray Ngramq = nowq.getElementNgram();
				FSArray Ngrama = nowa.getElementNgram();
				double score=0;
//				System.out.println(Ngrama.size());
				for ( i = 0; i < Ngramq.size(); i++) {
					NGram  qGram = (NGram)Ngramq.get(i);
					String qString = qGram.getCoveredText();
					for ( j = 0; j < Ngrama.size(); j++) {
						NGram aGram = (NGram)Ngrama.get(j);
						String aString = aGram.getCoveredText();
						if(aString.equals(qString))
						{
							score =score +1;
							break;
						}
					}
				}
				 cntentity = 0;
				 sumentity =0;
				FSIndex neIndex = aJCas.getAnnotationIndex(NamedEntityMention.type);
				Iterator neIterator = neIndex.iterator();
				while (neIterator.hasNext()) {
					NamedEntityMention nem = (NamedEntityMention) neIterator.next();
					if (nem.getBegin() >= nowa.getBegin() && nem.getEnd() <= nowa.getEnd()) {
						if (nowq.getCoveredText().indexOf(nem.getCoveredText()) >= 0) {
							
							cntentity++;
						}
					}
					sumentity++;
				}

				
				
				score = combinescore(nowq, nowa);
				
				
				
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
    public static class Config
    {
    	int isnot;
    	int isbe;
    	int ispas;
    	ArrayList<Token> isnamedentity;
    	Config(){
    		isnot =0;
    		isbe =0;
    		ispas=0;
    		isnamedentity =new ArrayList<Token>();
    	}
    }
	
	public static void judge(Token now, Config config)
	{
		 if (now.getFeatureLemma().equals("not") ) {
             config.isnot = 1;
         }
         if (now.getPosTag() !=null && now.getPosTag().equals("NNP")) {
             config.isnamedentity.add(now);
         }
         if(now.getFeatureLemma().equals("be")) {
             config.isbe = 1;
         }
         if(config.isbe==1 && now.getPosTag().contains("VB")){
             config.ispas = 1;
         }
         
	}
    public static float combinescore(Question question, Answer answer) {
        float score = NGramscore(question, answer);
        
        SentenceQA questionSentenceQA = (SentenceQA) question.getSentence();
        SentenceQA answerSentenceQA = (SentenceQA) answer.getSentence();
        FSArray nowmyq = questionSentenceQA.getElement();
        FSArray nowmya = answerSentenceQA.getElement();
        Config configq,configa;
        configa = new Config();
        configq = new Config();
       
      
        for (int i = 0; i < nowmyq.size(); i++) {
            Token questionToken = (Token) nowmyq.get(i);
            if(questionToken.getFeatureLemma()==null){
                continue;
            }
            judge(questionToken, configq);
          
        }
        for (int i = 0; i < nowmya.size(); i++) {
            Token answerToken = (Token) nowmya.get(i);
            
            if(answerToken.getFeatureLemma()==null){
                continue;
            }
            judge(answerToken,configa);
           
        }
        if (0 < configa.isnamedentity.size() && 0 < configq.isnamedentity.size())
        {
           
        	Token questionToken = (Token) configq.isnamedentity.get(0);
            Token answerToken = (Token) configa.isnamedentity.get(0);
            
            if(questionToken.getFeatureLemma()==null || answerToken.getFeatureLemma()==null){
            	 if(configa.ispas != configq.ispas){
            		 score = score + 1;
            	 }
            }
            if (questionToken.getFeatureLemma().equals(answerToken.getFeatureLemma())) {
            	 if(configa.ispas == configq.ispas){
            		 score = score + 1;
            	 }
            }
        }
        else {
        	if(configa.ispas != configq.ispas){
       		 score = score + 1;	
        	}
		}
        
        if(configa.isnot != configq.isnot){
            score = score - 1;
        }
//        
        return score;
    }
    
    
    public static float NGramscore(Question nowq, Answer nowa) {
	    int i,j;
	//	System.out.println("j--: "+j++);
		FSArray Ngramq = nowq.getElementNgram();
		FSArray Ngrama = nowa.getElementNgram();
		float score=0;
	//	System.out.println(Ngrama.size());
		for ( i = 0; i < Ngramq.size(); i++) {
			NGram  qGram = (NGram)Ngramq.get(i);
			String qString = qGram.getCoveredText();
			for ( j = 0; j < Ngrama.size(); j++) {
				NGram aGram = (NGram)Ngrama.get(j);
				String aString = aGram.getCoveredText();
				if(aString.equals(qString))
				{
					score =score +1;
					break;
				}
			}
		}
	
	
		score =(score + cntentity)/(Ngrama.size() + sumentity );
		return  score;
    }
}
