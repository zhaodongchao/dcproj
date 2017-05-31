package org.dongchao.security.redis;


import java.io.*;

/**
 * java对象序列化工具类
 * Created by zhaodongchao on 2017/5/30.
 */
public class JavaSerializable implements Serializable{
    /**
     * 将二进制字节反序列化为Object
     * @param bytes 待反序列化的二进制字节
     * @return Object
     */
    public static Object deSerializable(byte[] bytes) {
        Object result = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            result = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 将Java对象序列化成二进制字节
     * @param obj 待序列划的Java对象
     * @return
     */
    public static byte[] enJavaSerializable(Object obj) {
        if (obj instanceof String){
            try {
                return ((String) obj).getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        byte[] bytes = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            bytes = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
