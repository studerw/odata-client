/*
 * $Id: StringConversionTest.java 5 2013-03-12 06:24:16Z stbill79 $
 *
 * Copyright (c) 2013 William Studer
 */
package com.studerb.odata.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.studerb.odata.edm.MetadataParser;
import com.studerb.odata.edm.model.ComplexType;
import com.studerb.odata.edm.model.DataService;
import com.studerb.odata.edm.model.EntityType;
import com.studerb.odata.edm.model.Metadata;
import com.studerb.odata.edm.model.Schema;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * Creates a Java Pojo model based on OData metadata. Typical usage:
 * <p>
 * 
 * <pre>
 *  {@code
 *  InputStream in = ...get an XML inputstream of your OData metadata definition file (usually located
 *                   ... at the root of the rest http directory (e.g. http://services.odata.org/Northwind/Northwind.svc/$metadata)
 *  MetadataParser parser = new MetadataParser();
 *  Metadata metadata = parser.parseXml(r.getInputStream());
 *  File outputDir = ...Create output directory where source files will be located
 *  Generator generator = new DefaultGenerator(outputDir);
 *  generator.generate(metadata);
 * }
 * </pre>
 * 
 * @author William Studer
 * @see Metadata
 * @see MetadataParser
 */
public class Generator {

    final Logger log = LoggerFactory.getLogger(Generator.class);
    private final File outputDir;
    Configuration cfg;

    /**
     * Set the directory that will be used for placing the generated souce code. <b>Note that if the subdirectories
     * named after each scheme in the metadata already exist, the generate function will throw an exception</b>
     * 
     * @param outputDir
     *            directory to place generated source
     */
    public Generator(File outputDir) {
        this.createCfg();
        this.outputDir = outputDir;
    }


    /**
     * This method will generate the output source Java files based on the passed in Metadata
     * 
     * @param metadata
     *            OData metadata object created by passing in a OData schema XML file
     * @throws Exception
     *             if there is any parsing error or the output directory has not been set
     */
    public void generate(Metadata metadata) throws Exception {
        if (this.outputDir == null) {
            throw new RuntimeException("Generator must have its output directory set");
        }
        for (DataService ds : metadata.getDataServices()) {
            List<Schema> schemas = ds.getSchemas();
            if (schemas != null) {
                for (Schema schema : schemas) {
                    this.generateSchema(schema);
                }
            }
        }
    }

    protected File createOutputDir(Schema schema) throws IOException {
        File packDir = new File(this.outputDir, schema.getNamespace());
        if (packDir.exists()) {
            this.log.error("Will not overwrite existing directory. Please delete before runnign the generator: " + packDir.getAbsolutePath());
            throw new RuntimeException("Will not delete existing directory: " + packDir.getAbsolutePath());
        }
        FileUtils.forceMkdir(packDir);
        return packDir;
    }

    protected void generateSchema(Schema schema) throws Exception {
        if (schema.getEntityTypes().isEmpty() && schema.getComplexTypes().isEmpty()
                        && schema.getAssociations().isEmpty()) {
            this.log.warn("Ignoring java generation of empty schema named: " + schema.getNamespace());
            return;
        }

        File output = this.createOutputDir(schema);
        if (!schema.getEntityTypes().isEmpty()) {
            this.log.debug("\n\n----------Basic Types------------:\n");
            for (EntityType et : schema.getEntityTypes()) {
                File outputClass = new File(output, et.getName() + ".java");
                TypeWrapper wrapper = new TypeWrapper(et);
                HashMap<String, Object> root = Maps.newHashMap();
                root.put("typeWrapper", wrapper);
                Template temp = this.cfg.getTemplate("entity.ftl", "UTF-8");
                Writer out = new StringWriter();
                FileWriter writer = new FileWriter(outputClass);
                this.log.info("outputting: " + outputClass.getAbsolutePath());
                temp.process(root, writer);
                IOUtils.closeQuietly(out);
            }
        }
        if (!schema.getComplexTypes().isEmpty()) {
            this.log.debug("\n\n----------Complex Types------------:\n");
            System.err.println("\n\n----------TODO ADD IN COMPLEX TYPES------------:\n");
            for (ComplexType ct : schema.getComplexTypes()) {
                this.log.debug(ct.getName());
            }
        }
    }

    protected void createCfg() {
        try {
            File entityTmpl = new File(Generator.class.getClassLoader().getResource(
                            "com/studerb/odata/generate/entity.ftl").getFile());
            File configDir = entityTmpl.getParentFile();
            if (!configDir.exists()) {
                throw new RuntimeException("Freemarker Template Dir: " + configDir.getPath() + " doesn't exist");
            }
            this.cfg = new Configuration();
            this.cfg.setDirectoryForTemplateLoading(configDir);
            this.cfg.setObjectWrapper(new DefaultObjectWrapper());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
