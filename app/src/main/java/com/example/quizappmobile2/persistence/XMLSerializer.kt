package persistence

import android.content.res.Resources
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import models.Rounds
import java.io.InputStreamReader
import java.io.Reader
import kotlin.Throws
import java.lang.Exception
import java.io.FileWriter

/**
 * A serializer implementation that uses XML for object serialization and deserialization.
 *
 * This class implements the [Serializer] interface and provides the ability to read
 * and write objects to/from an XML format. It uses the XStream library for XML
 * serialization/deserialization.
 *
 * @param resources The Resources object for accessing app resources.
 * @param xmlResourceId The resource ID of the XML file to read from or write to during serialization and deserialization.
 */
class XMLSerializer(
    private val resources: Resources,
    private val xmlResourceId: Int,
    loadedRounds: List<Rounds>
) : Serializer {
    /**
     * Read an object from the XML file and return it.
     *
     * @return The deserialized object.
     * @throws Exception if there is an error during deserialization.
     */
    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(DomDriver())
        xStream.allowTypes(arrayOf(Any::class.java))
        xStream.allowTypesByWildcard(arrayOf("models.*"))

        val inputStream = resources.openRawResource(xmlResourceId)
        val reader: Reader = InputStreamReader(inputStream)

        val obj = xStream.fromXML(reader)
        reader.close()

        return obj
    }

    /**
     * Write the provided object to the XML file.
     *
     * @param obj The object to be serialized and written to the file.
     * @throws Exception if there is an error during serialization and writing.
     */
    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(DomDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(resources.toString())) // Note: You need to provide a valid file path here
        outputStream.writeObject(obj)
        outputStream.close()
    }
}
