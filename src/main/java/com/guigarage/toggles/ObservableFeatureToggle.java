package com.guigarage.toggles;

/**
 * A observable feature
 * 
 * @author Hendrik Ebbers
 *
 */
public interface ObservableFeatureToggle extends FeatureObservable {
	
	/**
	 * Returns true is the feature is active
	 * @return true if the feature is active
	 */
	boolean isActive();
	
	/**
	 * Returns the name of the feature
	 * @return the name of the feature
	 */
	String getName();
}
