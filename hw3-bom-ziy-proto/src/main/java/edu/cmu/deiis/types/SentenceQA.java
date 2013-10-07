

/* First created by JCasGen Mon Sep 23 00:56:01 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


/** This type contain the sentence in the question and answer, and it is used to make iteasy to tokenize the question and answer.
 * Updated by JCasGen Mon Oct 07 00:38:30 EDT 2013
 * XML source: /Users/mabodx/git/hw3-bom/hw3-bom-ziy-proto/src/main/resources/hw2-bom-aae.xml
 * @generated */
public class SentenceQA extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SentenceQA.class);
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
  protected SentenceQA() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SentenceQA(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SentenceQA(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SentenceQA(JCas jcas, int begin, int end) {
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
  //* Feature: element

  /** getter for element - gets tokenize question and answer
   * @generated */
  public FSArray getElement() {
    if (SentenceQA_Type.featOkTst && ((SentenceQA_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SentenceQA_Type)jcasType).casFeatCode_element)));}
    
  /** setter for element - sets tokenize question and answer 
   * @generated */
  public void setElement(FSArray v) {
    if (SentenceQA_Type.featOkTst && ((SentenceQA_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    jcasType.ll_cas.ll_setRefValue(addr, ((SentenceQA_Type)jcasType).casFeatCode_element, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for element - gets an indexed value - tokenize question and answer
   * @generated */
  public Token getElement(int i) {
    if (SentenceQA_Type.featOkTst && ((SentenceQA_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SentenceQA_Type)jcasType).casFeatCode_element), i);
    return (Token)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SentenceQA_Type)jcasType).casFeatCode_element), i)));}

  /** indexed setter for element - sets an indexed value - tokenize question and answer
   * @generated */
  public void setElement(int i, Token v) { 
    if (SentenceQA_Type.featOkTst && ((SentenceQA_Type)jcasType).casFeat_element == null)
      jcasType.jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SentenceQA_Type)jcasType).casFeatCode_element), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SentenceQA_Type)jcasType).casFeatCode_element), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    