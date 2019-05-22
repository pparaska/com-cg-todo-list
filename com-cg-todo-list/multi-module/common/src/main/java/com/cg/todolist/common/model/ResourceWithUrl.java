package com.cg.todolist.common.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement	//maps a class or an enum type to an XML element. When a top level class or an enum type is annotated with the 
				//@XmlRootElement annotation, then its value is represented as XML element in an XML document.

public class ResourceWithUrl<T> {
    private String id;
    @JsonUnwrapped //Annotation used to indicate that a property should be serialized "unwrapped"; that is, if it would be serialized as JSON Object, 
    				//its properties are instead included as properties of its containing Object
    private T content;

    private String url;

    public ResourceWithUrl() {
    }

    public ResourceWithUrl(T content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAnyElement	// This annotation serves as a "catch-all" property while unmarshalling xml content into a instance of a JAXB annotated class
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
