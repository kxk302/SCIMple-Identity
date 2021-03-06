package edu.psu.swe.scim.spec.protocol.attribute;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import edu.psu.swe.scim.spec.validator.Urn;
import lombok.Data;

@Data
public class AttributeReference implements Serializable {

  private static final long serialVersionUID = -3559538009692681470L;

  @Urn
  String urn;

  String[] attributeName;

  protected AttributeReference() {
  }

  public AttributeReference(String attributeReference) {
    String substringBeforeLast = StringUtils.substringBeforeLast(attributeReference, ":");
    String substringAfterLast = StringUtils.substringAfterLast(attributeReference, ":");

    if (StringUtils.isEmpty(substringAfterLast)) {
      urn = null;
      attributeName = parseAttributeName(substringBeforeLast);
    } else {
      urn = substringBeforeLast;
      attributeName = parseAttributeName(substringAfterLast);
    }
  }

  public AttributeReference(String urn, String attributeName) {
    this.urn = urn;
    this.attributeName = parseAttributeName(attributeName);
  }

  private String[] parseAttributeName(String attributeName) {
    return StringUtils.split(attributeName, ".");
  }

  public String getFullAttributeName() {
    return StringUtils.join(attributeName, ".");
  }

  public String getFullyQualifiedAttributeName() {
    StringBuilder sb = new StringBuilder();
    if (urn != null) {
      sb.append(urn);
    }
    if (urn != null && attributeName != null) {
      sb.append(":");
    }
    if (attributeName != null) {
      sb.append(StringUtils.join(attributeName, "."));
    }
    return sb.toString();
  }

  public String getAttributeBase() {
    return (urn != null ? (urn + ":") : "") + (attributeName != null ? StringUtils.join(attributeName, ".", 0, attributeName.length - 1) : "");
  }

}
