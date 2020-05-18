package com.viettel.imdb.util.compressor;


import net.openhft.chronicle.bytes.Bytes;

/**
 * Created by cuong on 14/07/2017.
 */
public interface Compressor {
    /**
     * Compress a src Bytes into dst
     * @param src
     * @param dst
     * @return
     */
    int compress(Bytes src, Bytes dst);

    /**
     * Decompress a src Bytes into dst
     * @param src
     * @param dst
     * @return
     */
    int decompress(Bytes src, Bytes dst, int originalSize);

    int getCompressBound(int srcLength);
}
