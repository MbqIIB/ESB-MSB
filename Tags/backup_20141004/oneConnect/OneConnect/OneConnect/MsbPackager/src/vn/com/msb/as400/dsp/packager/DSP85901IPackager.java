// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP85901IPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP85901IPackager extends DSPPackager
{

    public DSP85901IPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(10, "Description for field 1"), new DSPPackedField(3, 0, "Description for field 2"), new DSPCharField(500, "Description for field 3"), new DSPCharField(130, "Description for field 4")
        });
        addBody("85901I".toUpperCase(), fld);
    }
}
