package com.serverless.requests;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.model.Book;

import java.util.Collections;

public class ReadBook implements RequestHandler<Book, ApiGatewayResponse> {

    private static LambdaLogger LOGGER;

    @Override
    public ApiGatewayResponse handleRequest(Book input, Context context) {

        LOGGER = context.getLogger();
        input = new Book(1L, "book1", 9.99, "description A", "author A");
        input.setInfo("Read Request Info. Red book with id: "+input.getId());

        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(input.toString())
                .setHeaders(Collections.singletonMap("X-Powered-By", "FinalException"))
                .build();
    }
}