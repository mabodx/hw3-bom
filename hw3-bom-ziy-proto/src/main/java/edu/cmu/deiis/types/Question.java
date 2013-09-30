

/* First created by JCasGen Wed Sep 11 13:44:28 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



import org.apache.uima.jcas.cas.FSArray;


/** 
 * Updated by JCasGen Mon Sep 23 23:11:57 EDT 2013
 * XML source: /Users/mabodx/git/hw2-bom/hw2-bom/src/main/resources/hw2-bom-aee.xml
 * @generated */
public class Question extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Question.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Question() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Question(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Question(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Question(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
  //*--------------*
  //* Feature: sentence

  /** getter for sentence - gets 
   * @generated */
  public SentenceQA getSentence() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "edu.cmu.deiis.types.Question");
    return (SentenceQA)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_sentence)));}
    
  /** setter for sentence - sets  
   * @generated */
  public void setSentence(SentenceQA v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "edu.cmu.deiis.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_sentence, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: elementNgram

  /** getter for elementNgram - gets 
   * @generated */
  public FSArray getElementNgram() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_elementNgram == null)
      jcasType.jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.Question");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_elementNgram)));}
    
  /** setter for elementNgram - sets  
   * @generated */
  public void setElementNgram(FSArray v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_elementNgram == null)
      jcasType.jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.Question");
    jcasType.ll_cas.ll_setRefValue(addr, ((Question_Type)jcasType).casFeatCode_elementNgram, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for elementNgram - gets an indexed value - 
   * @generated */
  public NGram getElementNgram(int i) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_elementNgram == null)
      jcasType.jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.Question");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_elementNgram), i);
    return (NGram)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_elementNgram), i)));}

  /** indexed setter for elementNgram - sets an indexed value - 
   * @generated */
  public void setElementNgram(int i, NGram v) { 
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_elementNgram == null)
      jcasType.jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.Question");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_elementNgram), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Question_Type)jcasType).casFeatCode_elementNgram), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    