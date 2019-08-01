package com.ibm.streamsx.kafka.clients.producer.queuing;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.ibm.streams.operator.OperatorContext;
import com.ibm.streams.operator.Tuple;
import com.ibm.streamsx.kafka.clients.producer.KafkaProducerClient;
import com.ibm.streamsx.kafka.properties.KafkaOperatorProperties;

/**
 * This producer client queues incoming tuples, i.e. calls to {@link #send(org.apache.kafka.clients.producer.ProducerRecord, Tuple)},
 * until they are acknowledged via their associated callback. The {@link #processRecord(ProducerRecord, Tuple)} and {@link #processRecords(List, Tuple)} 
 * calls may block when the incoming queue exceeds a limit or when memory gets exhausted. On Exception received in the callback,
 * the queued producer record is retried with a new generation of a KafkaProducer instance.
 * 
 * This producer client does not guarantee the sequence of producer records.
 * 
 * TODO: how many producer generations per record? Then discard invoking the discard callback with the associated tuple - Note the tuple can be submitted for multiple topics
 * TODO: Make sure to invoke the discard callback only once per tuple? 
 * 
 * @author IBM Kafka toolkit team
 * @since toolkit version 2.2
 */
public class QueueingProducerClient extends KafkaProducerClient {

    private int producerGeneration = 0;

    /**
     * @param operatorContext
     * @param keyClass
     * @param valueClass
     * @param guaranteeRecordOrder
     * @param kafkaProperties
     * @throws Exception
     */
    public <K, V> QueueingProducerClient (OperatorContext operatorContext, Class<K> keyClass, Class<V> valueClass,
            boolean guaranteeRecordOrder, KafkaOperatorProperties kafkaProperties) throws Exception {
        super(operatorContext, keyClass, valueClass, guaranteeRecordOrder, kafkaProperties);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see com.ibm.streamsx.kafka.clients.producer.KafkaProducerClient#processRecord(org.apache.kafka.clients.producer.ProducerRecord, com.ibm.streams.operator.Tuple)
     */
    @Override
    public void processRecord (ProducerRecord<?, ?> producerRecord, Tuple associatedTuple) throws Exception {
        // association record to tuple is 1-to-1
//        super.processRecord (producerRecord, associatedTuple);
    }

    /**
     * @see com.ibm.streamsx.kafka.clients.producer.KafkaProducerClient#processRecords(java.util.List, com.ibm.streams.operator.Tuple)
     */
    @Override
    public void processRecords (List<ProducerRecord<?, ?>> records, Tuple associatedTuple) throws Exception {
        // association record to tuple is N-to-1
        // a tuple is done, when all its producer records are done
        
//        super.processRecords (records, associatedTuple);
    }

}
