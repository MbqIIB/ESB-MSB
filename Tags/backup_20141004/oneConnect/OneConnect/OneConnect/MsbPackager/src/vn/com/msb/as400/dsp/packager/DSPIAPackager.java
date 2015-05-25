// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPIAPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPIAPackager extends DSPPackager
{

    public DSPIAPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(10, "Description for field 3"), new DSPPackedField(5, 0, "Description for field 4"), new DSPNumericField(6, "Description for field 5"), new DSPCharField(30, "Description for field 6"), new DSPCharField(2, "Description for field 7"), new DSPCharField(10, "Description for field 8"), new DSPCharField(10, "Description for field 9"), new DSPCharField(850, "Description for field 10")
        });
        addBody("IA".toUpperCase(), fld);
    }
}
