package com.tbs.transfer.service.kafka;

import com.tbs.transfer.service.transfer.DebitCompleted;
import com.tbs.transfer.service.transfer.FraudAnalysisRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferEventConsumer {
    private final TransferEventProducer transferEventProducer;

    @KafkaListener(topics = "debit.completed")
    public void consume(DebitCompleted event){
        FraudAnalysisRequest request = new FraudAnalysisRequest(event.transferId(), event.amount());
        this.transferEventProducer.publish(request);
    }
}
