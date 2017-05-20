package org.arquillian.graphene.rusheye.configuration;

import org.jboss.arquillian.config.descriptor.api.ArquillianDescriptor;
import org.jboss.arquillian.core.api.InstanceProducer;
import org.jboss.arquillian.core.api.annotation.ApplicationScoped;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.core.api.annotation.Observes;

public class RushEyeConfigurationProducer {
	
    @Inject
    @ApplicationScoped
    InstanceProducer<RushEyeConfiguration> configurationProducer;

    protected RushEyeConfiguration create() {
        return new RushEyeConfiguration();
    }

    public void observe(@Observes ArquillianDescriptor descriptorCreated) {
    /*	descriptorCreated.exportAsString()
        Configuration.importTo(this.create()).from(descriptor);
        return configuration;
        configurationProducer.set(configureFromArquillianDescriptor(descriptorCreated));*/
    }
    
}
