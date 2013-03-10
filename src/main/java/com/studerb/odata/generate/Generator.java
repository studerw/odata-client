package com.studerb.odata.generate;

import java.io.File;

import com.studerb.odata.edm.model.Metadata;

public interface Generator {

	/**
	 * Set the directory that will be used for placing the generated souce code.
	 * <b>Note that if the subdirectories named after each scheme in the
	 * metadata already exist, the generate function will throw an exception</b>
	 * 
	 * @param file
	 *            directory to place generated source
	 * @return itself (to be used as <em>Builder Pattern</em>)
	 */
	public abstract Generator setOutputDir(File file);

	/**
	 * This method will generate the output source Java files based on the
	 * passed in Metadata
	 * 
	 * @param metadata
	 *            OData metadata object created by passing in a OData schema XML
	 *            file
	 * @throws Exception
	 *             if there is any parsing error or the output directory has not
	 *             been set
	 */
	public abstract void generate(Metadata metadata) throws Exception;

}
