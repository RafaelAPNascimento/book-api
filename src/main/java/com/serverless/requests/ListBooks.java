package com.serverless.requests;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ApiGatewayResponse;
import com.serverless.model.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ListBooks implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static LambdaLogger LOGGER;

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> request, Context context) {

        List<Book> books = Arrays.asList(new Book(1L, "book1", 9.99, "description A", "author A"),
                new Book(2L, "book1", 9.99, "description A", "author A"),
                new Book(3L, "book1", 9.99, "description A", "author A"),
                new Book(4L, "book1", 9.99, "description A", "author A"),
                new Book(5L, "book1", 9.99, "description A", "author A"));

        return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(books).build();
    }
}