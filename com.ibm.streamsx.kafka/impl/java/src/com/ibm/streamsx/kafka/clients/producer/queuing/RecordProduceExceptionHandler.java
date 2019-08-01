/**
 * 
 */
package com.ibm.streamsx.kafka.clients.producer.queuing;

/**
 * @author The IBM Kafka toolkit team
 *
 */
public interface RecordProduceExceptionHandler {
    /**
     * @param seqNo  the sequence number of the {@link PendingRecordProduce}
     * @param e      the exception
     */
    void onRecordProduceException (long seqNo, Exception e);
}
