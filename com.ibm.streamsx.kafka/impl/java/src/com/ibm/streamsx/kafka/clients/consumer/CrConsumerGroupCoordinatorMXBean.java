package com.ibm.streamsx.kafka.clients.consumer;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * This interface represents the MBean of a Kafka consumer group within a consistent region.
 * A Kafka consumer group can belong only to one consistent region. 
 * 
 * All functions are declared throwing IOException to force the client to handle communication problems.
 * 
 * @see <a href="http://www.oracle.com/technetwork/java/javase/tech/best-practices-jsp-136021.html#mozTocId805713">JMX Best Practices by Oracle</a>.
 * @author IBM Kafka toolkit maintainers
 */
public interface CrConsumerGroupCoordinatorMXBean {

    /**
     * JMX Notification type offset map merge is completed.
     */
    public final static String MERGE_COMPLETE_NTF_TYPE = "OFFSET.MAP.MERGE.COMPLETE";

    /**
     * Returns the index of the consistent region.
     * @return the consumer group index
     * @throws IOException
     */
    public int getConsistentRegionIndex() throws IOException;

    /**
     * Gets the Kafka Group-ID, i.e. the value of the group.id Kafka property
     * @return the group-ID
     * @throws IOException
     */
    public String getGroupId() throws IOException;

    /**
     * registers a consumer operator 
     * @param id a unique identifier of the operator, for example the operators full name
     * @see #deregisterConsumerOperator(String)
     * @throws IOException
     */
    public void registerConsumerOperator (String id) throws IOException;

    /**
     * deregisters a consumer operator 
     * @param id a unique identifier of the operator, for example the operators full name
     * @see #registerConsumerOperator(String)
     * @throws IOException
     */
    public void deregisterConsumerOperator (String id) throws IOException;

    /**
     * returns the number of registered consumers, which should be the size of the consumer group.
     * @return the number of consumers
     * @throws IOException
     */
    public int getNumRegisteredConsumers() throws IOException;

    /**
     * Get the names of the registered Consumer operators
     * @return the name of the registered operators. If none is registered, an empty Set is returned.
     * @see #registerConsumerOperator(String)
     * @see #deregisterConsumerOperator(String)
     * @throws IOException
     */
    public Set<String> getRegisteredConsumerOperators() throws IOException;

    /**
     * Merges the checkpoint data of a single consumer into the consolidated group checkpoint.
     * @param chkptSequenceId the checkpoint sequence ID.
     * @param resetAttempt the current number of attempts of resetting the CR
     * @param nRequiredDistinctContributions the number of expected distinct contributions for merge completeness
     * @param partialResetOffsetMap the partial set of offsets being partialResetOffsetMap.keySet() typically a subset of allPartitions.
     * @param operatorName The unique name of the operator
     * @throws IOException
     */
    public void mergeConsumerCheckpoint (long chkptSequenceId, int resetAttempt, 
            int nRequiredDistinctContributions, Map <CrConsumerGroupCoordinator.TP, Long> partialResetOffsetMap, String operatorName) throws IOException;

    /**
     * Gets the consolidated offset map that has been created by merging parts via {@link #mergeConsumerCheckpoint(long, int, Set, Map)}.
     * @param chkptSequenceId the checkpoint sequence ID.
     * @param resetAttempt the current number of attempts of resetting the CR
     * @param operatorName The unique name of the operator
     * @return the consolidated map that maps topic partitions to offsets
     * @throws IOException
     */
    public Map<CrConsumerGroupCoordinator.TP, Long> getConsolidatedOffsetMap (long chkptSequenceId, int resetAttempt, String operatorName) throws IOException;

    /**
     * Cleans the merge map for the given checkpoint sequence ID
     * @param chkptSequenceId the checkpoint sequence ID
     * @throws IOException
     */
    public void cleanupMergeMap (long chkptSequenceId) throws IOException;

    /**
     * Sets the rebalance reset pending state atomically to the given value and returns the old value.
     * @param pending the new state
     * @param operatorName The unique name of the operator
     * @return the old state
     * @throws IOException
     */
    public boolean getAndSetRebalanceResetPending (boolean pending, String operatorName) throws IOException;

    /**
     * Sets the rebalance reset pending state atomically to the given value.
     * @param pending the new state
     * @param operatorName The unique name of the operator
     * @throws IOException
     */
    public void setRebalanceResetPending (boolean pending, String operatorName) throws IOException;

    /**
     * returms the current rebalance reset pending state.
     * @return the state
     * @throws IOException
     */
    public boolean isRebalanceResetPending() throws IOException;
}
