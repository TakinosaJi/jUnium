package exdevlab.junium.utils;


import exdevlab.junium.selenium.SettingsBase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SettingsFabric <T extends SettingsBase> {

    private final Constructor<? extends T> _ctor;

    public SettingsFabric(Class<? extends T> impl) throws NoSuchMethodException {
        this._ctor = impl.getConstructor();
    }

    public T FromXML(String filePath) throws IllegalAccessException,
            InvocationTargetException, InstantiationException, JAXBException {

        T _obj = _ctor.newInstance();
        JAXBContext jaxbContext = JAXBContext.newInstance(_obj.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();;

        File file = new File(System.getProperty("settings.file.path", filePath));
        if(!file.exists()){
            jaxbMarshaller.marshal(_obj, file);
        }

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T settingsObj = (T) jaxbUnmarshaller.unmarshal(file);
        //jaxbMarshaller.marshal(settingsObj, file); TODO: Investigate how to serialize object to xml with indentation.

        return settingsObj;
    }
}