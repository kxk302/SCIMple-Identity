package edu.psu.swe.scim.client.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import edu.psu.swe.scim.spec.protocol.attribute.AttributeReference;
import edu.psu.swe.scim.spec.protocol.filter.AttributeComparisonExpression;
import edu.psu.swe.scim.spec.protocol.filter.CompareOperator;
import edu.psu.swe.scim.spec.protocol.filter.FilterParseException;
import edu.psu.swe.scim.spec.protocol.search.Filter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(JUnitParamsRunner.class)
public class FilterBuilderTest {

//  address.type EQ "work"
//  address.primary EQ true
//  address.primary EQ false
//  address.primary EQ null
//  address.number EQ 123
//  address.number EQ 123 OR address.primary EQ null
//  address.number EQ 123 AND address.primary EQ null
//  address.number EQ 123 OR NOT(address.primary EQ null)
//  NOT(address.number EQ 123) OR NOT(address.primary EQ null)
//  (address.number EQ 123) OR (address.primary EQ null)
//  ((address.number EQ 123) OR (address.primary EQ null))
//  address.primary PR
//  NOT(address.primary PR)
//  urn:scimscim:Custom+2.0:address EQ "work"
//  urn:justlongenoughnidxxxxxxxxxxxxxxx:Custom:address EQ "work"
//  urn:scim:Custom()+,-.:=@;$_!*\nss:address EQ "work"
//  address.type2_-3 EQ "work"
//
//  Invalid:
//  address.type EQ "work" MAYBE address.type EQ "home"
//  address.type YZ "work"
//  address.type EQ work
//  address..type EQ "work"
//  .address.type EQ "work"
//  address[street[apt EQ "100"]]
//  address[type EQ "work" AND street[apt EQ "100"]]
//  urn:scim+scim:Custom:address EQ "work"
//  urn:scim+scim:Custom.address EQ "work"
//  urn:reallyreallyreallyreallylongnidxx:Custom:address EQ "work"
//  address.type EQ "work" OR "home"
//  NOT(address.primary)
//  address^.type EQ "work"
//  address,type EQ "work"
//  address.2type EQ "work"

  
  FilterBuilder filterBuilder;
  AttributeReference attributeReference;
  AttributeReference attributeReference2;
  AttributeComparisonExpression attributeComparisonExpression;
  AttributeComparisonExpression attributeComparisonExpression2;

  Filter filter;

  @Before
  public void initialize() {
    filterBuilder = new FilterBuilder();

    attributeReference = new AttributeReference("addresses.streetAddress");

    attributeComparisonExpression = new AttributeComparisonExpression(attributeReference, CompareOperator.EW, "Cir");

    attributeReference2 = new AttributeReference("name.givenName");
    attributeComparisonExpression2 = new AttributeComparisonExpression(attributeReference2, CompareOperator.EQ, "Bilbo");
  }


  @Test
  public void testAnd() {

    try {
      System.out.println(filterBuilder.and(attributeComparisonExpression, attributeComparisonExpression2).build());
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // fail("Not yet implemented");
  }

  @Test
  public void testOr() throws UnsupportedEncodingException, FilterParseException {

    String encoded = filterBuilder.equalTo("addresses.postalCode", "16801")
                                  .and()
                                  .or(attributeComparisonExpression, attributeComparisonExpression2)
                                  .build();

    log.info(encoded);
    
    String decoded = URLDecoder.decode(encoded, "UTF-8").replace("%20", " ");
    
    log.info(decoded);
    
    Filter filter = new Filter(decoded);
  }

}