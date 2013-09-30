
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

/** 
 * Updated by JCasGen Mon Sep 23 23:11:57 EDT 2013
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
 
 
  /** @generated */
  final Feature casFeat_elementNgram;
  /** @generated */
  final int     casFeatCode_elementNgram;
  /** @generated */ 
  public int getElementNgram(int addr) {
        if (featOkTst && casFeat_elementNgram == null)
      jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.SentenceQA");
    return ll_cas.ll_getRefValue(addr, casFeatCode_elementNgram);
  }
  /** @generated */    
  public void setElementNgram(int addr, int v) {
        if (featOkTst && casFeat_elementNgram == null)
      jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.SentenceQA");
    ll_cas.ll_setRefValue(addr, casFeatCode_elementNgram, v);}
    
   /** @generated */
  public int getElementNgram(int addr, int i) {
        if (featOkTst && casFeat_elementNgram == null)
      jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.SentenceQA");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elementNgram), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elementNgram), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elementNgram), i);
  }
   
  /** @generated */ 
  public void setElementNgram(int addr, int i, int v) {
        if (featOkTst && casFeat_elementNgram == null)
      jcas.throwFeatMissing("elementNgram", "edu.cmu.deiis.types.SentenceQA");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elementNgram), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_elementNgram), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_elementNgram), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SentenceQA_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_element = jcas.getRequiredFeatureDE(casType, "element", "uima.cas.FSArray", featOkTst);
    casFeatCode_element  = (null == casFeat_element) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_element).getCode();

 
    casFeat_elementNgram = jcas.getRequiredFeatureDE(casType, "elementNgram", "uima.cas.FSArray", featOkTst);
    casFeatCode_elementNgram  = (null == casFeat_elementNgram) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_elementNgram).getCode();

  }
}



    