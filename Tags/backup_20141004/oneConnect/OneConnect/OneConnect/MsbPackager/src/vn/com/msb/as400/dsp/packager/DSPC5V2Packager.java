// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPC5V2Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPC5V2Packager extends DSPPackager
{

    public DSPC5V2Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(1, "Description for field 2"), new DSPCharField(1, "Description for field 3"), new DSPPackedField(3, 0, "Description for field 4"), new DSPPackedField(3, 0, "Description for field 5"), new DSPCharField(1, "Description for field 6"), new DSPCharField(35, "Description for field 7"), new DSPCharField(1, "Description for field 8")
        });
        addBody("C5V2".toUpperCase(), fld);
    }
}
