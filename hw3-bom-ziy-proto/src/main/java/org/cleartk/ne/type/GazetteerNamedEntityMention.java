

/* First created by JCasGen Sun Oct 06 23:48:00 EDT 2013 */
package org.cleartk.ne.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Mon Oct 07 00:38:30 EDT 2013
 * XML source: /Users/mabodx/git/hw3-bom/hw3-bom-ziy-proto/src/main/resources/hw2-bom-aae.xml
 * @generated */
public class GazetteerNamedEntityMention extends NamedEntityMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GazetteerNamedEntityMention.class);
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
  protected GazetteerNamedEntityMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public GazetteerNamedEntityMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public GazetteerNamedEntityMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public GazetteerNamedEntityMention(JCas jcas, int begin, int end) {
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
     
}

    