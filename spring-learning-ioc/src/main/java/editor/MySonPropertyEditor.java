package editor;

import pojo.Son;

import java.beans.PropertyEditorSupport;

/**
 * @author leofee
 */
public class MySonPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Son son = new Son();

        son.setName(text);

        setValue(son);
    }
}
