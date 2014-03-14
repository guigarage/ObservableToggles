package com.guigarage.toggles;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;

public enum SampleFeatures implements Feature {

	FEATURE1,
	
	FEATURE2,
	
	@EnabledByDefault
	FEATURE3;
}
