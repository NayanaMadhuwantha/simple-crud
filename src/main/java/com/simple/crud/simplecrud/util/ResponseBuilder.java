package com.simple.crud.simplecrud.util;

public class ResponseBuilder<T> {
    private final Response<T> response = new Response<>();

    public ResponseBuilder<T> message(String message) {
        response.setMessage(message);
        return this;
    }

    private ResponseBuilder<T> success(Boolean status) {
        response.setSuccess(status);
        return this;
    }

    public ResponseBuilder<T> success() {
        return new ResponseBuilder<T>().message("Succeed").success(true);
    }

    public ResponseBuilder<T> fail() {
        return new ResponseBuilder<T>().message("Failed").success(false);
    }

    public ResponseBuilder<T> error(ResponseError error) {
        response.setError(error);
        response.setMessage("Failed");
        response.setSuccess(false);
        return this;
    }

    public ResponseBuilder<T> addData(final T body) {
        response.setData(body);
        response.setMessage("Succeed");
        response.setSuccess(true);
        return this;
    }

    public Response<T> build() {
        return response;
    }
}
