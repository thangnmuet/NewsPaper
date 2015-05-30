package com.nip.newspaper.core.paser;

/**
 * Created by Esoft on 5/29/2015.
 */
public interface IParserListener<T> {
    void process(T value);
}
