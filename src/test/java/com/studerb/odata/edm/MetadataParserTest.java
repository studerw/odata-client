package com.studerb.odata.edm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.studerb.odata.edm.model.Association;
import com.studerb.odata.edm.model.AssociationEnd;
import com.studerb.odata.edm.model.AssociationSet;
import com.studerb.odata.edm.model.AssociationSetEnd;
import com.studerb.odata.edm.model.ComplexType;
import com.studerb.odata.edm.model.DataService;
import com.studerb.odata.edm.model.EntityContainer;
import com.studerb.odata.edm.model.EntitySet;
import com.studerb.odata.edm.model.EntityType;
import com.studerb.odata.edm.model.Metadata;
import com.studerb.odata.edm.model.Property;
import com.studerb.odata.edm.model.Schema;

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

    @Test
    public void getAllSections() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/northwind/northwind_metadata.xml");
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());

        Resource r2 = this.appContext.getResource("classpath:com/studerb/odata/netflix/netflix_metadata.xml");
        MetadataParser parser2 = new MetadataParser();
        Metadata metadata2 = parser2.parseXml(r2.getInputStream());
    }

    @Test
    public void NorthwindCheck() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/northwind/northwind_metadata.xml");
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());
        assertTrue(metadata.getDataServices().size() == 1);
        DataService ds = metadata.getDataServices().get(0);
        assertEquals(ds.getVersion(), "1.0");
		assertNull(ds.getMaxVersion());

        List<Schema> schemas = ds.getSchemas();
        assertTrue(schemas.size() == 2);
        Schema nwModel = schemas.get(0);
        log.debug("Model Namespace: " + nwModel.getNamespace());
        assertEquals(nwModel.getNamespace(), "NorthwindModel");
        List<EntityType> eTypes = nwModel.getEntityTypes();
        log.debug("Entity Types: " + eTypes.size());
        for (EntityType type : eTypes) {
            log.debug(type.getName());
        }
        assertTrue(eTypes.size() == 26);
        EntityType category = eTypes.get(0);
        assertEquals(category.getName(), "Category");
        assertEquals(category.getKeys().get(0), "CategoryID");
        List<String> props = Lists.transform(category.getProperties(), new Function<Property, String>() {
            @Override
            public String apply(Property input) {
                return input.getName();
            }
        });
        assertEquals(props, Lists.newArrayList("CategoryID", "CategoryName", "Description", "Picture"));
        Property catId = category.getProperties().get(0);
        assertEquals(catId.getName(), "CategoryID");
        assertEquals(catId.getType(), "Edm.Int32");
        assertFalse(catId.getNullable());

        List<Association> associations = nwModel.getAssociations();
        log.debug("Associations: " + associations.size());
        assertTrue(associations.size() == 11);
        for (Association a : associations) {
            log.debug(a.getName());
        }
        Association prod_cat = associations.get(0);
        log.debug(prod_cat.getName());
        assertEquals(prod_cat.getName(), "FK_Products_Categories");
        List<AssociationEnd> ends = prod_cat.getEnds();
        assertTrue(ends.size() == 2);
        AssociationEnd categories = ends.get(0);
        assertEquals(categories.getType(), "NorthwindModel.Category");
        assertEquals(categories.getMultiplicity(), "0..1");
        AssociationEnd products = ends.get(1);
        assertEquals(products.getType(), "NorthwindModel.Product");
        assertEquals(products.getMultiplicity(), "*");

        Schema container = schemas.get(1);
        log.debug("Second Schema: " + container.getNamespace());
        assertEquals(container.getNamespace(), "ODataWeb.Northwind.Model");
        assertNull(container.getAlias());

        EntityContainer ec = container.getEntityContainers().get(0);
        assertEquals(ec.getName(), "NorthwindEntities");
        List<EntitySet> eSets = ec.getEntitySets();
        log.debug("Entity Sets: " + eSets.size());
        assertTrue(eSets.size() == 26);
        EntitySet eSet = eSets.get(0);
        assertEquals(eSet.getName(), "Categories");
        assertEquals(eSet.getEntityType(), "NorthwindModel.Category");

        List<AssociationSet> aSets = ec.getAssociationSets();
        log.debug("Association Sets: " + aSets.size());
        assertTrue(aSets.size() == 11);
        AssociationSet aSet = aSets.get(0);
        log.debug(aSet.getName() + " / " + aSet.getAssociation());
        assertEquals(aSet.getName(), "FK_Products_Categories");
        List<AssociationSetEnd> aSetEnds = aSet.getEnds();
        assertTrue(aSetEnds.size() == 2);
        AssociationSetEnd end1 = aSetEnds.get(0);
        log.debug(end1.getRole() + " / " + end1.getEntitySet());
        assertEquals(end1.getRole(), "Categories");
        assertEquals(end1.getEntitySet(), "Categories");
    }

    @Test
    public void V3ParseTest() throws Exception {
        Resource r = this.appContext.getResource("classpath:com/studerb/odata/v3/odata_v3_example_metadata.xml");
        MetadataParser parser = new MetadataParser();
        Metadata metadata = parser.parseXml(r.getInputStream());
        assertTrue(metadata.getDataServices().size() == 1);
        DataService ds = metadata.getDataServices().get(0);
		assertEquals(ds.getVersion(), "3.0");
		assertEquals(ds.getMaxVersion(), "3.0");

        List<Schema> schemas = ds.getSchemas();
		assertTrue(schemas.size() == 1);
		Schema odataDemo = schemas.get(0);
		log.debug("Model Namespace: " + odataDemo.getNamespace());
		assertEquals(odataDemo.getNamespace(), "ODataDemo");
		List<EntityType> eTypes = odataDemo.getEntityTypes();
        log.debug("Entity Types: " + eTypes.size());
        for (EntityType type : eTypes) {
            log.debug(type.getName());
        }
		assertTrue(eTypes.size() == 5);
		EntityType product = eTypes.get(0);
		assertEquals(product.getName(), "Product");
		assertEquals(product.getKeys().get(0), "ID");
        assertNull(product.getBaseType());
		List<String> props = Lists.transform(product.getProperties(), new Function<Property, String>() {
			@Override
			public String apply(Property input) {
				return input.getName();
			}
		});
		assertEquals(props, Lists.newArrayList("ID", "Name", "Description", "ReleaseDate", "DiscontinuedDate", "Rating", "Price"));
		Property prodId = product.getProperties().get(0);
		assertEquals(prodId.getName(), "ID");
		assertEquals(prodId.getType(), "Edm.Int32");
		assertFalse(prodId.getNullable());

        // check basetype
        EntityType featuredProduct = eTypes.get(1);
        assertEquals(featuredProduct.getName(), "FeaturedProduct");
        log.debug("Base type of featured product: " + featuredProduct.getBaseType());
        assertEquals(featuredProduct.getBaseType(), "ODataDemo.Product");

		List<ComplexType> complexTypes = odataDemo.getComplexTypes();
		assertTrue(complexTypes.size() == 1);
		ComplexType address = complexTypes.get(0);
		assertEquals(address.getName(), "Address");
		List<String> cprops = Lists.transform(address.getProperties(), new Function<Property, String>() {
            @Override
            public String apply(Property input) {
                return input.getName();
            }
        });
		for (String s : cprops) {
			log.debug(s);
		}
		assertEquals(cprops, Lists.newArrayList("Street", "City", "State", "ZipCode", "Country"));

		List<Association> associations = odataDemo.getAssociations();
        log.debug("Associations: " + associations.size());
		assertTrue(associations.size() == 3);
        for (Association a : associations) {
            log.debug(a.getName());
        }
		Association prod_cat_cat_prods = associations.get(0);
		log.debug(prod_cat_cat_prods.getName());
		assertEquals(prod_cat_cat_prods.getName(), "Product_Category_Category_Products");
		List<AssociationEnd> ends = prod_cat_cat_prods.getEnds();
        assertTrue(ends.size() == 2);
        AssociationEnd categories = ends.get(0);
		assertEquals(categories.getType(), "ODataDemo.Category");
        assertEquals(categories.getMultiplicity(), "0..1");
        AssociationEnd products = ends.get(1);
		assertEquals(products.getType(), "ODataDemo.Product");
        assertEquals(products.getMultiplicity(), "*");

		EntityContainer ec = odataDemo.getEntityContainers().get(0);
		assertEquals(ec.getName(), "DemoService");
        List<EntitySet> eSets = ec.getEntitySets();
        log.debug("Entity Sets: " + eSets.size());
		assertTrue(eSets.size() == 4);
		assertEquals(eSets.get(1).getName(), "Advertisements");

        List<AssociationSet> aSets = ec.getAssociationSets();
        log.debug("Association Sets: " + aSets.size());
		assertTrue(aSets.size() == 3);
        AssociationSet aSet = aSets.get(0);
        log.debug(aSet.getName() + " / " + aSet.getAssociation());
		assertEquals(aSet.getName(), "Products_Advertisement_Advertisements");
        List<AssociationSetEnd> aSetEnds = aSet.getEnds();
        assertTrue(aSetEnds.size() == 2);
        AssociationSetEnd end1 = aSetEnds.get(0);
        log.debug(end1.getRole() + " / " + end1.getEntitySet());
		assertEquals(end1.getRole(), "FeaturedProduct_Advertisement");
		assertEquals(end1.getEntitySet(), "Products");

    }
}
