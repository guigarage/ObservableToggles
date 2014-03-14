package com.guigarage.toggles;

/**
 * Defines an observable feature.
 * 
 * @author Hendrik Ebbers
 *
 */
public interface FeatureObservable {

	/**
	 * Registeres the listener. Thi listener will be notified whenever the state of the feature changes.
	 * 
	 * @param listener a listener
	 */
	public void addListener(FeatureStateListener listener);
	
	/**
	 * Deregisteres the listener
	 * 
	 * @param listener a listener
	 */
	public void removeListener(FeatureStateListener listener);
}
