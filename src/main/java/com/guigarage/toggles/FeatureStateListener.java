package com.guigarage.toggles;

import org.togglz.core.Feature;

/**
 * A listener for the feature state
 * 
 * @author Hendrik Ebbers
 *
 */
@FunctionalInterface
public interface FeatureStateListener {

	/**
	 * If the Listene is registered this method will be called if the active state of a feature changes
	 * @param feature The fetuare
	 * @param active The current state
	 */
	public void stateChanged(Feature feature, boolean active);
}
