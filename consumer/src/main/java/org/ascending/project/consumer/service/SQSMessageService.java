package org.ascending.project.consumer.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SQSMessageService {

//    private final AmazonSQS sqsClient;
//    private final String queueName;
//
//    @Autowired
//    public SQSMessageService(AmazonSQS sqsClient, @Value("${queue.name}") String queueName) {
//        this.sqsClient = sqsClient;
//        this.queueName = queueName;
//    }
//
//    public void receiveMessage(){
//        System.out.println("Receiving messages from MyQueue.\n");
//        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(getQueueUrl(queueName));
//        final List<Message> msg = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
//
//        for (final Message message : msg) {
//            System.out.println("Message");
//            System.out.println("  MessageId:     " + message.getMessageId());
//            System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
//            System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
//            System.out.println("  Body:          " + message.getBody());
//            Map<String,String> messageAttributes = message.getAttributes();
//            for (final Map.Entry<String, String> entry : messageAttributes.entrySet()) {
//                System.out.println("Attribute");
//                System.out.println("  Name:  " + entry.getKey());
//                System.out.println("  Value: " + entry.getValue());
//            }
//            sqsClient.deleteMessage(getQueueUrl(queueName), message.getReceiptHandle());
//        }
//        System.out.println();
//    }
//
//
//    public String getQueueUrl(String queueName){
//        String queueUrl = sqsClient.getQueueUrl(queueName).getQueueUrl();
//        return queueUrl;
//    }

}
