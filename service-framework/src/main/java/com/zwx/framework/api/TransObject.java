package com.zwx.framework.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface TransObject extends Serializable, Cloneable {

	TransObject clone() throws CloneNotSupportedException;
}
