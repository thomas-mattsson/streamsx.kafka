package com.ibm.streamsx.kafka.test.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ibm.streams.operator.OutputTuple;
import com.ibm.streams.operator.StreamSchema;
import com.ibm.streams.operator.Tuple;
import com.ibm.streams.operator.Type;
import com.ibm.streamsx.topology.TStream;
import com.ibm.streamsx.topology.function.BiFunction;
import com.ibm.streamsx.topology.spl.SPLStream;
import com.ibm.streamsx.topology.spl.SPLStreams;

public class KafkaSPLStreamsUtils {

    public static final StreamSchema STRING_NOKEY_SCHEMA = Type.Factory.getStreamSchema("tuple<rstring message>");
    public static final StreamSchema STRING_SCHEMA = Type.Factory.getStreamSchema("tuple<rstring key, rstring message>");
    public static final StreamSchema INT_SCHEMA = Type.Factory.getStreamSchema("tuple<int32 key, int32 message>");
    public static final StreamSchema LONG_SCHEMA = Type.Factory.getStreamSchema("tuple<int64 key, int64 message>");
    public static final StreamSchema BLOB_SCHEMA = Type.Factory.getStreamSchema("tuple<blob key, blob message>");
    public static final StreamSchema DOUBLE_SCHEMA = Type.Factory.getStreamSchema("tuple<float64 key, float64 message>");
    public static final StreamSchema FLOAT_SCHEMA = Type.Factory.getStreamSchema("tuple<float32 key, float32 message>");

    public static SPLStream convertStreamToKafkaTuple(TStream<String> stream) {
        return convertStreamToKafkaTuple(stream, true);
    }

    public static SPLStream convertStreamToKafkaTuple(TStream<String> stream, boolean includeKey) {
        if(includeKey)
            return SPLStreams.convertStream(stream, KafkaSPLStreamsUtils.getStringBiFunction(), KafkaSPLStreamsUtils.STRING_SCHEMA);
        else
            return SPLStreams.convertStream(stream, KafkaSPLStreamsUtils.getStringNoKeyBiFunction(), KafkaSPLStreamsUtils.STRING_NOKEY_SCHEMA);
    }

    public static BiFunction<String, OutputTuple, OutputTuple> getStringBiFunction() {
        return new BiFunction<String, OutputTuple, OutputTuple>() {
            private static final long serialVersionUID = 1L;
            private int counter = 0;

            @Override
            public OutputTuple apply(String message, OutputTuple outTuple) {
                outTuple.setString("key", "key_" + counter++);
                outTuple.setString("message", message);

                return outTuple;
            }
        };
    }

    public static BiFunction<String, OutputTuple, OutputTuple> getStringNoKeyBiFunction() {
        return new BiFunction<String, OutputTuple, OutputTuple>() {
            private static final long serialVersionUID = 1L;

            @Override
            public OutputTuple apply(String message, OutputTuple outTuple) {
                outTuple.setString("message", message);

                return outTuple;
            }
        };
    }

    public static SPLStream union(List<SPLStream> streams, StreamSchema schema) {
        if(streams.size() == 0)
            throw new IllegalArgumentException("At least one stream must be provided.");

        if(streams.size() == 1) {
            return streams.get(0);
        } else {
            SPLStream stream1 = streams.get(0);
            Set<TStream<Tuple>> streamSet = new HashSet<TStream<Tuple>>(streams);
            streamSet.remove(stream1);

            return SPLStreams.convertStream(stream1.union(streamSet), getTupleStreamConvert(), schema);
        }
    }

    private static BiFunction<Tuple, OutputTuple, OutputTuple> getTupleStreamConvert() {
        return new BiFunction<Tuple, OutputTuple, OutputTuple>() {
            private static final long serialVersionUID = 1L;

            @Override
            public OutputTuple apply(Tuple v1, OutputTuple v2) {
                v2.assign(v1);
                return v2;
            }
        };
    }


    public static String[] duplicateArrayEntries(String[] inputArr, int numDuplicates) {
        List<String> list = new ArrayList<String>();
        for(String d : inputArr) {
            for(int i = 0; i < numDuplicates; i++) {
                list.add(d);
            }
        }

        return list.toArray(new String[0]);
    }
}
