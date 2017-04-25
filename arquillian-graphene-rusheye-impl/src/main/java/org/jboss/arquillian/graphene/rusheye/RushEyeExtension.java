package org.jboss.arquillian.graphene.rusheye;

import org.jboss.arquillian.core.spi.LoadableExtension;
import org.jboss.arquillian.graphene.rusheye.configuration.RushEyeConfigurator;
import org.jboss.arquillian.graphene.rusheye.enricher.RushEyeEnricher;
import org.jboss.arquillian.test.spi.TestEnricher;


public class RushEyeExtension implements LoadableExtension{

    public void register(ExtensionBuilder builder) {
    	System.out.println("Registered");
    	builder.observer(RushEyeConfigurator.class);
    	builder.service(TestEnricher.class, RushEyeEnricher.class);
    }
}
