package edu.psu.swe.scim.errai.client.models;

import org.jboss.errai.common.client.api.annotations.Portable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A DTO that describes the Patch sub-object of the SCIM
 * ServiceProviderConfig according to section 5 of the SCIM Schema
 * Specification.  See:
 * 
 * https://tools.ietf.org/html/rfc7643#section-5
 * 
 * @author Steve Moyer &lt;smoyer@psu.edu&gt;
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Portable
public class PatchConfig extends SupportedConfig {

}
