package com.tbs.transfer.service.kafka;

import com.tbs.transfer.service.repository.TransferRepository;
import com.tbs.transfer.service.transfer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferEventConsumer {
    private final TransferEventProducer transferEventProducer;
    private final TransferRepository transferRepository;

    @KafkaListener(topics = "debit.completed")
    public void consume(DebitCompleted event){
        FraudAnalysisRequest request = new FraudAnalysisRequest(event.transferId(), event.amount());
        this.transferEventProducer.publish(request);
    }

    @KafkaListener(topics = "fraud.analysis.result")
    public void consume(FraudAnalysisResult event){
        System.out.println("FRAUD ANALYSIS RESULT RECEBIDO: " + event);

        if(event.decision() != FraudDecision.APPROVED){
            return ;
        }

        Transfer transfer = this.transferRepository.findById(event.transferId())
                .orElseThrow(() -> new IllegalStateException("Transfer not found: " + event.transferId()));

        CreditRequested creditRequested = new CreditRequested(
                transfer.getId(),
                transfer.getReceiverAgency(),
                transfer.getReceiverAccountNumber(),
                transfer.getAmount()
        );

        this.transferEventProducer.publish(creditRequested);
        System.out.println("CREDIT REQUESTED PUBLICADO: " + creditRequested);
    }
}
