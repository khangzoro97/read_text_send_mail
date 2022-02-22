package com.javabydeveloper.model;

import java.util.List;
import java.util.Map;

public class Mail {

    private List<Object> attachments;
    private Map<String, Object> props;

    public Mail() {}




	public List<Object> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}

	public Map<String, Object> getProps() {
		return props;
	}

	public void setProps(Map<String, Object> props) {
		this.props = props;
	}

}
