package exdevlab.junium.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

public class JNA {
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONDBLCLK = 0x203;
    static int WM_CLOSE = 0x10;
    final static String winTitle = "Untitled - Notepad";

    public  void performKeyPress (char String) {

        User32 user32 = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        WinDef.HWND hwnd = user32.FindWindow(null, winTitle);
        user32.SetForegroundWindow(hwnd);

        long y = 77 + (22 << 16);//x + (y << 16)
        WinDef.LPARAM l = new WinDef.LPARAM(y);
        WinDef.WPARAM w = new WinDef.WPARAM(0);
        user32.PostMessage(hwnd, String, w, l);
        user32.PostMessage(hwnd, String, w, l);
    }
}
