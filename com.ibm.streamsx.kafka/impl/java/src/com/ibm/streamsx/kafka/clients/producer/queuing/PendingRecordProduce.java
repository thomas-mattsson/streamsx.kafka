/**
 * 
 */
package com.ibm.streamsx.kafka.clients.producer.queuing;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.ibm.streams.operator.Tuple;

/**
 * This class represents a produce attempt for a producer record. It is associated with one ProducerRecord,
 * the tuple that is associated with the ProducerRecord, and with a single callback.
 * 
 * @author The IBM Kafka toolkit team
 */
public class PendingRecordProduce implements Callback {
    // TODO: When multiple operators are fused - do they share this static variable?
    private static AtomicLong nextProducerRecordSeqNumber = new AtomicLong();
    
    private final int initialProducerGeneration;
    private final ProducerRecord<?, ?> producerRecord;
    private final long producerRecordSeqNumber;
    private final Tuple tuple;
    private RecordProducedHandler producedHandler;
    private RecordProduceExceptionHandler exceptionHandler;

    /**
     * 
     */
    public PendingRecordProduce (ProducerRecord<?, ?> producerRecord, int producerGeneration, Tuple tuple) {
        this.initialProducerGeneration = producerGeneration;
        this.producerRecord = producerRecord;
        this.producerRecordSeqNumber = nextProducerRecordSeqNumber.incrementAndGet();
        this.tuple = tuple;
        // TODO Auto-generated constructor stub
    }


    /**
     * @return the producerRecordSeqNumber
     */
    public long getProducerRecordSeqNumber() {
        return producerRecordSeqNumber;
    }


    /**
     * @param producedHandler the producedHandler to set
     */
    public void setProducedHandler(RecordProducedHandler producedHandler) {
        this.producedHandler = producedHandler;
    }


    /**
     * @param exceptionHandler the exceptionHandler to set
     */
    public void setExceptionHandler(RecordProduceExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }


    @Override
    public void onCompletion (RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            producedHandler.onRecordProduced (producerRecordSeqNumber, producerRecord, metadata);
            return;
        }
        exceptionHandler.onRecordProduceException (producerRecordSeqNumber, exception);
    }


    /**
     * get the producer record
     * @return the producer record
     */
    public ProducerRecord<?, ?> getRecord() {
        return producerRecord;
    }

}
