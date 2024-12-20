package io.devandre.synthdrive.shared.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionHandler {

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();

        // Log the exception
        if (exception instanceof ResourceNotFoundException) {
            // Log the exception
            return CompletableFuture.completedFuture(createException(exception, HttpStatus.NOT_FOUND));
        } else if(exception instanceof ValidationException) {
            // Log the exception
            return CompletableFuture.completedFuture(createException(exception, HttpStatus.BAD_REQUEST));
        }

        return CompletableFuture.completedFuture(createException(exception, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private DataFetcherExceptionHandlerResult createException(Throwable exception, HttpStatus status) {
        GraphQLError error = GraphqlErrorBuilder.newError()
                .message(exception.getMessage())
                .extensions(Collections.singletonMap("status", status.value()))
                .build();

        return DataFetcherExceptionHandlerResult.newResult()
                .error(error)
                .build();
    }
}
