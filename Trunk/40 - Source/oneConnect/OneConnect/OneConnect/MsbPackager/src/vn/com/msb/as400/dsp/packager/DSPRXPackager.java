// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPRXPackager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPRXPackager extends DSPPackager
{

    public DSPRXPackager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(130, "Description for field 3"), new DSPCharField(130, "Description for field 4"), new DSPCharField(40, "Description for field 5"), new DSPCharField(3, "Description for field 6"), new DSPCharField(40, "Description for field 7"), new DSPCharField(3, "Description for field 8")
        });
        addBody("RX".toUpperCase(), fld);
    }
}
