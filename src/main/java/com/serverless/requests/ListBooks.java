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
import com.serverless.model.Book;

import java.util.List;

public class ListBooks implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static LambdaLogger LOGGER;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        LOGGER = context.getLogger();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);

        LOGGER.log(String.format("====================== received request SaveBook:\n %s\n", request));

        List<Book> Books = dynamoDBMapper.scan(Book.class, scanExpression);
        Books.size();

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        if (!Books.isEmpty()) {

            try {
                ObjectMapper jsonMapper = new ObjectMapper();
                response.setStatusCode(200);
                response.setBody(jsonMapper.writeValueAsString(Books));
            }
            catch (JsonProcessingException e){
                e.printStackTrace();
                response.setStatusCode(500);
                response.setBody(e.getMessage());
            }
        }
        else{
            response.setStatusCode(200);
            response.setBody("Nenhum livro");
        }
        return response;
    }
}