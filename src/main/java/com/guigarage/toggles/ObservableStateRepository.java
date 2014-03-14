package com.guigarage.toggles;

import org.togglz.core.repository.StateRepository;

/**
 * Interface that defines a Togglz <tt>StateRepository</tt> with observable support
 * 
 * @author Hendrik Ebbers
 *
 */
public interface ObservableStateRepository extends StateRepository,
		FeatureObservable {

}
