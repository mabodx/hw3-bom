
/* First created by JCasGen Wed Sep 11 13:44:28 EDT 2013 */
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

/** This kind of type is the smallest type in the document. The system will annotate eachtoken span in each question and answer (break on whitespace and punctuation).
 * Updated by JCasGen Mon Oct 07 15:24:17 EDT 2013
 * @generated */
public class Token_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Token_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Token_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Token(addr, Token_Type.this);
  			   Token_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Token(addr, Token_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.Token");



  /** @generated */
  final Feature casFeat_PosTag;
  /** @generated */
  final int     casFeatCode_PosTag;
  /** @generated */ 
  public String getPosTag(int addr) {
        if (featOkTst && casFeat_PosTag == null)
      jcas.throwFeatMissing("PosTag", "edu.cmu.deiis.types.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_PosTag);
  }
  /** @generated */    
  public void setPosTag(int addr, String v) {
        if (featOkTst && casFeat_PosTag == null)
      jcas.throwFeatMissing("PosTag", "edu.cmu.deiis.types.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_PosTag, v);}
    
  
 
  /** @generated */
  final Feature casFeat_featureLemma;
  /** @generated */
  final int     casFeatCode_featureLemma;
  /** @generated */ 
  public String getFeatureLemma(int addr) {
        if (featOkTst && casFeat_featureLemma == null)
      jcas.throwFeatMissing("featureLemma", "edu.cmu.deiis.types.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_featureLemma);
  }
  /** @generated */    
  public void setFeatureLemma(int addr, String v) {
        if (featOkTst && casFeat_featureLemma == null)
      jcas.throwFeatMissing("featureLemma", "edu.cmu.deiis.types.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_featureLemma, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_PosTag = jcas.getRequiredFeatureDE(casType, "PosTag", "uima.cas.String", featOkTst);
    casFeatCode_PosTag  = (null == casFeat_PosTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_PosTag).getCode();

 
    casFeat_featureLemma = jcas.getRequiredFeatureDE(casType, "featureLemma", "uima.cas.String", featOkTst);
    casFeatCode_featureLemma  = (null == casFeat_featureLemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureLemma).getCode();

  }
}



    