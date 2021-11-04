package com.schedule.proj.logger;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;

@Plugin(name="MyAppender", category= Core.CATEGORY_NAME, elementType= Appender.ELEMENT_TYPE)
public class MyAppender extends AbstractAppender {

    protected MyAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions, Property[] properties) {
        super(name, filter, layout, ignoreExceptions, properties);
    }
    @PluginFactory
    public static MyAppender createAppender(@PluginAttribute("name") String name,
                                                @PluginElement("Filter") Filter filter,
                                                @PluginElement("Layout") Layout<? extends Serializable> layout) {
        return new MyAppender(name, filter,layout,false,null);
    }

    @Override
    public void append(LogEvent event) {
        System.out.println("MyAppender: "+ getLayout().toSerializable(event));
    }
}