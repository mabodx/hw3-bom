

/* First created by JCasGen Wed Sep 11 13:44:28 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** This kind of type is the smallest type in the document. The system will annotate eachtoken span in each question and answer (break on whitespace and punctuation).
 * Updated by JCasGen Mon Oct 07 15:24:17 EDT 2013
 * XML source: /Users/mabodx/git/hw3-bom/hw3-bom-ziy-proto/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Token.class);
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
  protected Token() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Token(JCas jcas, int begin, int end) {
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
  //* Feature: PosTag

  /** getter for PosTag - gets 
   * @generated */
  public String getPosTag() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_PosTag == null)
      jcasType.jcas.throwFeatMissing("PosTag", "edu.cmu.deiis.types.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_PosTag);}
    
  /** setter for PosTag - sets  
   * @generated */
  public void setPosTag(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_PosTag == null)
      jcasType.jcas.throwFeatMissing("PosTag", "edu.cmu.deiis.types.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_PosTag, v);}    
   
    
  //*--------------*
  //* Feature: featureLemma

  /** getter for featureLemma - gets 
   * @generated */
  public String getFeatureLemma() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_featureLemma == null)
      jcasType.jcas.throwFeatMissing("featureLemma", "edu.cmu.deiis.types.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_featureLemma);}
    
  /** setter for featureLemma - sets  
   * @generated */
  public void setFeatureLemma(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_featureLemma == null)
      jcasType.jcas.throwFeatMissing("featureLemma", "edu.cmu.deiis.types.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_featureLemma, v);}    
  }

    