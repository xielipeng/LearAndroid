package org.xlipeng.bluetoothbledemo;

/**
 * Created by xielipeng on 2016/12/23.
 */

public class DeviceBytes {

    /**
     * byte[] -->16进制字符串
     * @param buffer
     * @return
     */
    public static String byte2hex(byte[] buffer) {
        String h = "";
        for (int i = 0; i < buffer.length; i++) {
            String temp = Integer.toHexString(buffer[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            h = h + temp;
        }
        return h;
    }
}
