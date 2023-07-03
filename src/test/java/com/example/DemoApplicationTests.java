package com.example;

import org.eclipse.sisu.space.SpaceModule;
import org.eclipse.sisu.space.URLClassSpace;
import org.eclipse.sisu.wire.WireModule;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class DemoApplicationTests {

	@Test
	public void test() {
		ClassLoader classloader = DemoApplication.class.getClassLoader();
		Injector injector = Guice.createInjector(
				new WireModule(
						new SpaceModule(
								new URLClassSpace(classloader))));
		injector.getProvider(DemoApplication.class).get().run();
	}
	
}
