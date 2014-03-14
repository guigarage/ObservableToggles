package com.guigarage.toggles;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.mem.InMemoryStateRepository;

/**
 * A special Togglz <tt>StateRepository</tt> that will fire events to all listeners for each change in the state of a feature.
 * @author Hendrik Ebbers
 *
 */
public class InMemoryObservableStateRepository extends InMemoryStateRepository
		implements ObservableStateRepository {

	private List<FeatureStateListener> listeners;

	/**
	 * Default Constructor
	 */
	public InMemoryObservableStateRepository() {
		listeners = new CopyOnWriteArrayList<>();
	}

	/* (non-Javadoc)
	 * @see com.guigarage.toggles.FeatureObservable#addListener(com.guigarage.toggles.FeatureStateListener)
	 */
	@Override
	public void addListener(FeatureStateListener listener) {
		listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.guigarage.toggles.FeatureObservable#removeListener(com.guigarage.toggles.FeatureStateListener)
	 */
	@Override
	public void removeListener(FeatureStateListener listener) {
		listeners.remove(listener);
	}

	private void fire(Feature f, boolean active) {
		for (FeatureStateListener listener : listeners) {
			listener.stateChanged(f, active);
		}
	}

	/* (non-Javadoc)
	 * @see org.togglz.core.repository.mem.InMemoryStateRepository#setFeatureState(org.togglz.core.repository.FeatureState)
	 */
	@Override
	public void setFeatureState(FeatureState featureState) {
		super.setFeatureState(featureState);
		fire(featureState.getFeature(), featureState.isEnabled());
	}
}
