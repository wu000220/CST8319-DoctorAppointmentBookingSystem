/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

/**
 *
 * @author fwu
 */
public class ValidationException extends Exception {

    // Constructs a new ValidationException with a default detail message.
    public ValidationException() {
        super("Data not in valid format");
    }

    // Constructs a new ValidationException with the specified detail message.
    public ValidationException(String message) {
        super(message);
    }

    // Constructs a new ValidationException with the specified detail message and cause.
    public ValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    // Constructs a new ValidationException with the specified cause.
    public ValidationException(Throwable throwable) {
        super(throwable);
    }
}
