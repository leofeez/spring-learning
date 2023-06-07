package editor;

import pojo.Son;

import java.beans.PropertyEditorSupport;

/**
 * @author leofee
 */
public class MySonPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("Son property value is: " + text);
        Son son = new Son();
        son.setName(text);
        setValue(son);
        System.out.println("Son property value resolved: " + text + " ——> " + son.toString());
    }
}
