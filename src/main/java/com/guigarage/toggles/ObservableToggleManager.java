package com.guigarage.toggles;

import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.FeatureState;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Facade class for the observable toggles API.
 * Normally a <tt>ObservableToggleManager</tt> should be created to create <tt>ObservableFeatureToggle</tt> instances and listen to the state and changes of these feature toggles.
 *
 * @author Hendrik Ebbers
 */
public class ObservableToggleManager {

    private FeatureManager manager;
    private List<WeakReference<FeatureStateListener>> weakFeatureStateListeners;

    /**
     * Defines a <tt>ObservableToggleManager</tt> that uses a enum for all features. Internally a <tt>InMemoryObservableStateRepository</tt> and <tt>NoOpUserProvider</tt> is used to configure Togglz-
     *
     * @param featureClass The enum that defines all features
     */
    public ObservableToggleManager(Class<? extends Feature> featureClass) {
        this(new SimpleTogglzConfig<>(featureClass));
    }

    /**
     * Defines a <tt>ObservableToggleManager</tt> that is configured by the given <tt>ObservableTogglzConfig</tt>
     *
     * @param config The configuration
     */
    public ObservableToggleManager(ObservableTogglzConfig config) {
        manager = new FeatureManagerBuilder().togglzConfig(config).build();
        weakFeatureStateListeners = new CopyOnWriteArrayList<>();
        config.getStateRepository().addListener((feature, active) -> {

            for (WeakReference<FeatureStateListener> ref : weakFeatureStateListeners) {
                FeatureStateListener listener = ref.get();
                if (listener == null) {
                    weakFeatureStateListeners.remove(ref);
                } else {
                    listener.stateChanged(feature, active);
                }
            }
        });
    }

    /**
     * Returns the Togglz <tt>FeatureManager</tt> that is used internally
     *
     * @return the used Togglz <tt>FeatureManager</tt>
     */
    public FeatureManager getManager() {
        return manager;
    }

    /**
     * Returns an <tt>ObservableFeatureToggle</tt> for the given feature name.
     *
     * @param featureName Name of the feature
     * @return toggle for the given feature name
     */
    public ObservableFeatureToggle getFeatureToggle(String featureName) {
        return getFeatureToggle(getFeatureByName(featureName));
    }

    /**
     * Returns an <tt>ObservableFeatureToggle</tt> for the given Togglz feature.
     *
     * @param feature a Togglz feature
     * @return toggle for the given feature
     */
    public ObservableFeatureToggle getFeatureToggle(Feature feature) {
        DefaultFeatureToggle toggle = new DefaultFeatureToggle(feature, (t) -> isActive(t));
        weakFeatureStateListeners.add(new WeakReference<FeatureStateListener>(toggle));
        return toggle;
    }

    /**
     * Returns the registered feature with the given name
     *
     * @param featureName unique name of the feature
     * @return feature with the given name
     */
    public Feature getFeatureByName(String featureName) {
        for (Feature feature : getManager().getFeatures()) {
            if (feature.name().equals(featureName)) {
                return feature;
            }
        }
        return null;
    }

    /**
     * Checks if the Togglz feature is active
     *
     * @param feature the Togglz feature
     * @return true if the feature is active
     */
    public boolean isActive(Feature feature) {
        return manager.isActive(feature);
    }

    /**
     * Activates the given Togglz feature
     *
     * @param feature the Togglz feature that should be activated
     */
    public void activate(Feature feature) {
        manager.setFeatureState(new FeatureState(feature, true));
    }

    /**
     * Deactivates the given Togglz feature
     *
     * @param feature the Togglz feature that should be deactivated
     */
    public void deactivate(Feature feature) {
        manager.setFeatureState(new FeatureState(feature, false));
    }

    /**
     * Checks if the feature with the given unique name is active
     *
     * @param featureName unique name of the feature
     * @return true if the feature with the given unique name is active
     */
    public boolean isActive(String featureName) {
        return isActive(getFeatureByName(featureName));
    }

    /**
     * Activates the feature with the given name
     *
     * @param featureName unique name of the feature that should be activated
     */
    public void activate(String featureName) {
        activate(getFeatureByName(featureName));
    }

    /**
     * Deactivates the feature with the given name
     *
     * @param featureName unique name of the feature that should be deactivated
     */
    public void deactivate(String featureName) {
        deactivate(getFeatureByName(featureName));
    }
}
