package com.example;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.sisu.space.SpaceModule;
import org.eclipse.sisu.space.URLClassSpace;
import org.eclipse.sisu.wire.WireModule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Named
public class DemoApplication implements Runnable {

	private Service service;

	@Inject
	public DemoApplication(Service service) {
		this.service = service;
		System.err.println("DemoApplication");
	}

	public static void main(String[] args) throws Exception {
		ClassLoader classloader = DemoApplication.class.getClassLoader();

		Injector injector = Guice.createInjector(
				new WireModule(
						new SpaceModule(
								new URLClassSpace(classloader))));
		injector.getProvider(DemoApplication.class).get().run();
	}

	@Override
	public void run() {
		service.say();
	}
}

interface Service {
	void say();
}

class DefaultService implements Service {
	public void say() {
		System.err.println("Hi");
	}
}

@Named
class MyModule extends AbstractModule {
	public MyModule() {
		System.err.println("MyModule");
	}

	@Override
	protected void configure() {
		bind(Service.class).to(DefaultService.class);
	}
}