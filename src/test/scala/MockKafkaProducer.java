/*
 * Copyright (c) 2016 Koninklijke Philips N.V.
 * All rights are reserved. Reproduction or dissemination
 * in whole or in part is prohibited without the prior written
 * consent of the copyright holder.
 */

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.*;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.*;
import java.util.concurrent.*;

public class MockKafkaProducer<K, V> implements Producer<K, V> {

    public List<ProducerRecord<K, V>> receivedMessages = new ArrayList<>();
    public Exception throwError;
    @Override
    public void initTransactions() {

    }

    @Override
    public void beginTransaction() throws ProducerFencedException {

    }

    @Override
    public void sendOffsetsToTransaction(Map<TopicPartition, OffsetAndMetadata> offsets, String consumerGroupId) throws ProducerFencedException {

    }

    @Override
    public void commitTransaction() throws ProducerFencedException {

    }

    @Override
    public void abortTransaction() throws ProducerFencedException {

    }

    @Override
    public Future<RecordMetadata> send(ProducerRecord<K, V> record) {
       return null;
    }

    @Override
    public Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback) {
        receivedMessages.add(record);
        callback.onCompletion(new RecordMetadata(new TopicPartition("top", 1), 1,1,1,1,1,1), throwError);
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public List<PartitionInfo> partitionsFor(String topic) {
        return null;
    }

    @Override
    public Map<MetricName, ? extends Metric> metrics() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void close(long timeout, TimeUnit unit) {

    }
}
