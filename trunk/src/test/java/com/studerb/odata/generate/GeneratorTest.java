package com.studerb.odata.generate;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studerb.odata.edm.MetadataParser;
import com.studerb.odata.edm.model.Metadata;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/test-context.xml" })
public class GeneratorTest {

    @Inject ApplicationContext appContext;

    final static File CREATE_DIR = new File(SystemUtils.getJavaIoTmpDir(), "_ODATA_GENERATE_");
    final static File EXISTING = new File(SystemUtils.getJavaIoTmpDir(), "_ODATA_EXISTING_");

    @BeforeClass
    public static void before() throws IOException {
        if (CREATE_DIR.exists()) {
            FileUtils.cleanDirectory(CREATE_DIR);
        }
        if (EXISTING.exists()) {
            FileUtils.cleanDirectory(EXISTING);
        }
        CREATE_DIR.mkdirs();
        EXISTING.mkdirs();
    }

    @AfterClass
    public static void after() throws IOException {
        if (CREATE_DIR.exists()) {
            // FileUtils.forceDelete(CREATE_DIR);
        }
        if (EXISTING.exists()) {
            FileUtils.forceDelete(EXISTING);
        }
    }

    @Test
    public void generateNorthwind() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/northwind/northwind_metadata.xml");
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());
        new Generator(CREATE_DIR).generate(metadata);
    }

    @Test
    public void generateNetflix() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/netflix/netflix_metadata.xml");
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());
        new Generator(CREATE_DIR).generate(metadata);
    }

    @Test
    public void generateV3() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/v3/odata_v3_example_metadata.xml");
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());
        new Generator(CREATE_DIR).generate(metadata);
    }

    @Test(expected = RuntimeException.class)
    public void ExistingOutputDir() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/netflix/netflix_metadata.xml");
        Assert.assertTrue("Existing dir exists: " + EXISTING.getAbsolutePath(), EXISTING.exists());
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());
        File existing = new File(EXISTING, "NetflixCatalog.Model");
        existing.mkdirs();
        new Generator(EXISTING).generate(metadata);
    }

}
