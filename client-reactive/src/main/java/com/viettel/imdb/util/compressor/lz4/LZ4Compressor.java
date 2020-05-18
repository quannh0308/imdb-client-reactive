package com.viettel.imdb.util.compressor.lz4;


import com.viettel.imdb.util.compressor.Compressor;

/**
 * Created by cuong on 14/07/2017.
 */
public abstract class LZ4Compressor implements Compressor {
    private static LZ4Compressor nativeCompressor;
    static {
        nativeCompressor = new LZ4NativeCompressor();
    }
    public static LZ4Compressor newLZ4Compressor(){
        return nativeCompressor;
    }

    public int getCompressBound(int srcLength){
        return srcLength + srcLength / 255 + 16;
    }
}
