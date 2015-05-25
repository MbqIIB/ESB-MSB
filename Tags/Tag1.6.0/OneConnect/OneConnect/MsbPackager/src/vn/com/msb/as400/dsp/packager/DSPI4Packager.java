// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPI4Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPI4Packager extends DSPPackager
{

    public DSPI4Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPPackedField(2, 0, "Description for field 1"), new DSPCharField(2, "Description for field 2"), new DSPCharField(2, "Description for field 3"), new DSPPackedField(8, 2, "Description for field 4"), new DSPPackedField(8, 2, "Description for field 5")
        });
        addBody("I4".toUpperCase(), fld);
    }
}
