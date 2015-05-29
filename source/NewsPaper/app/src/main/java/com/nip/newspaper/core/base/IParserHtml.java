package com.nip.newspaper.core.base;

import android.provider.DocumentsContract;

/**
 * Created by nguyenminhthang on 5/28/15.
 */
public interface IParserHtml {
    int parserHtml(DocumentsContract.Document doc);
}
