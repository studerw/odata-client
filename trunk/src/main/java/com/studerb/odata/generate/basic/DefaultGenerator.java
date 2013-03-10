package com.studerb.odata.generate.basic;

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
import com.studerb.odata.generate.Generator;

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
 *  DefaultGenerator generator = new DefaultGenerator().setOutputDir(outputDir);
 *  generator.generate(metadata);
 * }
 * </pre>
 * 
 * @author Bill Studer
 * @see Metadata
 * @see MetadataParser
 */
public class DefaultGenerator implements Generator {

    final Logger log = LoggerFactory.getLogger(DefaultGenerator.class);
    private File dir;
    Configuration cfg;


    public DefaultGenerator() {
        this.createCfg();
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.studerb.odata.generate.Generator#setOutputDir(java.io.File)
	 */
	@Override
	public Generator setOutputDir(File file) {
        this.dir = file;
        return this;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.studerb.odata.generate.Generator#generate(com.studerb.odata.edm.model
	 * .Metadata)
	 */
    @Override
	public void generate(Metadata metadata) throws Exception {
        if (this.dir == null) {
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
        File packDir = new File(this.dir, schema.getNamespace());
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
            File entityTmpl = new File(DefaultGenerator.class.getClassLoader().getResource(
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
