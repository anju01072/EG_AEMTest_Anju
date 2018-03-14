/**
 * 
 */
package com.eg.test.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.eg.test.services.TagPageService;
import com.eg.test.util.JCRUtil;

/**
 * @author Anju
 *
 */
@Component(label = "Retrive Tagged Pages", description = "Service to get tagged pages from DAM", immediate = true, metatype = true)
@Service(TagPageService.class)
public class TagPageServiceImpl implements TagPageService{


	private ResourceResolver resourceResolver = null;

	@Reference
	private ResourceResolverFactory resourceResolverFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TagPageService.class);

	/**
	 * Activate.
	 *
	 * @param componentContext the component context
	 */
	@Activate
	protected void activate(ComponentContext componentContext) {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, "readService");
		try {
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
		} catch (LoginException e) {
			LOGGER.error("Login Exception" + e);
		}
	}


	@Override
	public List<String> getTaggedPagesFromDAM() {

		List<String> taggedPageList = new ArrayList<String>();

		QueryBuilder queryBuilder = JCRUtil.getQueryBuilder();
		String searchPath = "/content/dam/";
		Session session = resourceResolver.adaptTo(Session.class);
		Map<String, String> map = new HashMap<>();
		map.put("path", searchPath);
		map.put("type", "cq:Page");
		map.put("property", "jcr:content/cq:tags");
		map.put("property.value", "%_%");
		map.put("property.operation", "like");
		map.put("p.limit", "1"); 
		Query query = queryBuilder.createQuery(PredicateGroup.create(map),
				session);
		try {
			for (Hit hit : query.getResult().getHits()) {
				taggedPageList.add(hit.getPath());
			}

		} catch (RepositoryException e) {
			LOGGER.error("Error during getTaggedPagesFromDAM:", e.toString(), e);
		}

		return taggedPageList;
	}

}
