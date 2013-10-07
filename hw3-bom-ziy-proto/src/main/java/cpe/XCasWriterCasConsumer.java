package cpe;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/* 
 *******************************************************************************************
 * N O T E :     The XML format (XCAS) that this Cas Consumer outputs, is eventually 
 *               being superceeded by the more standardized and compact XMI format.  However
 *               it is used currently as the expected form for remote services, and there is
 *               existing tooling for doing stand-alone component development and debugging 
 *               that uses this format to populate an initial CAS.  So it is not 
 *               deprecated yet;  it is also being kept for compatibility with older versions.
 *               
 *               New code should consider using the XmiWriterCasConsumer where possible,
 *               which uses the current XMI format for XML externalizations of the CAS
 *******************************************************************************************               
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.impl.XCASSerializer;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;
import org.apache.uima.util.XMLSerializer;
import org.xml.sax.SAXException;

import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.Question;

/**
 * A simple CAS consumer that generates XCAS (XML representation of the CAS) files in the
 * filesystem.
 * <p>
 * This CAS Consumer takes one parameters:
 * <ul>
 * <li><code>OutputDirectory</code> - path to directory into which output files will be written</li>
 * </ul>
 * 
 * 
 */
public class XCasWriterCasConsumer extends CasConsumer_ImplBase {
  /**
   * Name of configuration parameter that must be set to the path of a directory into which the
   * output files will be written.
   */
	
	private double confidence = 1.0;
	private static double averagescore = 0;
	private static double cnt = 0;
	public static Comparator<AnswerScore> cmp = new Comparator<AnswerScore>() {

		public int compare(AnswerScore a, AnswerScore b) {
			if (a.getScore() < b.getScore())
				return 1;
			else {
				return 0;
			}

		}
	};

  public static final String PARAM_OUTPUTDIR = "OutputDirectory";

  private File mOutputDir;

  private int mDocNum;

  public void initialize() throws ResourceInitializationException {
    mDocNum = 0;
    mOutputDir = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR));
    if (!mOutputDir.exists()) {
      mOutputDir.mkdirs();
    }
  }

  public void destroy()
  {
//	  System.out.println(String.format("Average Precision: %.2f",averagescore / cnt)); 
  }
  public void collectionProcessComplete(ProcessTrace arg0) throws ResourceProcessException,
  IOException {
	  System.out.println(String.format("Average Precision: %.2f",averagescore / cnt)); 
	  // no default behavior
  }
  /**
   * Processes the CasContainer which was populated by the TextAnalysisEngines. <br>
   * In this case, the CAS is converted to XML and written into the output file .
   * 
   * @param aCAS
   *          CasContainer which has been populated by the TAEs
   * 
   * @throws ResourceProcessException
   *           if there is an error in processing the Resource
   * 
   * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(org.apache.uima.cas.CAS)
   */
  public void processCas(CAS aCAS) throws ResourceProcessException {
	  	
	  JCas jcas,aJCas;
	    try {
	      jcas = aCAS.getJCas();
	    } catch (CASException e) {
	      throw new ResourceProcessException(e);
	    }
	    aJCas =jcas;
	  cnt = cnt + 1;
		FSIndex idq = aJCas.getAnnotationIndex(Question.type);
		Iterator itq = idq.iterator();
		Question q = (Question) itq.next();

		FSIndex ida = aJCas.getAnnotationIndex(AnswerScore.type);
		Iterator ita = ida.iterator();

		int N = 0;
		List<AnswerScore> list = new ArrayList<AnswerScore>();
		while (ita.hasNext()) {
			AnswerScore aScore = (AnswerScore) ita.next();

			if (aScore.getAnswer().getIsCorrect()) {
				N++;
			}
			list.add(aScore);
		}
		Collections.sort(list, cmp);
		int correctnum = 0;
		for (int i = 0; i < N; ++i) {
			if (list.get(i).getAnswer().getIsCorrect()) {
				correctnum += 1;
			}
		}
		double precision = (double) correctnum / N;
		averagescore += precision;

		System.out.println("Question: " + q.getCoveredText() + "?");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAnswer().getIsCorrect()) {
				System.out.print("+ ");
			} else {
				System.out.print("- ");
			}
			System.out.print(String.format("%.2f ", list.get(i).getScore()));
			System.out.println(list.get(i).getCoveredText());
		}
		System.out.println(String.format("Precision at %.1f: %.2f\n",
				(double) N, precision));
		
	  String modelFileName = null;

	    

	    // retrieve the filename of the input file from the CAS
	    FSIterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
	    File outFile = null;
	    if (it.hasNext()) {
	      SourceDocumentInformation fileLoc = (SourceDocumentInformation) it.next();
	      File inFile;
	      try {
	        inFile = new File(new URL(fileLoc.getUri()).getPath());
	        String outFileName = inFile.getName();
	        if (fileLoc.getOffsetInSource() > 0) {
	          outFileName += ("_" + fileLoc.getOffsetInSource());
	        }
	        outFileName += ".xmi";
	        outFile = new File(mOutputDir, outFileName);
	        modelFileName = mOutputDir.getAbsolutePath() + "/" + inFile.getName() + ".ecore";
	      } catch (MalformedURLException e1) {
	        // invalid URL, use default processing below
	      }
	    }
	    if (outFile == null) {
	      outFile = new File(mOutputDir, "doc" + mDocNum++ + ".xmi");     
	    }
	    // serialize XCAS and write to output file
	    try {
	    	writeXCas(jcas.getCas(), outFile, modelFileName);
	    } catch (IOException e) {
	      throw new ResourceProcessException(e);
	    } catch (SAXException e) {
	      throw new ResourceProcessException(e);
	    }
  }

  /**
   * Serialize a CAS to a file in XCAS format
   * 
   * @param aCas
   *          CAS to serialize
   * @param name
   *          output file
   * 
   * @throws IOException
   *           if an I/O failure occurs
   * @throws SAXException
   *           if an error occurs generating the XML text
   */
  private void writeXCas(CAS aCas, File name, String modelFileName) throws IOException, SAXException {
    FileOutputStream out = null;

    try {
      out = new FileOutputStream(name);
      XCASSerializer ser = new XCASSerializer(aCas.getTypeSystem());
      XMLSerializer xmlSer = new XMLSerializer(out, false);
      ser.serialize(aCas, xmlSer.getContentHandler());
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

}
