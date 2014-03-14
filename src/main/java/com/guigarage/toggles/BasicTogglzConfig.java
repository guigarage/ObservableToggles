package com.guigarage.toggles;

import org.togglz.core.Feature;
import org.togglz.core.user.UserProvider;

/**
 * Basic configuration class for features that are defined by an enum
 * 
 * @author Hendrik Ebbers
 *
 * @param <T>
 */
public class BasicTogglzConfig<T extends Feature> implements ObservableTogglzConfig {

	private Class<T> featureClass;
	
	private ObservableStateRepository stateRepository;
	
	private UserProvider userProvider;
	
	/**
	 * Default constructor
	 * 
	 * @param featureClass The class of the enum that defines all features
	 * @param stateRepository A Togglz state repository
	 * @param userProvider a Togglz user provider
	 */
	public BasicTogglzConfig(Class<T> featureClass, ObservableStateRepository stateRepository, UserProvider userProvider) {
		this.featureClass = featureClass;
		this.stateRepository = stateRepository;
		this.userProvider = userProvider;
	}
	
	/* (non-Javadoc)
	 * @see org.togglz.core.manager.TogglzConfig#getFeatureClass()
	 */
	public Class<T> getFeatureClass() {
		return featureClass;
	}

	/* (non-Javadoc)
	 * @see com.guigarage.toggles.ObservableTogglzConfig#getStateRepository()
	 */
	public ObservableStateRepository getStateRepository() {
		return stateRepository;
	}

	/* (non-Javadoc)
	 * @see org.togglz.core.manager.TogglzConfig#getUserProvider()
	 */
	public UserProvider getUserProvider() {
		return userProvider;
	}

}
