
/* First created by JCasGen Mon Sep 23 00:56:01 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** This type contain the sentence in the question and answer, and it is used to make iteasy to tokenize the question and answer.
 * Updated by JCasGen Mon Oct 07 00:38:30 EDT 2013
 * @generated */
public class SentenceQA_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SentenceQA_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SentenceQA_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SentenceQA(addr, SentenceQA_Type.this);
  			   SentenceQA_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SentenceQA(addr, SentenceQA_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SentenceQA.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.SentenceQA");
 
  /** @generated */
  final Feature casFeat_element;
  /** @generated */
  final int     casFeatCode_element;
  /** @generated */ 
  public int getElement(int addr) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    return ll_cas.ll_getRefValue(addr, casFeatCode_element);
  }
  /** @generated */    
  public void setElement(int addr, int v) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    ll_cas.ll_setRefValue(addr, casFeatCode_element, v);}
    
   /** @generated */
  public int getElement(int addr, int i) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_element), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_element), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_element), i);
  }
   
  /** @generated */ 
  public void setElement(int addr, int i, int v) {
        if (featOkTst && casFeat_element == null)
      jcas.throwFeatMissing("element", "edu.cmu.deiis.types.SentenceQA");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_element), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_element), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_element), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SentenceQA_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_element = jcas.getRequiredFeatureDE(casType, "element", "uima.cas.FSArray", featOkTst);
    casFeatCode_element  = (null == casFeat_element) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_element).getCode();

  }
}



    