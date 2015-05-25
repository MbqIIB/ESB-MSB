// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP15102IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP15102IPackager extends DSPPackager
{

    public DSP15102IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPVietField(20, "Description for field 1"), new DSPPackedField(10, 0, "Description for field 2")
        });
        addBody("15102I".toUpperCase(), fld);
    }
}
