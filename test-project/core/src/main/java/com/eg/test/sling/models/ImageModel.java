/**
 * 
 */
package com.eg.test.sling.models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eg.test.services.TagPageService;


/**
 * The Class ImageModel.
 */

@Model(adaptables = {Resource.class},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageModel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageModel.class);

	@Inject
	private TagPageService tagPageService;


	/**
	 * Inits the model.
	 */
	@PostConstruct
	public void initModel() {
		LOGGER.debug("Image Model Initialized");
	}

	/**
	 * Gets the image List.
	 *
	 * @param rssPath the rss path
	 * @return the job title
	 */
	public List<String> getImageListing() {
		return tagPageService.getTaggedPagesFromDAM();
	}
}