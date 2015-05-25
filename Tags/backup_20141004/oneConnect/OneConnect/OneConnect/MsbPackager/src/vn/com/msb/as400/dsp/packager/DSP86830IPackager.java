// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP86830IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP86830IPackager extends DSPPackager
{

    public DSP86830IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(10, 0, "Description for field 1"), new DSPPackedField(4, 0, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPPackedField(4, 4, "Description for field 4"), new DSPPackedField(4, 2, "Description for field 5"), new DSPCharField(1, "Description for field 6"), new DSPCharField(40, "Description for field 7"), new DSPCharField(40, "Description for field 8"), new DSPCharField(1, "Description for field 9")
        });
        addBody("86830I".toUpperCase(), fld);
    }
}
