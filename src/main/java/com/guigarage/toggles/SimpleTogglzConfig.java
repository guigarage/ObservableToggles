package com.guigarage.toggles;

import org.togglz.core.Feature;
import org.togglz.core.user.NoOpUserProvider;

/**
 * A simple Togglz configuration with observable support. The configuration will use a enum that defines all features, a <tt>InMemoryObservableStateRepository</tt> and a <tt>NoOpUserProvider</tt>
 * @author hebbers
 *
 * @param <T>
 */
public class SimpleTogglzConfig<T extends Feature> extends BasicTogglzConfig<T> {

	/**
	 * Default Constructor
	 * 
	 * @param featureClass enum class that defines all features
	 */
	public SimpleTogglzConfig(Class<T> featureClass) {
		super(featureClass, new InMemoryObservableStateRepository(), new NoOpUserProvider());
	}

}
