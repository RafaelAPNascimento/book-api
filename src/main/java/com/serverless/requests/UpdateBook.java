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

public class UpdateBook implements RequestHandler<Book, String> {

    private static LambdaLogger LOGGER;
    private final String TABLE_BOOK = "Book";

    @Override
    public String handleRequest(Book input, Context context) {

        context.getLogger().log(String.format("====================== received request UpdateBook:\n %s\n", input));

        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

        UpdateItemRequest request = new UpdateItemRequest();
        request.setTableName(TABLE_BOOK);
        request.setReturnValues(ReturnValue.UPDATED_OLD);

        Map<String, AttributeValue> keysMap = new HashMap<>();
        keysMap.put("id", new AttributeValue(input.getId().toString()));
        request.setKey(keysMap);

        Map<String, AttributeValueUpdate> map = new HashMap<>();
        map.put("name", new AttributeValueUpdate(new AttributeValue(input.getName()),"PUT"));
        map.put("description", new AttributeValueUpdate(new AttributeValue(input.getDescription()),"PUT"));
        map.put("value", new AttributeValueUpdate(new AttributeValue(input.getValue().toString()),"PUT"));
        map.put("author", new AttributeValueUpdate(new AttributeValue(input.getAuthor()),"PUT"));
        request.setAttributeUpdates(map);

        try{
            UpdateItemResult result = ddb.updateItem(request);

            if (result.getAttributes() != null)
                return result.toString();
        }
        catch (AmazonServiceException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }
}