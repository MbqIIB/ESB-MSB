/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multi.seatechit.mbconnector.conversion;

import multi.seatechit.mbconnector.utils.Utils;

/**
 *
 * @author Hungpd
 */
class Bits {
    protected static final int size = 8;
    private boolean _$16892[];
    private static final boolean _$16893 = false;
    public Bits()
        {
            _$16892 = new boolean[8];
            for(int i = 0; i < 8; i++)
                _$16892[i] = false;

        }

        public void set(int i)
        {
            if(i >= 8 || i < 0)
            {
                System.out.println(String.valueOf(String.valueOf((new StringBuffer("Error: Invalid location[")).append(i).append("]. Bits.set() failed."))));
                return;
            } else
            {
                _$16892[i] = true;
                return;
            }
        }

        protected int getInt(int i)
        {
            if(i >= 8 || i < 0)
            {
                System.out.println(String.valueOf(String.valueOf((new StringBuffer("Error: Invalid location[")).append(i).append("]. Bits.get() failed."))));
                return -1;
            } else
            {
                return !_$16892[i] ? 0 : 1;
            }
        }

        public String toString()
        {
            StringBuffer sb = new StringBuffer();
            for(int i = 7; i >= 0; i--)
            {
                sb.append(!_$16892[i] ? "0" : "1");
                if(i % 4 == 0 && i != 7)
                    sb.append(" ");
            }

            return sb.toString();
        }

        public String getLocation()
        {
            StringBuffer sb = new StringBuffer("Bits = {");
            boolean on = false;
            for(int i = 0; i < 8; i++)
            {
                if(!_$16892[i])
                    continue;
                if(on && i != 7)
                {
                    sb.append(", ");
                    on = false;
                }
                sb.append(i);
                on = true;
            }

            sb.append("}");
            return sb.toString();
        }

        public boolean get(int i)
        {
            if(i >= 8 || i < 0)
            {
                System.out.println(String.valueOf(String.valueOf((new StringBuffer("Error: Invalid location[")).append(i).append("]. Bits.get() failed."))));
                return false;
            } else
            {
                return _$16892[i];
            }
        }

        public static Bits parseByte(byte b)
        {
            Bits bs = new Bits();
            for(int sh = 7; sh >= 0; sh--)
                if((b >> sh & 1) == 1)
                    bs.set(sh);

            return bs;
        }

        private static void _$16898(String value)
        {
            Byte b0 = Byte.valueOf(value);
            Bits b = parseByte(b0.byteValue());
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("\nValue=")).append(value).append("; binary ==> ").append(b.toString()))));
            System.out.println("Active location ==> ".concat(String.valueOf(String.valueOf(b.getLocation()))));
            for(int i = 0; i < 8; i++)
                System.out.println(String.valueOf(String.valueOf((new StringBuffer("Location [")).append(i).append("]=").append(b.get(i)))));

        }

        
//        public  void bitsTest(String data)
//        {
//            System.out.println("\nThis will be the sample string data expected to be return from host.");
//            if(data == null)
//                data = "abcde12345[]?";
//            byte ba[] = data.getBytes();
//            System.out.println(String.valueOf(String.valueOf((new StringBuffer("Raw=[")).append(data).append("]\nParsing...\n"))));
//            for(int i = 0; i < data.length(); i++)
//            {
//                Bits b = Utils.getBits(data, i);
//                System.out.println(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(i)))).append(". Raw=[").append(data.charAt(i)).append("]; Value=").append(ba[i]).append("; Binary ==> ").append(b.toString()))));
//                for(int j = 0; j < 8; j++)
//                    System.out.println(String.valueOf(String.valueOf((new StringBuffer("\tLocation [")).append(j).append("]=").append(b.get(j)))));
//
//                System.out.println();
//            }
//
//            try
//            {
//                System.out.println("---------------------------");
//                for(int k = 0; k < data.length(); k++)
//                {
//                    System.out.println();
//                    for(int l = 0; l < 8; l++)
//                        System.out.println(String.valueOf(String.valueOf((new StringBuffer("Byte[")).append(k).append("],bit[").append(l).append("]=").append(Utils.isBitTurnOn(data, k, l)))));
//
//                }
//
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//        }

}
