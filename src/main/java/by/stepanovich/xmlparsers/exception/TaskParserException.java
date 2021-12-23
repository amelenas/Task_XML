package by.stepanovich.xmlparsers.exception;

public class TaskParserException extends Exception{
    public TaskParserException() {
    }

    public TaskParserException(String message) {
        super(message);
    }

    public TaskParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskParserException(Throwable cause) {
        super(cause);
    }
}
