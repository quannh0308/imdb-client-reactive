package com.viettel.imdb.util.compressor;


import com.viettel.imdb.util.compressor.lz4.LZ4Compressor;

/**
 * Created by hai on 7/12/17.
 */
public class CompressorFactory {
    public static Compressor getCompressor(CompressorType compressorType){
        switch (compressorType){
            case LZ4:
                return LZ4Compressor.newLZ4Compressor();
            default:
                return null;
        }


    }
}
