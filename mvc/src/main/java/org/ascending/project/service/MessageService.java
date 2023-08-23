package org.ascending.project.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private AmazonSQS amazonSQS;

    @Value("${queue.name}")
    private String queueName;

    public void sendMessage(String messageBody, int delaySeconds){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(getQueueUrl(queueName))
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySeconds);
        amazonSQS.sendMessage(send_msg_request);
    }

    private String getQueueUrl(String queueName){
        GetQueueUrlResult getQueueUrlResult = amazonSQS.getQueueUrl(queueName);
        return getQueueUrlResult.getQueueUrl();
    }
}




