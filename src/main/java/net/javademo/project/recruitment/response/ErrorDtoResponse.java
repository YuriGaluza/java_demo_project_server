package net.javademo.project.recruitment.response;

import java.util.Objects;

public class ErrorDtoResponse {
    String error;

    public ErrorDtoResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDtoResponse that = (ErrorDtoResponse) o;
        return Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error);
    }
}
