package net.javademo.project.recruitment.server;

public class ServerException extends Exception {
    private ServerErrorCode errorCode;

    public ServerException(ServerErrorCode errorCode, Throwable cause) {
        super(errorCode.getServerErrorCode(), cause);
        this.errorCode = errorCode;
    }

    public ServerException(ServerErrorCode errorCode) {
        super(errorCode.getServerErrorCode());
        this.errorCode = errorCode;
    }

    public ServerException(ServerErrorCode errorCode, String param) {
        super(String.format(errorCode.getServerErrorCode(), param));
        this.errorCode = errorCode;
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public ServerErrorCode getServerErrorCode() {
        return errorCode;
    }
}
