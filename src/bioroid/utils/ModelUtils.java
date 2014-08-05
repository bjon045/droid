package bioroid.utils;

import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ModelUtils {

    @SuppressWarnings("unchecked")
    public static <T> T loadModelObject(Class<T> t, File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(t);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (T) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T loadModelObject(Class<T> t, String filename) {
        try {
            JAXBContext jc = JAXBContext.newInstance(t);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            URL url = ModelUtils.class.getResource(filename);
            return (T) unmarshaller.unmarshal(url);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void saveModelObject(T t, File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(t, file);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
