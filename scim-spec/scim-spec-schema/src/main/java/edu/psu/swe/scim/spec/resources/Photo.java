package edu.psu.swe.scim.spec.resources;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.psu.swe.scim.spec.annotation.ScimAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Scim core schema, <a href="https://tools.ietf.org/html/rfc7643#section-4.1.2>section 4.1.2</a>
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
@Data
@EqualsAndHashCode(callSuper=false)
public class Photo extends KeyedResource implements Serializable, TypedAttribute {
  
  private static final long serialVersionUID = 8821620834716156789L;
 
  @XmlElement
  @ScimAttribute(description="URL of a photo of the User.", referenceTypes={"external"})
  String value;
  
  @XmlElement(nillable=true)
  @ScimAttribute(canonicalValueList={"photo", "thumbnail"}, description="A label indicating the attribute's function; e.g., 'photo' or 'thumbnail'.")
  String type;
  
  @XmlElement
  @ScimAttribute(description="A human readable name, primarily used for display purposes. READ-ONLY.")
  String display;
  
  @XmlElement
  @ScimAttribute(description="A Boolean value indicating the 'primary' or preferred attribute value for this attribute, e.g. the preferred mailing address or primary e-mail address. The primary attribute value 'true' MUST appear no more than once.")
  Boolean primary = false;
}
