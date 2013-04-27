/*
 * $Id: StringConversionTest.java 5 2013-03-12 06:24:16Z stbill79 $
 *
 * Copyright (c) 2013 William Studer
 */
package com.studerb.odata.generate;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.studerb.odata.edm.MetadataParser;
import com.studerb.odata.edm.model.Metadata;

/**
 * <p>
 * Simple util class to generate OData source from the command line. Main method
 * takes as arguments the top directory to create the source output and the
 * absolute location of the metadata file. The arguments to the command are: 1)
 * The location of the top directory in which the source files will be
 * generated. 2) The location of the metadata file.
 * <p>
 * For example:
 * <code>java -jar GenerateUtil "c:/home/generate_source_dir" "c:/home/northwind_metadata.xml"</code>
 *
 * @author William Studer
 *
 */
public class GenerateUtil {

    final static Logger log = LoggerFactory.getLogger(GenerateUtil.class);

    public static void main(String[] args) throws Exception {
        System.err.println("****************************************BLAH***************************");
        if (args.length != 2) {
            throw new IllegalArgumentException("args <top directory where source is to be created> <file location of metadata file>");
        }
        String sourceDirStr = args[0];
        String metadataFileStr = args[1];

        File sourceDir = new File(sourceDirStr);
        File metadataFile = new File(metadataFileStr);

        if (!metadataFile.exists()) {
            throw new IllegalArgumentException("Metadata file does not exist: " + metadataFileStr);
        }
        log.info("Using metafile: " + metadataFile.getName());

        if (sourceDir.exists()) {
            log.info("deleting source directory: " + sourceDir.getName());
            FileUtils.deleteDirectory(sourceDir);
        }

        log.debug("Generating source output in directory: " + sourceDir);

        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(new FileInputStream(metadataFile));
        new Generator(sourceDir).generate(metadata);
    }
}
