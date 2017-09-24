package com.dubbo.codec;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMWriter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import sun.security.rsa.RSAPrivateKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;
import sun.security.util.DerValue;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Title:        RSA算法 
 * Description:  RSA算法
 *
 */
public class Rsa
{
    private static final Provider provider = new BouncyCastleProvider();
    private static final String alg = "RSA";
    private static final String providerStr = "BC";
    private static final String padding = "RSA/ECB/PKCS1Padding";

    private int keysize = 1024;

    private PrivateKey privateKey; // 私钥
    private PublicKey publicKey; // 公钥

    public Rsa()
    {
        Security.addProvider(provider);
    }

    /**
     * 生成密钥对
     *
     * @return KeyPair
     * @throws CodecException 异常
     */
    public KeyPair genKeyPair() throws CodecException
    {
        try
        {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(alg, providerStr);
            kpg.initialize(1024);
            KeyPair keyPair = kpg.generateKeyPair();
//            RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();
//            rsaKeyPairGenerator.initialize(keysize, null);
//            KeyPair keyPair = rsaKeyPairGenerator.generateKeyPair();
            this.setPrivateKey(keyPair.getPrivate());
            this.setPublicKey(keyPair.getPublic());
            return keyPair;
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public byte[] encode(Key key, byte[] in) throws CodecException
    {
        try
        {
            Cipher cip = Cipher.getInstance(padding, provider);
            cip.init(Cipher.ENCRYPT_MODE, key);
            return cip.doFinal(in);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }
    
    public byte[] encodeL(Key key, String in) throws CodecException
    {
    	int rsa_encode_size = 32;
        int strlength = in.length();
        byte[] encodeByte = new byte[strlength*8];
        int tmplen;
        int encodelength = 0;
		for( int loop=0;loop<strlength;loop+=rsa_encode_size)
        {
        	if( strlength > loop+rsa_encode_size )
        	{
        		tmplen = loop+rsa_encode_size;
        	}
        	else
        	{
        		tmplen = strlength;
        	}
        	byte[] substrbyte = in.substring(loop, tmplen).getBytes();
        	byte[] tmpbyte = encode(key, substrbyte);
        	System.arraycopy(tmpbyte, 0, encodeByte, encodelength, tmpbyte.length);
        	encodelength += tmpbyte.length;
        	tmpbyte = null;
        	substrbyte = null;
        }
		byte[] returnByte = new byte[encodelength];
        System.arraycopy(encodeByte, 0, returnByte, 0, encodelength);
        encodeByte = null;
        
        return returnByte;
    }
    
    public byte[] decode(Key key, byte[] in) throws CodecException
    {
        try
        {
            Cipher cip = Cipher.getInstance(padding, provider);
            cip.init(Cipher.DECRYPT_MODE, key);
            return cip.doFinal(in);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }
    
    public byte[] decodeL( Key key, byte[] in ) throws CodecException
    {
    	int rsa_decode_size = 128;
    	int length = in.length;
        byte[] subByte = new byte[128];
        byte[] decodeByte = new byte[length*2];
        int decodelength = 0;
        for( int loop=0;loop<length;loop+=rsa_decode_size)
        {
        	System.arraycopy(in, loop, subByte, 0, rsa_decode_size);
        	byte[] tmpbyte = decode(key,subByte);
        	System.arraycopy(tmpbyte, 0, decodeByte, decodelength, tmpbyte.length);
        	decodelength += tmpbyte.length;
        	tmpbyte = null;
        }
        byte[] returnByte = new byte[decodelength];
        System.arraycopy(decodeByte, 0, returnByte, 0, decodelength);
        decodeByte = null;
        subByte = null;
        return returnByte;
    }
    
    public void loadPemFromFile(String file) throws CodecException
    {
        try
        {
            loadPemFromReader(new FileReader(file));
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public void loadPemFromString(String str) throws CodecException
    {
        try
        {
            loadPemFromReader(new StringReader(str));
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    private void loadPemFromReader(Reader reader) throws CodecException
    {
        try
        {
            PEMParser pemReader = new PEMParser(reader);
            Object o = pemReader.readObject();
            if (o instanceof KeyPair)
            {
                this.setPrivateKey(((KeyPair) o).getPrivate());
                this.setPublicKey(((KeyPair) o).getPublic());
            } else if (o instanceof PublicKey)
            {
                this.setPublicKey((PublicKey) o);
            } else
            {
                throw new CodecException("The file is not a pem key file!");
            }
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public void loadDerPrivateKey(byte[] prikey) throws CodecException
    {
        try
        {
            DerValue pri = new DerValue(prikey);
            this.setPrivateKey(RSAPrivateKeyImpl.parseKey(pri));
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public void loadDerPrivateKeyFromFile(String filename) throws CodecException
    {
        try
        {
            byte[] pri = FileUtils.readFileToByteArray(new File(filename));
            DerValue derValue = new DerValue(pri);
            this.setPrivateKey(RSAPrivateKeyImpl.parseKey(derValue));
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public void loadDerPublicKey(byte[] pubkey) throws CodecException
    {
        try
        {
            DerValue pub = new DerValue(pubkey);
            this.setPublicKey(RSAPublicKeyImpl.parse(pub));
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public void loadDerPublicKeyFromFile(String filename) throws CodecException
    {
        try
        {
            byte[] pub = FileUtils.readFileToByteArray(new File(filename));
            DerValue derValue = new DerValue(pub);
            this.setPublicKey(RSAPublicKeyImpl.parse(derValue));
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    private Object loadFromJavaObject(String filename) throws CodecException
    {
        ObjectInputStream ois;
        try
        {
            FileInputStream fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e)
        {
            throw new CodecException(e);
        } finally
        {
            ois = null;
        }
    }

    public void loadPublicKeyFromJavaObject(String filename) throws CodecException
    {
        this.publicKey = (PublicKey) loadFromJavaObject(filename);
    }

    public void loadPrivateKeyFromJavaObject(String filename) throws CodecException
    {
        this.privateKey = (PrivateKey) loadFromJavaObject(filename);
    }

    public void savePrivateToDer(String filename) throws CodecException
    {
        try
        {
            FileUtils.writeByteArrayToFile(new File(filename), this.getPrivateKey().getEncoded());
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public void savePublicToDer(String filename) throws CodecException
    {
        try
        {
            FileUtils.writeByteArrayToFile(new File(filename), this.getPublicKey().getEncoded());
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    private void savePemToWriter(Object object, Writer writer) throws CodecException
    {
        PEMWriter pemWriter;
        try
        {
            pemWriter = new PEMWriter(writer);
            pemWriter.writeObject(object);
            pemWriter.flush();
            pemWriter.close();
        } catch (Exception e)
        {
            throw new CodecException(e);
        } finally
        {
            pemWriter = null;
        }
    }

    public void savePrivateToPemFile(String filename) throws CodecException
    {
        try
        {
            Writer writer = new FileWriter(filename);
            savePemToWriter(this.getPrivateKey(), writer);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public void savePublicToPemFile(String filename) throws CodecException
    {
        try
        {
            Writer writer = new FileWriter(filename);
            savePemToWriter(this.getPublicKey(), writer);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    /**
     * 保存私钥为.net格式
     *
     * @param filename
     * @throws CodecException
     */
    public void savePrivateKeyToDotNet(String filename) throws CodecException
    {
        try
        {
            Element valueElement = new Element("RSAKeyValue");
            Document document = new Document(valueElement);
            valueElement.addContent(new Element("Modulus").
                    setText(Base64.encode(((RSAPrivateKey) this.privateKey).getModulus().toByteArray())));
            valueElement.addContent(new Element("Exponent").
                    setText(Base64.encode(((RSAPrivateKey) this.privateKey).getPrivateExponent().toByteArray())));

            this.writeXML(filename, document);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    /**
     * 保存公钥为.net格式
     *
     * @param filename 文件名称
     * @throws CodecException 异常
     */
    public void savePublicKeyToDotNet(String filename) throws CodecException
    {
        try
        {
            Element valueElement = new Element("RSAKeyValue");
            Document document = new Document(valueElement);
            valueElement.addContent(new Element("Modulus").
                    setText(Base64.encode(((RSAPublicKey) this.publicKey).getModulus().toByteArray())));
            valueElement.addContent(new Element("Exponent").
                    setText(Base64.encode(((RSAPublicKey) this.publicKey).getPublicExponent().toByteArray())));

            this.writeXML(filename, document);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    /**
     * 生成xml
     *
     * @param filename String
     * @param document XML内容
     * @throws CodecException 异常
     */
    private void writeXML(String filename, Document document) throws CodecException
    {
        try
        {
            XMLOutputter outputter = new XMLOutputter();
            FileWriter writer = new FileWriter(filename);
            outputter.output(document, writer);
            writer.close();
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public PrivateKey getPrivateKey()
    {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey)
    {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey()
    {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey)
    {
        this.publicKey = publicKey;
    }

    public int getKeysize()
    {
        return keysize;
    }

    public void setKeysize(int keysize)
    {
        this.keysize = keysize;
    }

    public static void main(String[] arg) throws Exception
    {
        Rsa rsa = new Rsa();
//        rsa.genKeyPair();
//        rsa.loadPrivateKeyFromJavaObject("c:\\cookie_pri.key");
//        rsa.loadPublicKeyFromJavaObject("c:\\cookie_pub.key");
//        rsa.savePrivateToPemFile("c:\\pri_1.pem");
//        rsa.savePublicToPemFile("c:\\pub_1.pem");
//        rsa.savePrivateToDer("c:\\pri_n.der");
//        rsa.savePublicToDer("c:\\pub_n.der");
//        rsa.savePrivateKeyToDotNet("c:\\pri_n.xml");
//        rsa.savePublicKeyToDotNet("c:\\pub_n.xml");
        rsa.loadPrivateKeyFromJavaObject("D:\\sunluming\\cookie_pri_dev.key");
        rsa.loadPublicKeyFromJavaObject("D:\\sunluming\\cookie_pub_dev.key");

//        byte[] encodeStr = rsa.encode(rsa.getPrivateKey(), "的:\\sunluming\\svn\\netid-java\\trunk\\codfddfdnfig\\test\\passport\\netid-passport\\config\\cookie_pri.key1234567890-12340000".getBytes());
//        System.out.println(encodeStr.length);

//        DataInputStream stream = new DataInputStream(new FileInputStream("D:\\sunluming\\test.data"));
//        byte[] encodeStr = new byte[4096];
//		int length = stream.read(encodeStr);
//        stream.close();
        byte[] encodeStr = Base64.decode("S0TrBf43iYbtYmf50tWqRu1J0LENz1s6RtU6WEDzEgYxdF+izhf5thaR9UW2qhViaWB2sLDuAWIIJz4mMp+mNmArMY2MrzaMZA5Qg9cDeIjA5qhigmH52NBN+BE/oPX5+P5Yn1JFUUUE8cLyJ8eqg71lhC+NoSkxRx9ewWy2Dv0=");
        int length = encodeStr.length;
        byte[] encodeStr1 = new byte[128];
        String s = "";
        for( int loop=0;loop<length;loop+=128)
        {
        	System.arraycopy(encodeStr, loop, encodeStr1, 0, 128);
        	System.out.println(encodeStr1.length);
        	s += new String(rsa.decode(rsa.getPublicKey(),encodeStr1));
        }
//        String s = Base64.encode(encodeStr);
//        String s1 = new String(rsa.decode(rsa.getPublicKey(), Base64.decode(s)));
//        System.out.println(s);
        System.out.println(s.length() + "---" + s);
    }

}