/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.showcase.twitter.connect;

import org.springframework.social.connect.support.OAuth1ConnectionFactory;

import twitter4j.Twitter;

/**
 * TwitterConnectionFactory that creates connections that expose the Twitter 4j API binding.
 * @author Craig Walls
 */
public class TwitterConnectionFactory extends OAuth1ConnectionFactory<Twitter> {

	public TwitterConnectionFactory(String consumerKey, String consumerSecret) {
		super("twitter", new TwitterServiceProvider(consumerKey, consumerSecret), new TwitterApiAdapter());
	}

}
