package com.sise.utils;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class ParseXmlUtils {

	public final static String TAG = "ParseXmlUtils";

	
	/**
	 * Pull解析方式
	 * @param xmlData ：xml数据
	 */
	public static void parseXMLWithPull(String xmlData) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
			xmlPullParser.setInput(new StringReader(xmlData));
			//获取当前解析事件
			int eventType = xmlPullParser.getEventType();

			//各个结点
			String id = "";
			String name = "";
			String version = "";

			while (eventType != XmlPullParser.END_DOCUMENT) {// 还没解析到底时
				String nodeName = xmlPullParser.getName();//获取当前结点名
				switch (eventType) {
				// 开始解析某个结点
				case XmlPullParser.START_TAG:
					if ("id".equals(nodeName)) {
						id = xmlPullParser.nextText();//获取结点内具体内容
					} else if ("name".equals(nodeName)) {
						name = xmlPullParser.nextText();
					} else if ("version".equals(nodeName)) {
						version = xmlPullParser.nextText();
					}
					break;
				case XmlPullParser.END_TAG:
					if ("app".equals(nodeName)) {
						Log.d(TAG, "id is " + id);
						Log.d(TAG, "name is " + name);
						Log.d(TAG, "version is " + version);
					}
					break;
				default:
					break;
				}
				//获取下一个解析事件
				eventType=xmlPullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * SAX解析方式
	 * @param xmlData ：xml数据
	 */
	public static void parseXMLWithSAX(String xmlData){
		try {
			SAXParserFactory factory=SAXParserFactory.newInstance();
			XMLReader xmlReader=factory.newSAXParser().getXMLReader();
			ContentHandler handler=new ContentHandler();
			//将ContentHandler的实例设置到XMLReader中
			xmlReader.setContentHandler(handler);
			//开始执行解析
			xmlReader.parse(new InputSource(new StringReader(xmlData)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
