package org.jboss.tools.usage.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.eclipse.core.runtime.IBundleGroupProvider;
import org.jboss.tools.usage.reporting.JBossToolsComponents;
import org.jboss.tools.usage.test.fakes.BundleGroupProviderFake;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class JBossToolsComponentsTest {

	@Test
	public void reportedComponentsListIsComplete() {
		Collection<String> componentIds = JBossToolsComponents.getComponentIds(
				new IBundleGroupProvider[] {
						new BundleGroupProviderFake("org.jboss.tools.gwt.feature")
						, new BundleGroupProviderFake(
								"rubbish",
								"org.jboss.tools.seam.feature")
						, new BundleGroupProviderFake("org.jboss.tools.smooks.feature")
						, new BundleGroupProviderFake("org.jboss.tools.usage.feature.bandname")
					});

		assertThat(componentIds, JUnitMatchers.hasItems(
				JBossToolsComponents.JBossToolsFeatureNames.GWT.getAbbreviation(),
				JBossToolsComponents.JBossToolsFeatureNames.SEAM.getAbbreviation(),
				JBossToolsComponents.JBossToolsFeatureNames.SMOOKS.getAbbreviation()));
		assertFalse(componentIds.contains(JBossToolsComponents.JBossToolsFeatureNames.USAGE.getAbbreviation()));
	}
}