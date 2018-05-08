/**
 * 
 */
package com.omniselling.common.biz.operation;

/**
 * Base operation interface, all BizOperation should extend this one
 * @author Atomic
 *
 */
public interface IBizDomainOperation
{
    /**
     * Get the current domain of such operation
     * @return Domain name
     */
    String getDomain();

}
