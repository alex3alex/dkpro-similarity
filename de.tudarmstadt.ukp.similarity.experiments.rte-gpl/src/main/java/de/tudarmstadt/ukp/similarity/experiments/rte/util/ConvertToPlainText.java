/*******************************************************************************
 * Copyright 2013
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl-3.0.txt
 ******************************************************************************/
package de.tudarmstadt.ukp.similarity.experiments.rte.util;

import static de.tudarmstadt.ukp.similarity.experiments.rte.Pipeline.DATASET_DIR;
import static de.tudarmstadt.ukp.similarity.experiments.rte.Pipeline.UTILS_DIR;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.File;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.similarity.dkpro.io.CombinationReader.CombinationStrategy;
import de.tudarmstadt.ukp.similarity.dkpro.io.RTECorpusReader;
import de.tudarmstadt.ukp.similarity.experiments.rte.Pipeline.Dataset;


public class ConvertToPlainText
{
	public static void convert(Dataset dataset)
		throws Exception
	{
		File outputDir = new File(UTILS_DIR + "/plaintexts/" + RteUtil.getCommonDatasetName(dataset) + "/");
		
		if (!outputDir.exists())
		{
			CollectionReader reader = createReader(
					 RTECorpusReader.class,
		             RTECorpusReader.PARAM_COMBINATION_STRATEGY, CombinationStrategy.SAME_ROW_ONLY,
		             RTECorpusReader.PARAM_INPUT_FILE, RteUtil.getInputFilePathForDataset(DATASET_DIR, dataset));
			
			AnalysisEngine writer = createEngine(
					PlainTextWriter.class,
					PlainTextWriter.PARAM_OUTPUT_DIR, outputDir.getAbsolutePath());
		
			SimplePipeline.runPipeline(reader, writer);
		}
	}

}