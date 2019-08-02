package com.serverless.requests;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.model.Book;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UpdateBook implements RequestHandler<Book, Book> {

    private static LambdaLogger LOGGER;
    private final String TABLE_BOOK = "Book";

    @Override
    public Book handleRequest(Book input, Context context) {

        context.getLogger().log(String.format("====================== received request UpdateBook:\n %s\n", input));
        input.setInfo("Update Request Info");
        return input;
    }
}