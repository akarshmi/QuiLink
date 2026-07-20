package dev.akarshmi.quilink.shortener.util;

public class SnowflakeIdGenerator {
    /*
    What a SnowFlakeId Contain->
     * Bits allocation:
     * 1 bit for positive
     * 41 bits - Timestamp
     * 10 bits - Node ID
     * 12 bits - Sequence
     */
    private final long nodeId;

    private static final long EPOCH =  1704067200000L; // Jan 1, 2024 UTC
    private static final long NODE_ID_BITS = 10L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long NODE_ID_SHIFT = SEQUENCE_BITS;
    private static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public SnowflakeIdGenerator(long nodeId) {
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException("Node ID out of range");
        }
        this.nodeId = nodeId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards");
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                while (timestamp <= lastTimestamp) {
                    timestamp = System.currentTimeMillis();
                }
            }
        }else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << (NODE_ID_BITS + SEQUENCE_BITS))
                | (nodeId << NODE_ID_SHIFT)
                | sequence;
    }


}
