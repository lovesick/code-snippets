package io.github.lovesick;

/**
 * 数据转换相关
 *
 * @author zuojian
 * @date 2018/12/5
 */
public class BytesIntStringDataConverter {


    /**
     * int转byte数组 (高位在前)
     *
     * @param value int
     * @return byte数组
     */
    public static byte[] intToBytes(int value) {
        return intToBytes(value, 4);
    }

    /**
     * int转byte数组 (高位在前)
     *
     * @param value int
     * @param len   返回数组长度
     * @return byte数组
     */
    public static byte[] intToBytes(int value, int len) {
        byte[] result;

        switch (len) {
            case 1:
                result = new byte[1];
                result[0] = (byte) (value & 0xFF);
                break;
            case 2:
                result = new byte[2];
                result[0] = (byte) ((value >> 8) & 0xFF);
                result[1] = (byte) (value & 0xFF);
                break;
            case 3:
                result = new byte[3];
                result[0] = (byte) ((value >> 16) & 0xFF);
                result[1] = (byte) ((value >> 8) & 0xFF);
                result[2] = (byte) (value & 0xFF);
                break;
            case 4:
                result = new byte[4];
                result[0] = (byte) ((value >> 24) & 0xFF);
                result[1] = (byte) ((value >> 16) & 0xFF);
                result[2] = (byte) ((value >> 8) & 0xFF);
                result[3] = (byte) (value & 0xFF);
                break;
            default:
                result = new byte[1];
                break;
        }

        return result;

    }


    /**
     * 字符串转Byte数组
     *
     * @param str 字符串
     * @return Byte数组
     */
    public static byte[] stringToBytes(String str) {
        if (str == null) {
            return new byte[0];
        }
        return str.getBytes();
    }

    /**
     * Byte数组转字符串
     *
     * @param src Byte数组
     * @return 字符串
     */
    public static String bytesToString(byte[] src) {
        return new String(src);
    }


    /**
     * Byte数组转字符串
     *
     * @param src    Byte数组
     * @param offset 偏移量
     * @return 字符串
     */
    public static String bytesToString(byte[] src, int offset) {
        if (src == null) {
            return "";
        }
        byte[] bytes;

        if (offset == 0) {
            bytes = src;
        } else {
            bytes = new byte[src.length - offset];
            System.arraycopy(src, offset, bytes, 0, src.length - offset);
        }
        return new String(bytes);
    }


    /**
     * Byte数组转字符串
     *
     * @param src    Byte数组
     * @param offset 偏移量
     * @param len    长度，即结束位置-偏移量
     * @return 字符串
     */
    public static String bytesToString(byte[] src, int offset, int len) {
        if (src == null) {
            return "";
        }
        byte[] bytes = new byte[len];
        System.arraycopy(src, offset, bytes, 0, len);
        return bytesToString(bytes, 0);
    }

    /**
     * byte数组转int (高位在前)
     *
     * @param src byte数组
     * @return int
     */
    public static int bytesToInt(byte[] src) {
        return bytesToInt(src, 0);
    }

    /**
     * byte数组转int (高位在前)
     *
     * @param src    byte数组
     * @param offset 偏移量
     * @return int
     */
    public static int bytesToInt(byte[] src, int offset) {
        byte[] bytes;

        if (offset == 0) {
            bytes = src;
        } else {
            bytes = new byte[src.length - offset];
            System.arraycopy(src, offset, bytes, 0, src.length - offset);
        }

        int value = 0;
        int len = bytes.length;
        switch (len) {
            case 0:
                break;
            case 1:
                value = bytes[0] & 0xFF;
                break;
            case 2:
                value = ((src[offset] & 0xFF) << 8)
                        | (src[offset + 1] & 0xFF);
                break;
            case 3:
                value = ((src[offset] & 0xFF) << 16)
                        | ((src[offset + 1] & 0xFF) << 8)
                        | (src[offset + 2] & 0xFF);
                break;
            case 4:
                value = ((src[offset] & 0xFF) << 24)
                        | ((src[offset + 1] & 0xFF) << 16)
                        | ((src[offset + 2] & 0xFF) << 8)
                        | (src[offset + 3] & 0xFF);
                break;
            default:
                break;
        }

        return value;
    }


    /**
     * byte数组转int (高位在前)
     *
     * @param src    byte数组
     * @param offset 偏移量
     * @param len    长度，即结束位置-偏移量
     * @return int
     */
    public static int bytesToInt(byte[] src, int offset, int len) {
        byte[] bytes = new byte[len];
        System.arraycopy(src, offset, bytes, 0, len);
        return bytesToInt(bytes, 0);
    }

    /**
     * 拼接多个Byte数组
     *
     * @param values 多个Byte数组
     * @return 拼接后的数组
     */
    public static byte[] mergeBytes(byte[]... values) {
        int lengthByte = 0;
        for (byte[] value : values) {
            lengthByte += value.length;
        }

        byte[] allByte = new byte[lengthByte];
        int countLength = 0;
        for (byte[] b : values) {
            System.arraycopy(b, 0, allByte, countLength, b.length);
            countLength += b.length;
        }
        return allByte;
    }


}
