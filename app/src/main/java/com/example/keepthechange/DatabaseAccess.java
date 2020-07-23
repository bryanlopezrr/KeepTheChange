package com.example.keepthechange;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class DatabaseAccess {

    private String TAG = "DynamoDB_Demo";
    private static volatile DatabaseAccess instance;

    private final String COGNITO_IDENTITY_POOL_ID = "us-east-1_WhZBlntFO";
    private final Regions COGNITO_IDENTITY_POOL_REGION = Regions.US_EAST_1;
    private final String DYNAMODB_TABLE = "DynamoTEST";

    private Context context;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonDynamoDB dbClient;
    private Table dbTable;


    private DatabaseAccess(Context context){
        credentialsProvider = new CognitoCachingCredentialsProvider
                (context, COGNITO_IDENTITY_POOL_ID, COGNITO_IDENTITY_POOL_REGION);

        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        dbClient.setRegion(Region.getRegion(Regions.US_EAST_1));

        dbTable = Table.loadTable(dbClient, DYNAMODB_TABLE);

    }



    public static synchronized DatabaseAccess getInstance(Context context){

        if(instance == null){
            instance = new DatabaseAccess(context);
        }

        return instance;
    }


}
