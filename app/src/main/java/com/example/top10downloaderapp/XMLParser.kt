package com.example.top10downloaderapp

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class XMLParser {

    private val feed = ArrayList<App>()

    private var text = ""
    private var name = ""
    private var summary = ""

    fun parseRSS(): ArrayList<App> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
            parser.setInput(url.openStream(), null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT){
                val tagName = parser.name
                when(eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("summary", true) -> {
                            summary = text
                        }
                        tagName.equals("im:name", true) -> {
                            name = text
                            feed.add(App(name, summary))
                        }
                        else -> {}
                    }
                    else -> {}
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return feed
    }
}