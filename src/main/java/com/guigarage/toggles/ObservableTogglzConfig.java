package com.guigarage.toggles;

import org.togglz.core.manager.TogglzConfig;

/**
 * Defines a Togglz configuration with observable support
 * @author hebbers
 *
 */
public interface ObservableTogglzConfig extends TogglzConfig {

	/* (non-Javadoc)
	 * @see org.togglz.core.manager.TogglzConfig#getStateRepository()
	 */
	ObservableStateRepository getStateRepository();
}
