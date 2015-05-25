// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSPC0Packager.java

package vn.com.msb.as400.dsp.packager;

import vn.com.msb.as400.dsp.*;

public class DSPC0Packager extends DSPPackager
{

    public DSPC0Packager()
    {
    }

    public void declare()
    {
        DSPField fld[] = null;
        fld = (new DSPField[] {
            new DSPHexField(1, "Description for field 1"), new DSPCharField(40, "Description for field 2"), new DSPCharField(40, "Description for field 3"), new DSPCharField(40, "Description for field 4"), new DSPCharField(19, "Description for field 5"), new DSPCharField(1, "Description for field 6")
        });
        addBody("C0".toUpperCase(), fld);
    }
}
