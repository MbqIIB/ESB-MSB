// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPSWPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPSWPackager extends DSPPackager
{

    public DSPSWPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(5, "Description for field 3"), new DSPPackedField(2, 0, "Description for field 4"), new DSPCharField(500, "Description for field 5")
        });
        addBody("SW".toUpperCase(), fld);
    }
}
