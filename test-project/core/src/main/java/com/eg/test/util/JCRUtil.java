/**
 * 
 */
package com.eg.test.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.day.cq.search.QueryBuilder;

/**
 * @author Anju
 *
 */
public class JCRUtil {

	public static QueryBuilder getQueryBuilder() {
		BundleContext bundleContext = FrameworkUtil.getBundle(JCRUtil.class).getBundleContext();
		ServiceReference factoryRef = bundleContext.getServiceReference(QueryBuilder.class.getName());
		return (QueryBuilder) bundleContext.getService(factoryRef);

	}

}
