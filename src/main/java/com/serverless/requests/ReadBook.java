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

    private final String TABLE_BOOK = "Book";
    private static LambdaLogger LOGGER;

    @Override
    public ApiGatewayResponse handleRequest(Book input, Context context) {

        LOGGER = context.getLogger();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDb = new DynamoDB(client);

        if(input != null && input.getId() != null){

            Table table = dynamoDb.getTable(TABLE_BOOK);
            Item item = table.getItem("id", input.getId());
            String json = item.toJSONPretty();
            return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(json).build();
        }

        return ApiGatewayResponse.builder()
                .setStatusCode(404)
                .setObjectBody(new Response("Book not found", Collections.singletonMap("Book", input)))
                .setHeaders(Collections.singletonMap("X-Powered-By", "UOL"))
                .build();
    }
}