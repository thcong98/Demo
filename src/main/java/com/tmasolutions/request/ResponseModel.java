package com.tmasolutions.request;

import java.io.Serializable;

public class ResponseModel implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final Object data;

    private Boolean success = false;
    private String errorMessage = "";

    public Boolean getSuccess() {
        return success;
    }

    public ResponseModel(Boolean success, Object oData) {
        this.data = oData;
        this.success = success;
        this.errorMessage = "";
    }

    public ResponseModel(Boolean success, Object oData, String errorMessage) {
        this.data = oData;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return this.data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
