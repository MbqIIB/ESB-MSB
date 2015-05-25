// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSP26005RPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSP26005RPackager extends DSPPackager
{

    public DSP26005RPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPCharField(10, "Description for field 1"), new DSPCharField(40, "Description for field 2"), new DSPCharField(32, "Description for field 3"), new DSPCharField(20, "Description for field 4"), new DSPCharField(32, "Description for field 5"), new DSPCharField(20, "Description for field 6"), new DSPCharField(32, "Description for field 7"), new DSPCharField(20, "Description for field 8"), new DSPCharField(32, "Description for field 9"), new DSPCharField(20, "Description for field 10"), 
            new DSPCharField(32, "Description for field 11"), new DSPCharField(20, "Description for field 12"), new DSPCharField(32, "Description for field 13"), new DSPCharField(20, "Description for field 14")
        });
        addBody("26005R".toUpperCase(), fld);
    }
}
