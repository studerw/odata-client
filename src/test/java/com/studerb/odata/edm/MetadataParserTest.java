package com.studerb.odata.edm;

import java.io.BufferedInputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring/test-context.xml" })
public class MetadataParserTest {
    final static Logger log = Logger.getLogger(MetadataParserTest.class);

    @Inject
    ApplicationContext appContext;

    BufferedInputStream in;

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {
        IOUtils.closeQuietly(this.in);
    }

    @Test
    public void checkGoodMetadataFiles() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/northwind/northwind_metadata.xml");
        MetadataParser parser = new MetadataParser();
        parser.parseXml(r.getInputStream());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkBadMetadataFiles() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/northwind/Employees.xml");
        MetadataParser parser = new MetadataParser();
        parser.parseXml(r.getInputStream());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyMetadataFiles() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/Empty.xml");
        MetadataParser parser = new MetadataParser();
        parser.parseXml(r.getInputStream());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkBlankMetadataFiles() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/Blank.xml");
        MetadataParser parser = new MetadataParser();
        parser.parseXml(r.getInputStream());
    }

}
