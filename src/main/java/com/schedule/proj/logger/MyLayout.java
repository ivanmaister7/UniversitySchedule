package com.schedule.proj.logger;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.nio.charset.Charset;

@Plugin(name = "MyLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)

public class MyLayout extends AbstractStringLayout {
    protected MyLayout(Charset charset) {
        super(charset);
    }

    @PluginFactory
    public static MyLayout createCustomLayout(@PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
        return new MyLayout(charset);
    }

    @Override
    public String toSerializable(LogEvent event) {
        return " MyLayout : !" + event.getLevel() + "! " + event.getThreadName()+", " +
                "message: "+event.getMessage().getFormattedMessage() +
                " (" + (event.getMarker() == null? "": event.getMarker()) +
                ") " + event.getContextData().toString();
    }
}