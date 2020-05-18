package com.viettel.imdb.util.compressor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuong on 14/07/2017.
 */
public enum CompressorType {
    NO_COMPRESSOR((byte)0),
    LZ4((byte)1),
    //LZW((byte)2),
    ;

    private final byte value;

    CompressorType(int compressorType){
        this.value = (byte) compressorType;
    }

    public byte getValue(){
        return value;
    }

    public static CompressorType get(final int value){
        try {
            CompressorType compressorType = map.get((byte)value);
            return compressorType;
        } catch (Exception e){
            return CompressorType.NO_COMPRESSOR;
        }
    }

    private static List<CompressorType> map;

    static {
        map = new ArrayList<>();
        for (CompressorType compressorType: CompressorType.values()){
            map.add(compressorType.value, compressorType);
        }
    }
}
