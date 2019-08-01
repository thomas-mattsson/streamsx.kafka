/**
 * 
 */
package com.ibm.streamsx.kafka.clients.producer.queuing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import com.ibm.streams.operator.Tuple;

/**
 * This class represents a pending tuple which is being processed.
 * It is associated with the Tuple from the input port, and one or more {@link PendingRecordProduce} instances (one for each topic).
 * 
 * @author The IBM Kafka toolkit team
 */
public class PendingTupleProcess implements RecordProducedHandler, RecordProduceExceptionHandler {

    public static enum Status {
        IN_PROGRESS,
        DONE,
        FAILED_RETRIABLE,
        FAILED_FINALLY
    }
    private static final Logger trace = Logger.getLogger (PendingTupleProcess.class);
    private final Tuple tuple;
    private Status status = Status.IN_PROGRESS;
    private final Map <Long, PendingRecordProduce> producerRecordAttempts;
    private final int initialNumRecords;
    private int nProducedRecords = 0;

    /**
     * @return the tuple
     */
    public Tuple getTuple() {
        return tuple;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 
     */
    public PendingTupleProcess (final Tuple tuple, List<ProducerRecord<?, ?>> records, int producerGeneration) {
        this.tuple = tuple;
        this.initialNumRecords = records.size();
        producerRecordAttempts = new HashMap<> (records.size());
        for (ProducerRecord<?, ?> r: records) {
            PendingRecordProduce p = new PendingRecordProduce (r, producerGeneration, tuple);
            p.setProducedHandler (this);
            p.setExceptionHandler (this);
            producerRecordAttempts.put (p.getProducerRecordSeqNumber(), p);
        }
        // TODO Auto-generated constructor stub
    }

    public PendingTupleProcess (final Tuple tuple, ProducerRecord<?, ?> record, int producerGeneration) {
        this.tuple = tuple;
        this.initialNumRecords = 1;
        producerRecordAttempts = new HashMap<> (1);
        PendingRecordProduce p = new PendingRecordProduce (record, producerGeneration, tuple);
        p.setProducedHandler (this);
        p.setExceptionHandler (this);
        producerRecordAttempts.put (p.getProducerRecordSeqNumber(), p);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see com.ibm.streamsx.kafka.clients.producer.queuing.RecordProduceExceptionHandler#onRecordProduceException(java.lang.Exception)
     */
    @Override
    public synchronized void onRecordProduceException (long seqNo, Exception e) {
        // TODO: handle exceptions that cannot retried (missing topic, authentication errors, ...)
        final String topic = producerRecordAttempts.get (seqNo).getRecord().topic();
        trace.error ("Producer record cannot be produced for topic '" + topic + "': " + e);
        // TODO Auto-generated method stub
        if (status == Status.IN_PROGRESS) {
            // status can also be FAILED_FINALLY depending on the exception type
            status = Status.FAILED_RETRIABLE;
        }
        // invoke 'Done'-callback when status is final
    }

    /**
     * @see com.ibm.streamsx.kafka.clients.producer.queuing.RecordProducedHandler#onRecordProduced(org.apache.kafka.clients.producer.ProducerRecord, org.apache.kafka.clients.producer.RecordMetadata)
     */
    @Override
    public synchronized void onRecordProduced (long seqNo, ProducerRecord<?, ?> record, RecordMetadata metadata) {
        
        ++nProducedRecords;
        // do not send this record again for this tuple:
        producerRecordAttempts.remove (seqNo);
        if (nProducedRecords == initialNumRecords && status == Status.IN_PROGRESS) {
            this.status = Status.DONE;
            // invoke 'Done'-callback
        }
        
        // TODO Auto-generated method stub
        
    }

}
