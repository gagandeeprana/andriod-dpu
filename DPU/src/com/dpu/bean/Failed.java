package com.dpu.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class Failed {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@JsonProperty("error-code")
	private long code;

	@JsonProperty("error-message")
	private String message;

	@JsonProperty("error-auxiliary-message")
	private String auxiliary;

	@JsonProperty("params")
	private Object parms;

	private Object resultList;

	public Failed() {
	}

	public Failed(int code, String message, String auxiliary) {
		this.code = code;
		this.message = message;
		this.auxiliary = auxiliary;
	}

	public void setFailed(int code, String message, String auxiliary) {
		this.code = code;
		this.message = message;
		this.auxiliary = auxiliary;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = Integer.valueOf(String.valueOf(code));
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuxiliary() {
		return auxiliary;
	}

	public Object getResultList() {
		return resultList;
	}

	public void setResultList(Object resultList) {
		this.resultList = resultList;
	}

	public void setAuxiliary(String auxiliary) {
		this.auxiliary = auxiliary;
	}

	public Object getParms() {
		return parms;
	}

	public void setParms(Object parms) {
		this.parms = parms;
	}

}
