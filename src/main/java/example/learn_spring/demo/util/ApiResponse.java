package example.learn_spring.demo.util;

/**
 * Structured api response
 *
 * @param <T>
 */
public class ApiResponse<T> { // Use generics for data flexibility
    private int statusCode;
    private String message;
    private T data; // Could be an object, list, etc.
    private String error;
    private String token;

    public ApiResponse() {
    }

    public ApiResponse(int statusCode, String message, String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
    }

    public ApiResponse(int statusCode, String message, T data, String token) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public ApiResponse(int statusCode, String message, T data, String token, String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.token = token;
        this.error = error;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

}