package com.studerb.odata.edm.netflix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.util.List;

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

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.studerb.odata.edm.MetadataParser;
import com.studerb.odata.edm.model.Association;
import com.studerb.odata.edm.model.AssociationEnd;
import com.studerb.odata.edm.model.AssociationSet;
import com.studerb.odata.edm.model.AssociationSetEnd;
import com.studerb.odata.edm.model.DataService;
import com.studerb.odata.edm.model.EntityContainer;
import com.studerb.odata.edm.model.EntitySet;
import com.studerb.odata.edm.model.EntityType;
import com.studerb.odata.edm.model.Metadata;
import com.studerb.odata.edm.model.Property;
import com.studerb.odata.edm.model.Schema;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring/test-context.xml" })
public class NetflixMetdataParserTest {
    final static Logger log = Logger.getLogger(NetflixMetdataParserTest.class);

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
    public void getAllSections() throws Exception {
        Resource r2 = this.appContext.getResource("classpath:com/studerb/odata/netflix/netflix_metadata.xml");
        MetadataParser parser2 = new MetadataParser();
        Metadata metadata2 = parser2.parseXml(r2.getInputStream());
        assertNotNull(metadata2);
    }

    @Test
    public void NetflixCheck() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/netflix/netflix_metadata.xml");
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());
        assertTrue(metadata.getDataServices().size() == 1);
        DataService ds = metadata.getDataServices().get(0);
        assertEquals(ds.getVersion(), "2.0");
        assertNull(ds.getMaxVersion());

        List<Schema> schemas = ds.getSchemas();
        assertTrue(schemas.size() == 2);
        Schema netflixModel = schemas.get(0);
        log.debug("Model Namespace: " + netflixModel.getNamespace());
        assertEquals(netflixModel.getNamespace(), "NetflixCatalog.Model");
        List<EntityType> eTypes = netflixModel.getEntityTypes();
        log.debug("Entity Types: " + eTypes.size());
        for (EntityType type : eTypes) {
            log.debug(type.getName());
        }
        assertTrue(eTypes.size() == 7);
        EntityType genre = eTypes.get(0);
        assertEquals(genre.getName(), "Genre");
        assertEquals(genre.getKeys().get(0), "Name");
        List<String> props = Lists.transform(genre.getProperties(), new Function<Property, String>() {
            @Override
            public String apply(Property input) {
                return input.getName();
            }
        });
        assertEquals(props, Lists.newArrayList("Name"));
        Property name = genre.getProperties().get(0);
        assertEquals(name.getName(), "Name");
        assertEquals(name.getType(), "Edm.String");
        assertFalse(name.getNullable());
        assertTrue(name.getMaxLength().equals("50"));
        assertFalse(name.getUnicode());
        assertFalse(name.getFixedLength());


        List<Association> associations = netflixModel.getAssociations();
        log.debug("Associations: " + associations.size());
        assertTrue(associations.size() == 12);
        for (Association a : associations) {
            log.debug(a.getName());
        }
        Association titleAudioFormat = associations.get(0);
        log.debug(titleAudioFormat.getName());
        assertEquals(titleAudioFormat.getName(), "FK_TitleAudioFormat_Title");
        List<AssociationEnd> ends = titleAudioFormat.getEnds();
        assertTrue(ends.size() == 2);
        AssociationEnd titles = ends.get(0);
        assertEquals(titles.getType(), "NetflixCatalog.Model.Title");
        assertEquals(titles.getMultiplicity(), "1");
        AssociationEnd titleAudioFormats= ends.get(1);
        assertEquals(titleAudioFormats.getType(), "NetflixCatalog.Model.TitleAudioFormat");
        assertEquals(titleAudioFormats.getMultiplicity(), "*");

        Schema container = schemas.get(1);
        log.debug("Second Schema: " + container.getNamespace());
        assertEquals(container.getNamespace(), "Netflix.Catalog");
        assertNull(container.getAlias());

        EntityContainer ec = container.getEntityContainers().get(0);
        assertEquals(ec.getName(), "NetflixCatalog");
        List<EntitySet> eSets = ec.getEntitySets();
        log.debug("Entity Sets: " + eSets.size());
        assertTrue(eSets.size() == 7);
        EntitySet eSet = eSets.get(0);
        assertEquals(eSet.getName(), "Genres");
        assertEquals(eSet.getEntityType(), "NetflixCatalog.Model.Genre");

        List<AssociationSet> aSets = ec.getAssociationSets();
        log.debug("Association Sets: " + aSets.size());
        assertTrue(aSets.size() == 12);
        AssociationSet aSet = aSets.get(0);
        log.debug(aSet.getName() + " / " + aSet.getAssociation());
        assertEquals(aSet.getName(), "FK_TitleAudioFormat_Title");
        List<AssociationSetEnd> aSetEnds = aSet.getEnds();
        assertTrue(aSetEnds.size() == 2);
        AssociationSetEnd end1 = aSetEnds.get(0);
        log.debug(end1.getRole() + " / " + end1.getEntitySet());
        assertEquals(end1.getRole(), "Titles");
        assertEquals(end1.getEntitySet(), "Titles");
    }


}
