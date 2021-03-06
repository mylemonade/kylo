package com.thinkbiganalytics.ui;

/*-
 * #%L
 * kylo-ui-app
 * %%
 * Copyright (C) 2017 ThinkBig Analytics
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;

/**
 * Created by sr186054 on 2/24/17.
 */
@Configuration
public class FeedManagerWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(FeedManagerWebMvcConfigurerAdapter.class);

    @Value("${static.path:}")
    private String staticPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/feed-mgr/**").addResourceLocations("classpath:/static/").resourceChain(true).addResolver(new GzipResourceResolver());;
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").resourceChain(true).addResolver(new GzipResourceResolver());;

        if (StringUtils.isNotBlank(staticPath)) {
            //static path will not be defined when running from source
            LOG.info("Setting up static resources to be at '{}'", staticPath);
            registry.addResourceHandler("/feed-mgr/assets/**").addResourceLocations("file:" + staticPath).resourceChain(true).addResolver(new GzipResourceResolver());;
            registry.addResourceHandler("/assets/**").addResourceLocations("file:" + staticPath).resourceChain(true).addResolver(new GzipResourceResolver());;
        }
    }
}
