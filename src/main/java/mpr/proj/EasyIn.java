package mpr.proj;

import java.io.*;
import java.sql.Date;

import mpr.proj.pedigree.Horse;
import mpr.proj.pedigree.Sex;

public abstract class EasyIn {

    static String s = new String();
    static byte[] b = new byte[512];
    static int bytesRead = 0;

    public static String getString() {
        boolean ok = false;
        while(!ok) {
            try {
                bytesRead = System.in.read(b);
                s = new String(b,0,bytesRead-1);
                s=s.trim();
                ok = true;
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }
    
    public static void clear(){
            try {
                        Runtime.getRuntime().exec("cls");
                } catch (IOException e) {
                        
                }        
    }

    public static int getInt() {
        int i = 0;
        boolean ok = false;
        while(!ok) {
            try {
                bytesRead = System.in.read(b);
                s = new String(b,0,bytesRead-1);
                i = Integer.parseInt(s.trim());
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("\nEasyIn: podana wartosc powinna byc liczba calkowita!\n");
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return i;
    }

    public static byte getByte() {
        byte i = 0;
        boolean ok = false;
        while(!ok) {
            try {
                s = new String(b,0,bytesRead-1);
                bytesRead = System.in.read(b);
                i = Byte.parseByte(s.trim());
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("\nEasyIn: podana wartosc powinna byc typu byte!\n");
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return i;
    }

    public static short getShort() {
        short i = 0;
        boolean ok = false;
        while(!ok) {
            try {
                bytesRead = System.in.read(b);
                s = new String(b,0,bytesRead-1);
                i = Short.parseShort(s.trim());
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("\nEasyIn: podana wartosc powinna byc liczba typu short integer!\n");
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return i;
    }

    public static long getLong() {
        long l = 0;
        boolean ok = false;
        while(!ok) {
            try {
                bytesRead = System.in.read(b);
                s = new String(b,0,bytesRead-1);
                l = Long.parseLong(s.trim());
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("\nEasyIn: podana wartosc powinna byc liczba typu long integer!\n");
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return l;
    }

    public static double getDouble() {
        double d = 0;
        boolean ok = false;
        while(!ok) {
            try {
                bytesRead = System.in.read(b);
                s = new String(b,0,bytesRead-1);
                d = (Double.valueOf(s.trim())).doubleValue();
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("\nEasyIn: podana wartosc powinna byc liczba typu double!\n");
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return d;
    }

    public static float getFloat() {
        float f = 0;
        boolean ok = false;
        while(!ok) {
            try {
                bytesRead = System.in.read(b);
                s = new String(b,0,bytesRead-1);
                f = (Float.valueOf(s.trim())).floatValue();
                ok = true;
            } catch(NumberFormatException e) {
                System.out.println("\nEasyIn: podana wartosc powinna byc liczba typu float!\n");
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return f;
    }

    public static char getChar() {
        char c = ' ';
        boolean ok = false;
        while(!ok) {
            try {
                bytesRead = System.in.read(b);
                s = new String(b,0,bytesRead-1);
                if(s.trim().length()!=1) {
                    System.out.println("\nEasyIn: podana wartosc powinna byc pojedynczym znakiem!\n");
                } else {
                    c = s.trim().charAt(0);
                    ok = true;
                }
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return c;
    }

    public static void pause() {
        boolean ok = false;
        while(!ok) {
            try {
                System.in.read(b);
                ok = true;
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void pause(String messageIn) {
        boolean ok = false;
        while(!ok) {
            try {
                System.out.print(messageIn);
                System.in.read(b);
                ok = true;
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}