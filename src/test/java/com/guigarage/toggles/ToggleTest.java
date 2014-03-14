package com.guigarage.toggles;

import org.junit.Assert;

import org.junit.Test;
import org.togglz.core.Feature;

public class ToggleTest {

	private boolean activeState;
	
	@Test
	public void test1() {
		ObservableToggleManager manager = new ObservableToggleManager(SampleFeatures.class);
		Assert.assertFalse(manager.isActive(SampleFeatures.FEATURE1));
		Assert.assertFalse(manager.isActive(SampleFeatures.FEATURE2));
		Assert.assertTrue(manager.isActive(SampleFeatures.FEATURE3));
		
		manager.activate(SampleFeatures.FEATURE1);
		Assert.assertTrue(manager.isActive(SampleFeatures.FEATURE1));
		
		ObservableFeatureToggle toggle1 = manager.getFeatureToggle(SampleFeatures.FEATURE1);
		Assert.assertTrue(toggle1.isActive());
		manager.deactivate(SampleFeatures.FEATURE1);
		Assert.assertFalse(toggle1.isActive());
		
		
		activeState = false;
		toggle1.addListener(new FeatureStateListener() {
			
			@Override
			public void stateChanged(Feature feature, boolean active) {
				activeState = active;
			}
		});
		manager.activate(SampleFeatures.FEATURE1);
		Assert.assertTrue(activeState);
	}
	
	@Test
	public void testByName() {
		ObservableToggleManager manager = new ObservableToggleManager(SampleFeatures.class);
		Assert.assertFalse(manager.isActive("FEATURE1"));
		Assert.assertFalse(manager.isActive("FEATURE2"));
		Assert.assertTrue(manager.isActive("FEATURE3"));
		
		manager.activate("FEATURE1");
		Assert.assertTrue(manager.isActive("FEATURE1"));
		
		ObservableFeatureToggle toggle1 = manager.getFeatureToggle("FEATURE1");
		Assert.assertTrue(toggle1.isActive());
		manager.deactivate("FEATURE1");
		Assert.assertFalse(toggle1.isActive());
		
		
		activeState = false;
		toggle1.addListener(new FeatureStateListener() {
			
			@Override
			public void stateChanged(Feature feature, boolean active) {
				activeState = active;
			}
		});
		manager.activate(SampleFeatures.FEATURE1);
		Assert.assertTrue(activeState);
	}
}
